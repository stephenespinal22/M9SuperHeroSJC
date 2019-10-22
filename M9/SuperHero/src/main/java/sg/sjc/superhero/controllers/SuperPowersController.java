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
import org.springframework.web.bind.annotation.RequestMapping;
import sg.sjc.superhero.dtos.SuperPowers;
import sg.sjc.superhero.services.SuperPowersService;

/**
 *
 * @author jhoan
 */
    @Controller
public class SuperPowersController {

    private SuperPowersService service;

    @Autowired
    public SuperPowersController(SuperPowersService service) {
        this.service = service;
    }

    @GetMapping("powers")

    public String loadPage(Model model) {
        List<SuperPowers> superPowersList = service.readAllSuperPowers();

        model.addAttribute("superPowersList", superPowersList);

        return "powers";

    }

    @PostMapping("addNewSuperPower")
    public String addSuperPowers(HttpServletRequest request) {

        SuperPowers newSuperPowers = new SuperPowers();

        newSuperPowers.setName(request.getParameter("powerName"));

        service.createSuperPowers(newSuperPowers);

        //tell spring to redirect user to mapping locations
        return "redirect:/powers";

    }

    @GetMapping("deleteSuperPowers")
    public String deleteSuperPowers(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deletePowerById(id);
        service.deleteSuperPowers(id);

        return "redirect:/powers";

    }

    @PostMapping("editSuperPower")
    public String editSuperPowers(HttpServletRequest request) {

        SuperPowers superPowersToEdit = new SuperPowers();

        superPowersToEdit.setSpwId(Integer.parseInt(request.getParameter("powId")));
        superPowersToEdit.setName(request.getParameter("powerName"));

        service.updateSuperPowers(superPowersToEdit);

        return "redirect:/powers";

    }
    
}