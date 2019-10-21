/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sjc.superhero.dtos.SuperPowers;
import sg.sjc.superhero.daos.SuperPowersDao;

/**
 *
 * @author jhoan
 */



@Service
public class SuperPowersServiceImpl implements SuperPowersService {

    SuperPowersDao superPowersDao;

    @Autowired
    public SuperPowersServiceImpl(SuperPowersDao superPowersDao) {
        this.superPowersDao = superPowersDao;
    }
    
    @Override
    public SuperPowers createSuperPowers(SuperPowers superPowers) {
        return superPowersDao.createSuperPowers(superPowers);
    }

    @Override
    public List<SuperPowers> readAllSuperPowers() {
        return superPowersDao.readAllSuperPowers();
    }

    @Override
    public SuperPowers readSuperPowersById(int id) {
        return superPowersDao.readSuperPowersById(id);
    }

    @Override
    public void updateSuperPowers(SuperPowers superPowers) {
        superPowersDao.updateSuperPowers(superPowers);
    }

    @Override
    public void deleteSuperPowers(int id) {
        superPowersDao.deleteSuperPowers(id);
    }

}