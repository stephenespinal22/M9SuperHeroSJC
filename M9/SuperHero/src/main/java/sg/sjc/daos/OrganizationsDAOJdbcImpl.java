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
import sg.sjc.dtos.Organization;


@Repository
public class OrganizationsDAOJdbcImpl implements OrganizationsDAO {
    
    private final JdbcTemplate jdbc;
    
    private final String getOrg = "Select * From Organizations Where orgId = ?;";
    
    @Autowired
    public OrganizationsDAOJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    @Override
    public Organization getOrgById(int id) {
        return this.jdbc.queryForObject(getOrg, new OrganizationsJDBCMapper(), id);
    }

    @Override
    public List<Organization> getAllOrgs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> listAllHeroOrgs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> listAllVillainOrgs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization addOrg(Organization org) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrg(Organization org) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrgById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        public static final class OrganizationsJDBCMapper implements RowMapper<Organization> {
        
        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrgId(rs.getInt("orgId"));
            organization.setName(rs.getString("`name`"));
            organization.setDescription(rs.getString("`description`"));
            organization.setContactInfo(rs.getString("contactInfo"));

            
            return organization;
            
        }
    }
    
}
