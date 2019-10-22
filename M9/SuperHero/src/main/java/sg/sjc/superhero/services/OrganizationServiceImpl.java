/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.sjc.superhero.daos.OrganizationDao;
import sg.sjc.superhero.daos.SPOrgDAO;
import sg.sjc.superhero.daos.SuperPersonsDAO;
import sg.sjc.superhero.dtos.Organization;
import sg.sjc.superhero.dtos.SuperPerson;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final SuperPersonsDAO spsDAO;
    private final SPOrgDAO spoDAO;
    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(SuperPersonsDAO spsDAO, SPOrgDAO spoDAO, OrganizationDao organizationDao) {
        this.spsDAO = spsDAO;
        this.spoDAO = spoDAO;
        this.organizationDao = organizationDao;
    }

    @Override
    @Transactional
    public Organization createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public List<Organization> readAllOrganizations() {

        List<Organization> organizationList = organizationDao.readAllOrganizations();

        List<SuperPerson> superList = new ArrayList<SuperPerson>();

        for (Organization org : organizationList) {
            superList = spsDAO.getAllSuperPersonsByOrganizationId(org.getOrgId());
            org.setSuperPersons(superList);
        }

        return organizationList;
    }

    @Override
    public Organization readOrganizationById(int id) {
        return organizationDao.readOrganizationById(id);
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }

    @Override
    @Transactional
    public void deleteOrganization(int id) {
        organizationDao.deleteOrganization(id);
    }

    @Override
    public void deleteOrgById(int orgId) {
        spoDAO.deleteOrgById(orgId);
    }

    @Override
    @Transactional
    public void createNewMember(int superId, int orgId) {
        spoDAO.Create(superId, orgId);
    }
}
