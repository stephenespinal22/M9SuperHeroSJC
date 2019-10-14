/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sg.sjc.superhero.dtos.Sighting;
import sg.sjc.superhero.services.SightingService;

/**
 *
 * @author stephenespinal
 */
@Controller
public class SightingController {
    
    SightingService service;

    @Autowired
    public SightingController(SightingService service) {
        this.service = service;
    }
    
     @GetMapping("sightings")
    public String loadPage(Model model) {
        List<Sighting> sightingList = service.readAllSightings();

        model.addAttribute("sightingList", sightingList);

        return "sightings";
    }

}
