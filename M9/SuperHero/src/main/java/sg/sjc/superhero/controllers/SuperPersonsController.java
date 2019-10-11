/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import static java.lang.System.console;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("SuperPersons", superPersons);
        return "supers";
    }
    
    @PostMapping("addHeroVillain")
    public String addHeroVillain(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String isVillain = request.getParameter("isVillain");
        
        SuperPerson superPerson = new SuperPerson();
        superPerson.setName(name);
        superPerson.setDescription(description);
        superPerson.setIsVillain(Boolean.parseBoolean(isVillain));
        
        spsDAO.addSuperPerson(superPerson);
        return "redirect:/supers";
    }
    
}
