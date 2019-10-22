/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.sjc.superhero.dtos.Location;
import sg.sjc.superhero.dtos.Sighting;
import sg.sjc.superhero.dtos.SuperPerson;
import sg.sjc.superhero.services.LocationService;
import sg.sjc.superhero.services.SightingService;
import sg.sjc.superhero.services.SuperPersonsService;

/**
 *
 * @author stephenespinal
 */
/**
 *
 * @author stephenespinal
 */
@Controller
public class HomeController {

    private SightingService sightingService;
    private LocationService locationService;
    private SuperPersonsService superPersonService;

    @Autowired
    public HomeController(SightingService sightingService, LocationService locationService, SuperPersonsService superPersonService) {
        this.sightingService = sightingService;
        this.locationService = locationService;
        this.superPersonService = superPersonService;
    }

    @GetMapping("home")
    public String loadPage(Model model) {
        List<Sighting> sightingList = sightingService.readAllSightings();
        List<Location> locationList = locationService.readAllLocations();
        List<SuperPerson> superPersonList = superPersonService.getAllSuperPersons();

        model.addAttribute("sightingList", sightingList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superPersonList", superPersonList);

        return "home";
    }

}
