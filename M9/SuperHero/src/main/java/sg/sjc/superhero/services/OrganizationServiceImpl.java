/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sjc.superhero.daos.OrganizationDao;
import sg.sjc.superhero.dtos.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
    
    @Override
    public Organization createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public List<Organization> readAllOrganizations() {
        return organizationDao.readAllOrganizations();
    }

    @Override
    public Organization readOrganizationById(int id) {
        return organizationDao.readOrganizationById(id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }

    @Override
    public void deleteOrganization(int id) {
        organizationDao.deleteOrganization(id);
    }
    
}
