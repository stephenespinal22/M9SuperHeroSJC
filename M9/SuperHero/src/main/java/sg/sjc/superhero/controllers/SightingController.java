/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

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
import sg.sjc.superhero.services.LocationService;
import sg.sjc.superhero.services.SightingService;

/**
 *
 * @author stephenespinal
 */
@Controller
public class SightingController {

    private SightingService sightingService;
    private LocationService locationService;

    @Autowired
    public SightingController(SightingService sightingService, LocationService locationService) {
        this.sightingService = sightingService;
        this.locationService = locationService;
    }

    @GetMapping("sightings")
    public String loadPage(Model model) {
        List<Sighting> sightingList = sightingService.readAllSightings();
        List<Location> locationList = locationService.readAllLocations();

        model.addAttribute("sightingList", sightingList);
        model.addAttribute("locationList", locationList);

        return "sightings";
    }

    @PostMapping("addNewSighting")
    @Transactional
    public String addSighting(HttpServletRequest request) {

        Sighting newSighting = new Sighting();

        newSighting.setDescription(request.getParameter("description"));
        newSighting.setSightingDate(request.getParameter("dateTime"));
        newSighting.setLocation(locationService.readLocationById(Integer.parseInt(request.getParameter("location"))));

        //System.out.println(request.getParameterValues("superPersons"));

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

    //NAVIGATION BAR METHODS
    @RequestMapping("/locations")
    public String directToLocations() {
        return "locations";
    }

    @RequestMapping("/organizations")
    public String directToOrganizations() {
        return "organizations";
    }

    @RequestMapping("/sightings")
    public String directToSightings() {
        return "sightings";
    }

    @RequestMapping("/supers")
    public String directToSupers() {
        return "supers";
    }
}
