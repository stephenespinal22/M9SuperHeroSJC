/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

/**
 *
 * @author corey
 */
public interface SpPowersDAO {
    
    void Create(int superId, int powId);
    void Delete(int superId, int powId);
    void deleteSuperPersonById(int superId);
    void deletePowerById(int powId);
}
