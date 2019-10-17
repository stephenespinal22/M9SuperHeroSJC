/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.util.List;
import sg.sjc.superhero.dtos.Sighting;

/**
 *
 * @author stephenespinal
 */
public interface SightingDao {

    Sighting createSighting(Sighting sighting);

    List<Sighting> readAllSightings();

    Sighting readSightingById(int id);

    //one to many reads
    List<Sighting> readSightingsByLocationId(int locationId);

    void updateSighting(Sighting sighting);

    void deleteSighting(int id);

}
