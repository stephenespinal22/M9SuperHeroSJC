/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String displayHeroesVillains(Integer id, Model model) {
        List<SuperPerson> superPersons = service.getAllSuperPersons();
        List<Organization> organizations = orgService.readAllOrganizations();
        model.addAttribute("SuperPersons", superPersons);
        model.addAttribute("Organizations", organizations);
        return "supers";
    }
    
    
    @PostMapping("addHeroVillain")
    public String addHeroVillain(HttpServletRequest request, @Valid SuperPerson superPerson, BindingResult result) {
        
        String[] orgIds = request.getParameterValues("organizations");
        List<Organization> organizations = new ArrayList<>();
        
        for (String orgId : orgIds) {
            organizations.add(orgService.readOrganizationById(Integer.parseInt(orgId)));
        }
                
        superPerson.setName(request.getParameter("name"));
        superPerson.setDescription(request.getParameter("description"));
        superPerson.setIsVillain(Boolean.parseBoolean(request.getParameter("isVillain")));
        superPerson.setOrganizations(organizations);

        service.addSuperPerson(superPerson);
        
        for (String orgId : orgIds){
            service.createNewMember(superPerson.getSuperId(), Integer.parseInt(orgId));
        }
        
        return "redirect:/supers";
    }
    
    @PostMapping("editHeroVillain")
    public String editHeroVillain(HttpServletRequest request){
        
        String[] orgIds = request.getParameterValues("organizations");
        List<Organization> organizations = new ArrayList<>();
        
        for (String orgId : orgIds) {
            organizations.add(orgService.readOrganizationById(Integer.parseInt(orgId)));
        }
        
        SuperPerson editSuper = new SuperPerson();
        editSuper.setSuperId(Integer.parseInt(request.getParameter("superId")));
        editSuper.setName(request.getParameter("name"));
        editSuper.setDescription(request.getParameter("description"));
        editSuper.setIsVillain(Boolean.parseBoolean(request.getParameter("isVillain")));
        editSuper.setOrganizations(organizations);
        
        service.deleteMember(editSuper.getSuperId());
        service.updateSuperPerson(editSuper);
        for (String orgId : orgIds){
            service.createNewMember(editSuper.getSuperId(), Integer.parseInt(orgId));
        }
        
        return "redirect:/supers";
    }
    
    @GetMapping("deleteHeroVillain")
    public String deleteHeroVillain(HttpServletRequest request){
        System.out.println(request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteMember(id);
        service.deleteSuperPersonById(id);
    
        return "redirect:/supers";
    }
    
}
