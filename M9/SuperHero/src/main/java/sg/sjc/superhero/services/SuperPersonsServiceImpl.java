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
import sg.sjc.superhero.daos.SuperPersonsDAO;
import sg.sjc.superhero.dtos.SuperPerson;

@Service
public class SuperPersonsServiceImpl implements SuperPersonsService {
    
    private final SuperPersonsDAO spsDAO;
    
    @Autowired
    public SuperPersonsServiceImpl(SuperPersonsDAO spsDAO){
       this.spsDAO = spsDAO; 
    }

    @Override
    public SuperPerson getSuperPersonById(int id) {
        return spsDAO.getSuperPersonById(id);
    }

    @Override
    public List<SuperPerson> getAllSuperPersons() {
       return spsDAO.getAllSuperPersons();
    }

    @Override
    @Transactional
    public SuperPerson addSuperPerson(SuperPerson superPerson) {
        return spsDAO.addSuperPerson(superPerson);
    }

    @Override
    public void updateSuperPerson(SuperPerson superPerson) {
        spsDAO.updateSuperPerson(superPerson);
    }

    @Override
    @Transactional
    public void deleteSuperPersonById(int id) {
        spsDAO.deleteSuperPersonById(id);
    }
    
}
