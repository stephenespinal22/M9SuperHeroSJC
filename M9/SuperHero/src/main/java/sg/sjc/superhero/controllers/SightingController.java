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
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
public class SightingController {

    private SightingService sightingService;
    private LocationService locationService;
    private SuperPersonsService superPersonService;

    @Autowired
    public SightingController(SightingService sightingService, LocationService locationService, SuperPersonsService superPersonService) {
        this.sightingService = sightingService;
        this.locationService = locationService;
        this.superPersonService = superPersonService;
    }

    @GetMapping("sightings")
    public String loadPage(Model model) {
        List<Sighting> sightingList = sightingService.readAllSightings();
        List<Location> locationList = locationService.readAllLocations();
        List<SuperPerson> superPersonList = superPersonService.getAllSuperPersons();
        
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superPersonList", superPersonList);

        return "sightings";
    }

    @PostMapping("addNewSighting")
    @Transactional
    public String addSighting(HttpServletRequest request) {
        
        String[] superPersonsIds = request.getParameterValues("superPersons");

        List<SuperPerson> superPersonsInSightingList = new ArrayList<SuperPerson>();

        for (String superPersonId : superPersonsIds) {
            superPersonsInSightingList.add(superPersonService.getSuperPersonById(Integer.parseInt(superPersonId)));
        }
        
        Sighting newSighting = new Sighting();

        newSighting.setDescription(request.getParameter("description"));
        newSighting.setSightingDate(request.getParameter("dateTime"));
        newSighting.setLocation(locationService.readLocationById(Integer.parseInt(request.getParameter("location"))));
        newSighting.setSuperPersons(superPersonsInSightingList);
        
        sightingService.createSighting(newSighting);

        //tell spring to redirect user to mapping locations
        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        sightingService.deleteSighting(id);

        return "redirect:/sightings";
    }

    @PostMapping("editSighting")
    public String editSighting(HttpServletRequest request) {

        Sighting sightingToEdit = new Sighting();

        sightingToEdit.setSightingId(Integer.parseInt(request.getParameter("sightingId")));
        sightingToEdit.setDescription(request.getParameter("description"));
        sightingToEdit.setSightingDate(request.getParameter("dateTime"));
        sightingToEdit.setLocation(locationService.readLocationById(Integer.parseInt(request.getParameter("location"))));

        sightingService.updateSighting(sightingToEdit);

        return "redirect:/sightings";
    }

}
