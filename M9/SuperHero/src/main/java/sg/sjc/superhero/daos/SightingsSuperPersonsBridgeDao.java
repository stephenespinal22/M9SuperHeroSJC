/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

/**
 *
 * @author stephenespinal
 */
public interface SightingsSuperPersonsBridgeDao {
    void createRelationShip(int sightingId, int superId);
    void deleteRelationShip(int sightingId, int superId);
    void deleteSightingById(int sightingId);
    void deleteSuperPersonById(int superId);
}
