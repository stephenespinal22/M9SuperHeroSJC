/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.sjc.superhero.daos.LocationDao;
import sg.sjc.superhero.daos.SightingDao;
import sg.sjc.superhero.daos.SightingsSuperPersonsBridgeDao;
import sg.sjc.superhero.daos.SuperPersonsDAO;
import sg.sjc.superhero.dtos.Location;
import sg.sjc.superhero.dtos.Sighting;
import sg.sjc.superhero.dtos.SuperPerson;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationDao locationDao;
    private SightingsSuperPersonsBridgeDao sightingSuperDao;
    private SightingDao sightingDao;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao, SightingsSuperPersonsBridgeDao sightingSuperDao, SightingDao sightingDao) {
        this.locationDao = locationDao;
        this.sightingSuperDao = sightingSuperDao;
        this.sightingDao = sightingDao;
    }

    @Override
    public Location createLocation(Location location) {
        return locationDao.createLocation(location);
    }

    @Override
    public List<Location> readAllLocations() {
        return locationDao.readAllLocations();
    }

    @Override
    public Location readLocationById(int id) {
        return locationDao.readLocationById(id);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    @Transactional
    public void deleteLocation(int id) {
        
        List<Sighting> sightingList = sightingDao.readSightingsByLocationId(id);

        for (Sighting sighting : sightingList) {
            sightingSuperDao.deleteSightingById(sighting.getSightingId());
//            if (sighting.getSuperPersons() != null) {
//                for (SuperPerson supers : sighting.getSuperPersons()) {
//                    sightingSuperDao.deleteSightingById(supers.getSuperId());
//                }
//            }
        }
        locationDao.deleteLocation(id);
    }

}
