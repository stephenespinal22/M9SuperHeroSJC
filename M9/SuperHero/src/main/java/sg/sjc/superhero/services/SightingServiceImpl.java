/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.sjc.superhero.daos.SightingDao;
import sg.sjc.superhero.daos.SightingsSuperPersonsBridgeDao;
import sg.sjc.superhero.daos.SuperPersonsDAO;
import sg.sjc.superhero.dtos.Sighting;
import sg.sjc.superhero.dtos.SuperPerson;

@Service
public class SightingServiceImpl implements SightingService {

    private SightingDao sightingDao;
    private SuperPersonsDAO superPersonDAO;
    private SightingsSuperPersonsBridgeDao sightingSuperDao;

    @Autowired
    public SightingServiceImpl(SightingDao sightingDao, SuperPersonsDAO superPersonDAO, SightingsSuperPersonsBridgeDao sightingSuperDao) {
        this.sightingDao = sightingDao;
        this.superPersonDAO = superPersonDAO;
        this.sightingSuperDao = sightingSuperDao;
    }

    @Override
    @Transactional
    public Sighting createSighting(Sighting sighting) {
        return sightingDao.createSighting(sighting);
    }

    @Override
    public List<Sighting> readAllSightings() {
        List<Sighting> sightingList = sightingDao.readAllSightings();

        List<SuperPerson> superPersonList = new ArrayList<SuperPerson>();

        for (Sighting sighting : sightingList) {
            superPersonList = superPersonDAO.getSuperPersonsBySightingId(sighting.getSightingId());
            sighting.setSuperPersons(superPersonList);
        }

        return sightingList;
    }

    @Override
    public Sighting readSightingById(int id) {
        return sightingDao.readSightingById(id);
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    @Transactional
    public void deleteSighting(int id) {
        sightingDao.deleteSighting(id);
    }

    @Override
    @Transactional
    public void createNewRelationShip(int sightingId, int superId) {
        sightingSuperDao.createRelationShip(sightingId, superId);
    }

    @Override
    @Transactional
    public void deleteSightingFromRelationship(int sightingId) {
        sightingSuperDao.deleteSightingById(sightingId);
    }

}
