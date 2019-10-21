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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sjc.superhero.daos.SightingDao;
import sg.sjc.superhero.dtos.Sighting;

@Service
public class SightingServiceImpl implements SightingService {

    SightingDao sightingDao;

    @Autowired
    public SightingServiceImpl(SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        return sightingDao.createSighting(sighting);
    }

    @Override
    public List<Sighting> readAllSightings() {
        return sightingDao.readAllSightings();
    }

    @Override
    public Sighting readSightingById(int id) {
        return sightingDao.readSightingById(id);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public void deleteSighting(int id) {
        sightingDao.deleteSighting(id);
    }

}
