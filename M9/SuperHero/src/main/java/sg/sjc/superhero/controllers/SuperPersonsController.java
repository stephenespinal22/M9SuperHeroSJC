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
import sg.sjc.superhero.dtos.Organization;
import sg.sjc.superhero.dtos.SuperPerson;
import sg.sjc.superhero.services.OrganizationService;
import sg.sjc.superhero.services.SuperPersonsService;

/**
 *
 * @author corey
 */

@Controller
public class SuperPersonsController {
    
    
    @Autowired
    SuperPersonsService service;
    
    @Autowired
    OrganizationService orgService;
    
    @GetMapping("supers")
    public String displayHeroesVillains(Model model) {
        List<SuperPerson> superPersons = service.getAllSuperPersons();
        List<Organization> organizations = orgService.readAllOrganizations();
        model.addAttribute("SuperPersons", superPersons);
        model.addAttribute("Organizations", organizations);
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
        
        service.addSuperPerson(superPerson);
        return "redirect:/supers";
    }
    
    @PostMapping("editHeroVillain")
    public String editHeroVillain(HttpServletRequest request){
        SuperPerson editSuper = new SuperPerson();
        editSuper.setSuperId(Integer.parseInt(request.getParameter("superId")));
        editSuper.setName(request.getParameter("name"));
        editSuper.setDescription(request.getParameter("description"));
        editSuper.setIsVillain(Boolean.parseBoolean(request.getParameter("isVillain")));
        
        service.updateSuperPerson(editSuper);
        
        return "redirect:/supers";
    }
    
    @GetMapping("deleteHeroVillain")
    public String deleteHeroVillain(HttpServletRequest request){
        System.out.println(request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteSuperPersonById(id);
        
        return "redirect:/supers";
    }
    
}
