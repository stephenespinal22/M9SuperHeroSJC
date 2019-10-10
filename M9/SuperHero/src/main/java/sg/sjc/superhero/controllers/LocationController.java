/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sg.sjc.superhero.services.LocationService;

/**
 *
 * @author stephenespinal
 */
@Controller
public class LocationController {

    private LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }
}
