/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sg.sjc.dtos.SuperPerson;

@Repository
public class SuperPersonsDAOJdbcImpl implements SuperPersonsDAO {
    
    private final JdbcTemplate jdbc;
    
    private final String getSuperPerson = "Select * From SuperPersons Where superId = ?;";
    private final String getAllSuperPersons = "Select * From SuperPersons;";

    @Autowired
    public SuperPersonsDAOJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }
    
    
    @Override
    public SuperPerson getSuperPersonById(int id) {
        return this.jdbc.queryForObject(getSuperPerson, new SuperPersonsJDBCMapper(), id);
    }

    @Override
    public List<SuperPerson> getAllSuperPersons() {
        return this.jdbc.query(getAllSuperPersons, new SuperPersonsJDBCMapper());
    }

    @Override
    public List<SuperPerson> getAllHeroes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SuperPerson> getAllVillains() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SuperPerson addSuperPerson(SuperPerson superPerson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSuperPersonById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class SuperPersonsJDBCMapper implements RowMapper<SuperPerson> {
        
        @Override
        public SuperPerson mapRow(ResultSet rs, int index) throws SQLException {
            SuperPerson superPerson = new SuperPerson();
            superPerson.setSuperId(rs.getInt("superId"));
            superPerson.setName(rs.getString("`name`"));
            superPerson.setDescription(rs.getString("`description`"));
            superPerson.setIsVillain(rs.getBoolean("isVillain"));
            
            return superPerson;
            
        }
    }
    
}
