/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sg.sjc.superhero.dtos.Location;

/**
 *
 * @author stephenespinal
 */
@Repository
public class LocationDaoImpl implements LocationDao {

    private final JdbcTemplate jdbcTemplate;

    private final String insertLocation = "INSERT INTO Locations (`name`, `description`, address, longitude, latitude) VALUES (?,?,?,?,?)"; //create
    private final String selectAllLocations = "SELECT locationId, `name`, `description`, address, longitude, latitude FROM Locations"; //read all
    private final String selectLocationById = selectAllLocations + " WHERE locationId = ?"; //readbyId
    private final String updateLocationById = "UPDATE Locations SET `name` = ?, `description` = ?, address = ?, longitude = ?, latitude = ? WHERE locationId = ?"; //update
    private final String deleteLocationById = "DELETE FROM LOCATIONS WHERE locationId = ?"; //delete

    @Autowired
    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Location createLocation(Location location) {
        jdbcTemplate.update(insertLocation, location.getName(), location.getDescription(), location.getAddress(),
                location.getLongitude(), location.getLatitude());
        location.setLocationId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return location;
    }

    @Override
    public List<Location> readAllLocations() {
        return jdbcTemplate.query(selectAllLocations, new LocationJDBCMapper());
    }

    @Override
    public Location readLocationById(int id) {
        return jdbcTemplate.queryForObject(selectLocationById, new LocationJDBCMapper(),id);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(updateLocationById,location.getName(),location.getDescription(),
                location.getAddress(),location.getLongitude(),location.getLatitude(),location.getLocationId());
    }

    @Override
    public void deleteLocation(int id) {
        jdbcTemplate.update(deleteLocationById,id);
    }

    private class LocationJDBCMapper implements org.springframework.jdbc.core.RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            
            location.setLocationId(rs.getInt("locationId"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLongitude(rs.getDouble("longitude"));
            location.setLatitude(rs.getDouble("latitude"));

            return location;
        }

    }
}
