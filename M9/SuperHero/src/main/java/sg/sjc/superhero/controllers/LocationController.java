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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public String addLocation(HttpServletRequest request) {

        Location newLocation = new Location();

        newLocation.setName(request.getParameter("name"));
        newLocation.setDescription(request.getParameter("description"));
        newLocation.setAddress(request.getParameter("address"));
        newLocation.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        newLocation.setLongitude(Double.parseDouble(request.getParameter("longitude")));

        service.createLocation(newLocation);

        //tell spring to redirect user to mapping locations
        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteLocation(id);

        return "redirect:/locations";
    }

    @PostMapping("editLocation")
    public String editLocation(HttpServletRequest request) {

        Location locationToEdit = new Location();

        locationToEdit.setLocationId(Integer.parseInt(request.getParameter("locationId")));
        locationToEdit.setName(request.getParameter("name"));
        locationToEdit.setDescription(request.getParameter("description"));
        locationToEdit.setAddress(request.getParameter("address"));
        locationToEdit.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        locationToEdit.setLongitude(Double.parseDouble(request.getParameter("longitude")));

        service.updateLocation(locationToEdit);

        return "redirect:/locations";
    }

}
