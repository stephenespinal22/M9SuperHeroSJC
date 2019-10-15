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
import sg.sjc.superhero.services.OrganizationService;

/**
 *
 * @author stephenespinal
 */
@Controller
public class OrganizationController {

    private OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping("organizations")
    public String loadPage(Model model) {
        List<Organization> organizationList = service.readAllOrganizations();

        model.addAttribute("organizationList", organizationList);

        return "organizations";
    }

    @PostMapping("addNewOrganization")
    public String testForm(HttpServletRequest request) {

        Organization newOrganization = new Organization();

        newOrganization.setName(request.getParameter("name"));
        newOrganization.setDescription(request.getParameter("description"));
        newOrganization.setContactInfo(request.getParameter("contactInfo"));

        service.createOrganization(newOrganization);

        //tell spring to redirect user to mapping locations
        return "redirect:/organizations";
    }

    @GetMapping("deleteOrganization")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteOrganization(id);

        return "redirect:/organizations";
    }

    @PostMapping("editOrganization")
    public String editLocation(HttpServletRequest request) {

        Organization organizationToEdit = new Organization();
        
        organizationToEdit.setOrgId(Integer.parseInt(request.getParameter("orgId")));
        organizationToEdit.setName(request.getParameter("name"));
        organizationToEdit.setDescription(request.getParameter("description"));
        organizationToEdit.setContactInfo(request.getParameter("contactInfo"));

        service.updateOrganization(organizationToEdit);

        return "redirect:/organizations";
    }
}
