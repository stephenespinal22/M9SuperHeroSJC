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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sg.sjc.superhero.dtos.SuperPerson;

@Repository
public class SuperPersonsDAOJdbcImpl implements SuperPersonsDAO {
    
    private final JdbcTemplate jdbc;
    
    private final String getSuperPerson = "Select * From SuperPersons Where superId = ?;";
    private final String getAllSuperPersons = "Select * From SuperPersons;";
    private final String addHeroVillain = "Insert Into SuperPersons(`name`, `description`, isVillain) values (?,?,?);";
    private final String updateHeroVillain = "Update SuperPersons Set `name` = ?, `description` = ?, isVillain = ? Where superId = ?;";
    private final String deleteHeroVillain = "Delete From SuperPersons Where superId = ?;";

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
    @Transactional
    public SuperPerson addSuperPerson(SuperPerson superPerson) {
      jdbc.update(addHeroVillain, superPerson.getName(), superPerson.getDescription(), superPerson.isIsVillain());
      int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
      superPerson.setSuperId(newId);
      return superPerson;
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        jdbc.update(updateHeroVillain, superPerson.getName(), superPerson.getDescription(), superPerson.isIsVillain(), superPerson.getSuperId());
    }

    @Override
    @Transactional
    public void deleteSuperPersonById(int id) {
        jdbc.update(deleteHeroVillain, id);
    }
    
    public static final class SuperPersonsJDBCMapper implements RowMapper<SuperPerson> {
        
        @Override
        public SuperPerson mapRow(ResultSet rs, int index) throws SQLException {
            SuperPerson superPerson = new SuperPerson();
            superPerson.setSuperId(rs.getInt("superId"));
            superPerson.setName(rs.getString("name"));
            superPerson.setDescription(rs.getString("description"));
            superPerson.setIsVillain(rs.getBoolean("isVillain"));
//            superPerson.setOrganizations();
            
            return superPerson;
            
        }
    }
    
}
