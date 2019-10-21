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
import sg.sjc.superhero.dtos.SuperPowers;

/**
 *
 * @author jhoan
 */

@Repository
public class SuperPowersDaoImpl implements SuperPowersDao {

    private final JdbcTemplate jdbcTemplate;

    private final String insertSuperPowers = "INSERT INTO Powers (powerName) VALUES (?)"; //create
    private final String selectAllSuperPowers = "SELECT powId, powerName FROM Powers"; //read all
    private final String selectSuperPowersById = selectAllSuperPowers + " WHERE powId = ?"; //readbyId
    private final String updateSuperPowers = "UPDATE Powers SET powerName = ? WHERE powId = ?"; //update
    private final String deleteSuperPowersById = "DELETE FROM Powers WHERE powId = ?"; //delete

    @Autowired
    public SuperPowersDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public SuperPowers createSuperPowers(SuperPowers superPowers) {
        jdbcTemplate.update(insertSuperPowers, superPowers.getName());
        superPowers.setSpwId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return superPowers;
    }

    @Override
    public List<SuperPowers> readAllSuperPowers() {
        return jdbcTemplate.query(selectAllSuperPowers, new SuperPowersJDBCMapper());
    }

    @Override
    public SuperPowers readSuperPowersById(int id) {
        return jdbcTemplate.queryForObject(selectSuperPowersById, new SuperPowersJDBCMapper(),id);
    }

    @Override
    public void updateSuperPowers(SuperPowers superPowers) {
        jdbcTemplate.update(updateSuperPowers,superPowers.getName(),superPowers.getSpwId()); 
    }

    @Override
    public void deleteSuperPowers(int id) {
        jdbcTemplate.update(deleteSuperPowersById,id);
    }


  
    
        private class SuperPowersJDBCMapper implements org.springframework.jdbc.core.RowMapper<SuperPowers> {

        @Override
        public SuperPowers mapRow(ResultSet rs, int i) throws SQLException {
            SuperPowers superPowers = new SuperPowers();
            
            superPowers.setSpwId(rs.getInt("powId"));
            superPowers.setName(rs.getString("powerName"));
            


            return superPowers;
        }

    }
}