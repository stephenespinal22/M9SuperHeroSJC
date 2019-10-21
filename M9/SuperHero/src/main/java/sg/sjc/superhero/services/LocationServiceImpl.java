/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sjc.superhero.daos.LocationDao;
import sg.sjc.superhero.dtos.Location;

@Service
public class LocationServiceImpl implements LocationService {

    LocationDao locationDao;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
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
    public void deleteLocation(int id) {
        locationDao.deleteLocation(id);
    }

}
