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

    private final String getSuperPerson = "Select superId, `name`, `description`, isVillain, imagePath From SuperPersons Where superId = ?";
    private final String getAllSuperPersons = "Select superId, `name`, `description`, isVillain, imagePath From SuperPersons";
    private final String getAllSuperPersonsByOrganizationId = "Select sp.superId, `name`, `description`, isVillain, imagePath FROM SuperPersons as sp JOIN SuperPersonOrganization as spo ON sp.superId = spo.superId Where spo.orgId = ?";

    private final String getSuperPersonsBySightingId = "Select sp.superId, `name`, `description`, isVillain, imagePath FROM SuperPersons as sp JOIN SuperPersonSighting as sps ON sp.superId = sps.superId Where sps.sightingId = ?";

    private final String getAllSuperPersonsByPowerId = "Select sp.superId, `name`, `description`, isVillain, imagePath FROM SuperPersons as sp JOIN SuperPersonPower as spp ON sp.superId = spp.superId Where spp.powId = ?";

    private final String addHeroVillain = "Insert Into SuperPersons(`name`, `description`, isVillain) values (?,?,?)";
    private final String updateHeroVillain = "Update SuperPersons Set `name` = ?, `description` = ?, isVillain = ? Where superId = ?";
    private final String deleteHeroVillain = "Delete From SuperPersons Where superId = ?;";

    //insert Image Path
    private final String addImagePath = "Update SuperPersons Set imagePath = ? Where superId = ?";

    @Autowired
    public SuperPersonsDAOJdbcImpl(JdbcTemplate jdbcTemplate) {
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

    @Override
    public List<SuperPerson> getAllSuperPersonsByOrganizationId(int id) {
        return this.jdbc.query(getAllSuperPersonsByOrganizationId, new SuperPersonsJDBCMapper(), id);
    }

    @Override
    public List<SuperPerson> getSuperPersonsBySightingId(int sightingId) {
        return this.jdbc.query(getSuperPersonsBySightingId, new SuperPersonsJDBCMapper(), sightingId);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByPowerId(int spwId) {
        return this.jdbc.query(getAllSuperPersonsByPowerId, new SuperPersonsJDBCMapper(), spwId);
    }

    @Override
    public void addImage(String imagePath, int id) {
        jdbc.update(addImagePath, imagePath,id);
    }

    public static final class SuperPersonsJDBCMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int index) throws SQLException {
            SuperPerson superPerson = new SuperPerson();
            superPerson.setSuperId(rs.getInt("superId"));
            superPerson.setName(rs.getString("name"));
            superPerson.setDescription(rs.getString("description"));
            superPerson.setIsVillain(rs.getBoolean("isVillain"));
            superPerson.setImagePath(rs.getString("imagePath"));

//            superPerson.setOrganizations();
            return superPerson;

        }
    }

}
