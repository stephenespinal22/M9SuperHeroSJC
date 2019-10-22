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
public class SpPowersDAOJdbcImpl implements SpPowersDAO {
    
    private final JdbcTemplate jdbc;
    
    private final String insertSuperPower = "Insert into SuperPersonPower (superId, powId) values (?,?)";
    private final String deleteSuper = "Delete from SuperPersonPower Where superId = ?";
    private final String deleteSuperPower = "Delete From SuperPersonPower Where powId = ?";
    
    @Autowired
    public SpPowersDAOJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }
    

    @Override
    public void Create(int superId, int powId) {
        jdbc.update(insertSuperPower, superId, powId);
    }

    @Override
    public void Delete(int superId, int powId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperPersonById(int superId) {
        jdbc.update(deleteSuper, superId);
    }

    @Override
    public void deletePowerById(int powId) {
        jdbc.update(deleteSuperPower, powId);
    }
    
}
