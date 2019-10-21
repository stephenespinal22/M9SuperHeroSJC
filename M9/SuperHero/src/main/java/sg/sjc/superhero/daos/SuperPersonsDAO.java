/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.util.List;
import sg.sjc.superhero.dtos.Organization;
import sg.sjc.superhero.dtos.SuperPerson;

/**
 *
 * @author corey
 */
public interface SuperPersonsDAO {
    
    SuperPerson getSuperPersonById(int id);
    List<SuperPerson> getAllSuperPersons();
    SuperPerson addSuperPerson(SuperPerson superPerson);
    void updateSuperPerson(SuperPerson superPerson);
    void deleteSuperPersonById(int id);
    
}
