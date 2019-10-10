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
    private final String updateLocationById = "UPDATE Locations SET `name` = ?, `description` = ?, address = ?, longitude = ?, latitude = ? WHERE locationId = ?";
    private final String deleteLocationById = "DELETE FROM LOCATIONS WHERE locationId = ?";

    @Autowired
    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Location createLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> readAllLocations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location readLocationById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Location location) {
    }

    @Override
    public void deleteLocation(int id) {
    }

    private class LocationJDBCMapper implements org.springframework.jdbc.core.RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();

            return location;
        }

    }
}
