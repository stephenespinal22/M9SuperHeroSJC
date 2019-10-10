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
import sg.sjc.superhero.daos.SuperPersonsDAO;
import sg.sjc.superhero.dtos.SuperPerson;

/**
 *
 * @author corey
 */

@Controller
public class SuperPersonsController {
    
    
    @Autowired
    SuperPersonsDAO spsDAO;
    
    @GetMapping("supers")
    public String displayHeroesVillains(Model model) {
        List<SuperPerson> superPersons = spsDAO.getAllSuperPersons();
        model.addAttribute("supers", superPersons);
        return "supers";
    }
    
}
