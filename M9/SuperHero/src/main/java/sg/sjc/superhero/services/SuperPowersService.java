/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import sg.sjc.superhero.dtos.SuperPowers;

/**
 *
 * @author jhoan
 */
public interface SuperPowersService {

    SuperPowers createSuperPowers(SuperPowers superPowers);

    List<SuperPowers> readAllSuperPowers();

    SuperPowers readSuperPowersById(int id);

    void updateSuperPowers(SuperPowers superPowers);

    void deleteSuperPowers(int id);

}