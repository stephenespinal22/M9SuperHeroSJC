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

    @PostMapping("addNewSighting")
    public String addSighting(HttpServletRequest request) {

        Sighting newSighting = new Sighting();

        newSighting.setDescription(request.getParameter("description"));
        newSighting.setSightingDate(request.getParameter("dateTime"));

        service.createSighting(newSighting);

        //tell spring to redirect user to mapping locations
        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteSighting(id);

        return "redirect:/sightings";
    }

    @PostMapping("editSighting")
    public String editSighting(HttpServletRequest request) {

        Sighting sightingToEdit = new Sighting();

        sightingToEdit.setSightingId(Integer.parseInt(request.getParameter("sightingId")));
        sightingToEdit.setDescription(request.getParameter("description"));
        sightingToEdit.setSightingDate(request.getParameter("dateTime"));


        service.updateSighting(sightingToEdit);

        return "redirect:/sightings";
    }
}
