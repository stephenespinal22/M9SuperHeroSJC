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
public interface SPOrgDAO {
    
    void Create(int superId, int orgId);
    void Delete(int superId, int orgId);
    void deleteSuperPersonById(int superId);
    void deleteOrgById(int orgId);
    
}
