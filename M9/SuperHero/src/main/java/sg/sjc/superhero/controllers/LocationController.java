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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.sjc.superhero.dtos.Location;
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

    @GetMapping("locations")
    public String loadPage(Model model) {
        List<Location> locationList = service.readAllLocations();

        model.addAttribute("locationList", locationList);

        return "locations";
    }

    @PostMapping("addNewLocation")
    public String testForm(HttpServletRequest request) {
        
        Location newLocation = new Location();
        
        newLocation.setName(request.getParameter("name"));
        newLocation.setDescription(request.getParameter("description"));
        newLocation.setAddress(request.getParameter("address"));
        
        service.createLocation(newLocation);

        //tell spring to redirect user to mapping locations
        return "redirect:/locations";
    }

}
