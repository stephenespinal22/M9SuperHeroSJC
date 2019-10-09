/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.daos;

import java.util.List;
import sg.sjc.dtos.SuperPersons;

/**
 *
 * @author corey
 */
public interface SuperPersonsDAO {
    
    SuperPersons getSuperPersonById(int id);
    List<SuperPersons> getAllSuperPersons();
    List<SuperPersons> getAllHeroes();
    List<SuperPersons> getAllVillains();
    SuperPersons addSuperPerson(SuperPersons superPerson);
    void updateSuperPerson(SuperPersons superPerson);
    void deleteSuperPersonById(int id);
    
}
