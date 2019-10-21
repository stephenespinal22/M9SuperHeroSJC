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
public class SuperPersonsServiceImpl implements SuperPersonsService {

    private final SuperPersonsDAO spsDAO;
    private final SPOrgDAO spoDAO;
    private final OrganizationDao orgDAO;

    @Autowired
    public SuperPersonsServiceImpl(SuperPersonsDAO spsDAO, SPOrgDAO spoDAO, OrganizationDao orgDAO) {
        this.spsDAO = spsDAO;
        this.spoDAO = spoDAO;
        this.orgDAO = orgDAO;
    }

    @Override
    public SuperPerson getSuperPersonById(int id) {
        return spsDAO.getSuperPersonById(id);
    }

    @Override
    public List<SuperPerson> getAllSuperPersons() {

        List<SuperPerson> superPersonsList = spsDAO.getAllSuperPersons();
        
        List<Organization> orgList = new ArrayList<Organization>();

        for (SuperPerson superPerson : superPersonsList) {
            orgList = orgDAO.getOrganizationsBySuperPersonId(superPerson.getSuperId());
            superPerson.setOrganizations(orgList);
        }

        return superPersonsList;
    }

    @Override
    @Transactional
    public SuperPerson addSuperPerson(SuperPerson superPerson) {
        return spsDAO.addSuperPerson(superPerson);
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        spsDAO.updateSuperPerson(superPerson);
    }

    @Override
    @Transactional
    public void deleteSuperPersonById(int id) {
        spsDAO.deleteSuperPersonById(id);
    }

    @Override
    @Transactional
    public void createNewMember(int superId, int orgId) {
        spoDAO.Create(superId, orgId);
    }

    @Override
    public void deleteMember(int superId) {
        spoDAO.deleteSuperPersonById(superId);
    }

}