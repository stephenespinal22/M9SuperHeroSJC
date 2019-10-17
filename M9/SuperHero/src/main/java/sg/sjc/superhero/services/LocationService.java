/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import sg.sjc.superhero.dtos.Location;

/**
 *
 * @author stephenespinal
 */
public interface LocationService {

    Location createLocation(Location location);

    List<Location> readAllLocations();

    Location readLocationById(int id);

    void updateLocation(Location location);

    void deleteLocation(int id);
}
