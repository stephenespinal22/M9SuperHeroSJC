/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.util.List;
import sg.sjc.superhero.dtos.Organization;

/**
 *
 * @author stephenespinal
 */
public interface OrganizationDao {

    Organization createOrganization(Organization organization);

    List<Organization> readAllOrganizations();

    Organization readOrganizationById(int id);

    void updateOrganization(Organization organization);

    void deleteOrganization(int id);
}
