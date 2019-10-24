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
import sg.sjc.superhero.dtos.Sighting;

@Repository
public class SightingDaoImpl implements SightingDao {

    private final JdbcTemplate jdbcTemplate;

    private final String insertSighting = "INSERT INTO Sightings (`description`, locationId, sightingDate) VALUES (?,?,?)"; //create
    private final String selectAllSightings = "SELECT loc.locationId, `name`, loc.`description`, address, longitude, latitude,"
            + " Sightings.sightingId, Sightings.`description`, sightingDate FROM Locations AS loc JOIN Sightings "
            + "ON loc.locationId = Sightings.locationId "; //read all
    private final String selectAllSightingsOrdered = "SELECT loc.locationId, `name`, loc.`description`, address, longitude, latitude,"
            + " sightingId, Sightings.`description`, sightingDate FROM Locations AS loc JOIN Sightings "
            + "ON loc.locationId = Sightings.locationId ORDER BY STR_TO_DATE(sightingDate, '%m/%d/%Y %h:%i %p') DESC"; //read all
    private final String selectSightingsById = selectAllSightings + " WHERE sightingId = ?"; //readbyId
    private final String selectSightingsByLocationId = "SELECT loc.locationId, `name`, loc.`description`, address, longitude, latitude, Sightings.sightingId, Sightings.`description`, sightingDate FROM Locations AS loc JOIN Sightings ON loc.locationId = Sightings.locationId WHERE loc.locationId = ?";

    private final String getSightingsBySuperPersonId = selectAllSightings + " JOIN SuperPersonSighting as sps ON Sightings.sightingId = sps.sightingId Where sps.superId = ?";

    private final String updateSighting = "UPDATE Sightings SET `description` = ?, locationId = ?, sightingDate = ? WHERE sightingId = ?"; //update
    private final String deleteSightingById = "DELETE FROM Sightings WHERE sightingId = ?"; //delete

    @Autowired
    public SightingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        jdbcTemplate.update(insertSighting, sighting.getDescription(), sighting.getLocation().getLocationId(), sighting.getSightingDate());
        sighting.setSightingId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return sighting;
    }

    @Override
    public List<Sighting> readAllSightings() {
        return jdbcTemplate.query(selectAllSightingsOrdered, new SightingJDBCMapper());
    }

    @Override
    public Sighting readSightingById(int id) {
        return jdbcTemplate.queryForObject(selectSightingsById, new SightingJDBCMapper(), id);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(updateSighting, sighting.getDescription(), sighting.getLocation().getLocationId(), sighting.getSightingDate(), sighting.getSightingId());
    }

    @Override
    public void deleteSighting(int id) {
        jdbcTemplate.update(deleteSightingById, id);
    }

    @Override
    public List<Sighting> readSightingsByLocationId(int locationId) {
        return jdbcTemplate.query(selectSightingsByLocationId, new SightingJDBCMapper(), locationId);
    }

    @Override
    public List<Sighting> getSightingsBySuperPersonId(int superId) {
        return jdbcTemplate.query(getSightingsBySuperPersonId, new SightingJDBCMapper(),superId);
    }

    private class SightingJDBCMapper implements org.springframework.jdbc.core.RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {

            Location locationForThisSighting = new Location();
            locationForThisSighting.setLocationId(rs.getInt("locationId"));
            locationForThisSighting.setName(rs.getString("name"));
            locationForThisSighting.setDescription(rs.getString("description"));
            locationForThisSighting.setAddress(rs.getString("address"));
            locationForThisSighting.setLongitude(rs.getDouble("longitude"));
            locationForThisSighting.setLatitude(rs.getDouble("latitude"));

            Sighting sighting = new Sighting();

            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setDescription(rs.getString(8)); //8th column in query
            sighting.setSightingDate(rs.getString("sightingDate"));
            sighting.setLocation(locationForThisSighting);

            return sighting;
        }

    }
}
