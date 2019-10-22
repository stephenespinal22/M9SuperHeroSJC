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
    
    private final String insertSightingSuperPersonMerge = "Insert Into SuperPersonOrganization(superId, orgId) values (?,?);";
    private final String deleteSighting = "Delete From SuperPersonOrganization where superId = ?;";
    private final String deleteSuperPerson = "Delete From SuperPersonOrganization where orgId = ?;";
    
     @Autowired
    public SightingsSuperPersonsBridgeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public void createRelationShip(int sightingId, int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRelationShip(int sightingId, int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSightingById(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperPersonById(int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
