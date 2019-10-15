/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import sg.sjc.superhero.dtos.Sighting;

/**
 *
 * @author stephenespinal
 */
public interface SightingService {

    Sighting createSighting(Sighting sighting);

    List<Sighting> readAllSightings();

    Sighting readSightingById(int id);

    void updateSighting(Sighting sighting);

    void deleteSighting(int id);
}
