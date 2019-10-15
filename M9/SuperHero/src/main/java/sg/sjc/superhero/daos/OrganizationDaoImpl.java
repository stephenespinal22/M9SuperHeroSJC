/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sg.sjc.superhero.dtos.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final JdbcTemplate jdbcTemplate;

    private final String insertOrganization = "INSERT INTO Organizations (`name`, `description`, address, longitude, latitude) VALUES (?,?,?,?,?)"; //create
    private final String selectAllOrganizations = "SELECT organizationId, `name`, `description`, address, longitude, latitude FROM Organizations"; //read all
    private final String selectOrganizationById = selectAllOrganizations + " WHERE organizationId = ?"; //readbyId
    private final String updateOrganization = "UPDATE Organizations SET `name` = ?, `description` = ?, address = ?, longitude = ?, latitude = ? WHERE organizationId = ?"; //update
    private final String deleteOrganizationById = "DELETE FROM LOCATIONS WHERE organizationId = ?"; //delete

    @Autowired
    public OrganizationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Organization createOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> readAllOrganizations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization readOrganizationById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrganization(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
