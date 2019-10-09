/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.daos;

import java.util.List;
import sg.sjc.dtos.Organization;

/**
 *
 * @author corey
 */
public interface OrganizationsDAO {
    
    Organization getOrgById(int id);
    List<Organization> getAllOrgs();
    List<Organization> listAllHeroOrgs();
    List<Organization> listAllVillainOrgs();
    Organization addOrg(Organization org);
    void updateOrg(Organization org);
    void deleteOrgById(int id);
    
    
}
