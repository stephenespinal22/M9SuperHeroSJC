/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author stephenespinal
 */
@Controller
public class SightingController {
    
     @GetMapping("sightings")
    public String loadPage(Model model) {
        //List<Sighting> sightingList = service.readAllLocations();

        model.addAttribute("sightingList", 1);

        return "sightings";
    }

}
