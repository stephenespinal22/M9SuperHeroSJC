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
import sg.sjc.superhero.dtos.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final JdbcTemplate jdbcTemplate;

    private final String insertOrganization = "INSERT INTO Organizations (`name`,`description`, contactInfo) VALUES (?,?,?)"; //create
    private final String selectAllOrganizations = "SELECT orgId, `name`,`description`, contactInfo FROM Organizations"; //read all
    private final String selectOrganizationById = selectAllOrganizations + " WHERE orgId = ?"; //readbyId
    private final String getAllOrganizationsBySuperPersonId = "Select org.orgId, `name`,`description`, contactInfo FROM Organizations as org JOIN SuperPersonOrganization as spo ON org.orgId = spo.orgId Where spo.superId = ?";
    private final String updateOrganization = "UPDATE Organizations SET `name` = ?, `description` = ?, contactInfo = ? WHERE orgId = ?"; //update
    private final String deleteOrganizationById = "DELETE FROM Organizations WHERE orgId = ?"; //delete

    @Autowired
    public OrganizationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        jdbcTemplate.update(insertOrganization, organization.getName(), organization.getDescription(), organization.getContactInfo());
        organization.setOrgId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return organization;
    }

    @Override
    public List<Organization> readAllOrganizations() {
        return jdbcTemplate.query(selectAllOrganizations, new OrganizationJDBCMapper());
    }

    @Override
    public Organization readOrganizationById(int id) {
        return jdbcTemplate.queryForObject(selectOrganizationById, new OrganizationJDBCMapper(), id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(updateOrganization, organization.getName(), organization.getDescription(),
                organization.getContactInfo(), organization.getOrgId());
    }

    @Override
    public void deleteOrganization(int id) {
        jdbcTemplate.update(deleteOrganizationById, id);
    }

    @Override
    public List<Organization> getOrganizationsBySuperPersonId(int id) {
        return jdbcTemplate.query(getAllOrganizationsBySuperPersonId, new OrganizationJDBCMapper(),id);
    }

    private class OrganizationJDBCMapper implements org.springframework.jdbc.core.RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();

            organization.setOrgId(rs.getInt("orgId"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setContactInfo(rs.getString("contactInfo"));

            return organization;
        }

    }
}
