/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SPOrgDAOJdbcImpl implements SPOrgDAO {
    
    private final JdbcTemplate jdbc;
    
    private final String insertSuperOrgMerge = "Insert Into SuperPersonOrganization(superId, orgId) values (?,?);";
    
    @Autowired
    public SPOrgDAOJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public void Create(int superId, int orgId) {
        jdbc.update(insertSuperOrgMerge, superId, orgId);
    }

    @Override
    public void Delete(int superId, int orgId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperPersonById(int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrgById(int orgId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
