/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sg.sjc.superhero.dtos.Sighting;

@Repository
public class SightingDaoImpl implements SightingDao {
   private final JdbcTemplate jdbcTemplate;

    private final String insertSighting = "INSERT INTO Sightings (`description`, locationId, sightingDate) VALUES (?,?,?)"; //create
    private final String selectAllSightings = "SELECT sightingId, `description`, locationId, sightingDate FROM Sightings"; //read all
    private final String selectSightingsById = selectAllSightings + " WHERE sightingId = ?"; //readbyId
    private final String updateSighting = "UPDATE Sightings SET `description` = ?, locationId = ?, sightingDate = ? WHERE sightingId = ?"; //update
    private final String deleteSightingById = "DELETE FROM Sightings WHERE sightingId = ?"; //delete

    @Autowired
    public SightingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        jdbcTemplate.update(insertSighting, sighting.getDescription(), 1, sighting.getSightingDate());
        sighting.setSightingId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return sighting;
    }

    @Override
    public List<Sighting> readAllSightings() {
        return jdbcTemplate.query(selectAllSightings, new SightingJDBCMapper());
    }

    @Override
    public Sighting readSightingById(int id) {
        return jdbcTemplate.queryForObject(selectSightingsById, new SightingJDBCMapper(), id);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(updateSighting, sighting.getDescription(), 1, sighting.getSightingDate(), sighting.getSightingId());
    }

    @Override
    public void deleteSighting(int id) {
        jdbcTemplate.update(deleteSightingById, id);
    }

    private class SightingJDBCMapper implements org.springframework.jdbc.core.RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();

            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setDescription(rs.getString("description"));
            sighting.setSightingDate(rs.getString("sightingDate"));

            return sighting;
        }

    }
}
