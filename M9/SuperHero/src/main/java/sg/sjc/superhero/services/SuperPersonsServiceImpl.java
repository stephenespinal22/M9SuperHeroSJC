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
import sg.sjc.superhero.daos.SpPowersDAO;
import sg.sjc.superhero.daos.SightingDao;
import sg.sjc.superhero.daos.SightingsSuperPersonsBridgeDao;
import sg.sjc.superhero.daos.SuperPersonsDAO;
import sg.sjc.superhero.daos.SuperPowersDao;
import sg.sjc.superhero.dtos.Organization;
import sg.sjc.superhero.dtos.Sighting;
import sg.sjc.superhero.dtos.SuperPerson;
import sg.sjc.superhero.dtos.SuperPowers;

@Service
public class SuperPersonsServiceImpl implements SuperPersonsService {

    private final SuperPersonsDAO spsDAO;
    private final SPOrgDAO spoDAO;
    private final OrganizationDao orgDAO;
    private SightingsSuperPersonsBridgeDao sightingSuperDao;
    private SightingDao sightingDao;
    private final SuperPowersDao spDAO;
    private final SpPowersDAO sppDAO;

    @Autowired
    public SuperPersonsServiceImpl(SuperPersonsDAO spsDAO, SPOrgDAO spoDAO, OrganizationDao orgDAO, SightingsSuperPersonsBridgeDao sightingSuperDao,
    SightingDao sightingDao) {
        this.spsDAO = spsDAO;
        this.spoDAO = spoDAO;
        this.orgDAO = orgDAO;
        this.sightingSuperDao = sightingSuperDao;
        this.sightingDao = sightingDao;
        this.spDAO = spDAO;
        this.sppDAO = sppDAO;
    }

    @Override
    public SuperPerson getSuperPersonById(int id) {
        return spsDAO.getSuperPersonById(id);
    }

    @Override
    public List<SuperPerson> getAllSuperPersons() {

        List<SuperPerson> superPersonsList = spsDAO.getAllSuperPersons();
        
        List<SuperPowers> superPowersList = new ArrayList<SuperPowers>();
        
        List<Organization> orgList = new ArrayList<Organization>();

        for (SuperPerson superPerson : superPersonsList) {
            orgList = orgDAO.getOrganizationsBySuperPersonId(superPerson.getSuperId());
            superPowersList = spDAO.getAllPowersBySuperId(superPerson.getSuperId());
            superPerson.setPowers(superPowersList);
            superPerson.setOrganizations(orgList);
        }
        
        List<Sighting> sightingList = new ArrayList<Sighting>();

        for (SuperPerson superPerson : superPersonsList) {
            sightingList = sightingDao.getSightingsBySuperPersonId(superPerson.getSuperId());
            superPerson.setSightings(sightingList);
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
    
    @Override
    @Transactional
    public void deleteSuperPersonRelationshipSighting(int superId) {
        sightingSuperDao.deleteSuperPersonById(superId);
    }

    @Override
    public void createSuperPower(int superId, int powId) {
       sppDAO.Create(superId, powId);
    }

    @Override
    public void deleteSuper(int superId) {
        sppDAO.deleteSuperPersonById(superId);
    }

}
