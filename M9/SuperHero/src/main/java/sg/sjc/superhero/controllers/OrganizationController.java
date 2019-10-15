/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sg.sjc.superhero.dtos.Organization;

/**
 *
 * @author stephenespinal
 */
@Controller
public class OrganizationController {

    @GetMapping("organizations")
    public String loadPage(Model model) {
        //List<Organization> organizationList = service.readAllOrganizations();

        model.addAttribute("organizationList", 1);

        return "organizations";
    }
}
