/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.util.List;
import sg.sjc.superhero.dtos.Location;

/**
 *
 * @author stephenespinal
 */
public interface LocationDao {
    
        Location createGame(Location game);

    List<Location> readAllGames();

    Location readGameById(int id);

    void updateGame(Location game);

    void deleteAllGames();
}
