/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class SightingsSuperPersonsBridgeDaoImpl implements SightingsSuperPersonsBridgeDao {
    
    private final JdbcTemplate jdbc;
    
    private final String insertSightingSuperPersonMerge = "Insert Into SuperPersonSighting(sightingId, superId) values (?,?);";
    private final String deleteSighting = "Delete From SuperPersonSighting where sightingId = ?;";
    private final String deleteSuperPerson = "Delete From SuperPersonSighting where superId = ?;";
    
     @Autowired
    public SightingsSuperPersonsBridgeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public void createRelationShip(int sightingId, int superId) {
        jdbc.update(insertSightingSuperPersonMerge, sightingId, superId);
    }

    @Override
    public void deleteSightingById(int sightingId) {
        jdbc.update(deleteSighting, sightingId);
    }

    @Override
    public void deleteSuperPersonById(int superId) {
        jdbc.update(deleteSuperPerson, superId);
    }
    
}
