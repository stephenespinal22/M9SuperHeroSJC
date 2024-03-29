/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.services;

import java.util.List;
import sg.sjc.superhero.dtos.SuperPerson;

/**
 *
 * @author corey
 */
public interface SuperPersonsService {
    SuperPerson getSuperPersonById(int id);
    List<SuperPerson> getAllSuperPersons();
    SuperPerson addSuperPerson(SuperPerson superPerson);
    void createNewMember(int superId, int orgId);
    void deleteMember(int superId);
    void updateSuperPerson(SuperPerson superPerson);
    void deleteSuperPersonById(int id);
    void createSuperPower(int superId, int powId);
    void deleteSuper(int superId);    
    void deleteSuperPersonRelationshipSighting(int superId);
    
    void addImagePath(String path,int id);
}
