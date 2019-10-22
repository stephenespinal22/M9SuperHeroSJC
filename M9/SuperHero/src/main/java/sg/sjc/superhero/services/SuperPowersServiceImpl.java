/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sjc.superhero.daos.SpPowersDAO;
import sg.sjc.superhero.daos.SuperPersonsDAO;
import sg.sjc.superhero.dtos.SuperPowers;
import sg.sjc.superhero.daos.SuperPowersDao;
import sg.sjc.superhero.dtos.SuperPerson;

/**
 *
 * @author jhoan
 */
@Service
public class SuperPowersServiceImpl implements SuperPowersService {

    SuperPowersDao superPowersDao;
    SpPowersDAO sppDAO;
    SuperPersonsDAO superPersonDao;

    @Autowired
    public SuperPowersServiceImpl(SuperPowersDao superPowersDao, SpPowersDAO sppDAO,SuperPersonsDAO superPersonDao) {
        this.superPowersDao = superPowersDao;
        this.sppDAO = sppDAO;
        this.superPersonDao = superPersonDao;
    }

    @Override
    public SuperPowers createSuperPowers(SuperPowers superPowers) {
        return superPowersDao.createSuperPowers(superPowers);
    }

    @Override
    public List<SuperPowers> readAllSuperPowers() {

        List<SuperPowers> superPowersList = superPowersDao.readAllSuperPowers();
        
        List<SuperPerson> superList = new ArrayList<SuperPerson>();

        for (SuperPowers superPower : superPowersList) {
            superList = superPersonDao.getAllSuperPersonsByPowerId(superPower.getSpwId());
            superPower.setSuperPersons(superList);
        }

        return superPowersList;
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

    @Override
    public void deletePowerById(int id) {
        sppDAO.deletePowerById(id);
    }

}
