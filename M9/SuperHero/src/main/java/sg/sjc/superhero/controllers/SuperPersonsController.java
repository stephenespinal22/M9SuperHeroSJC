/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sg.sjc.superhero.dtos.Organization;
import sg.sjc.superhero.dtos.SuperPerson;
import sg.sjc.superhero.dtos.SuperPowers;
import sg.sjc.superhero.services.OrganizationService;
import sg.sjc.superhero.services.SuperPersonsService;
import sg.sjc.superhero.services.SuperPowersService;

/**
 *
 * @author corey
 */
@Controller
public class SuperPersonsController {

    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @Autowired
    SuperPersonsService service;

    @Autowired
    OrganizationService orgService;

    @Autowired
    SuperPowersService spService;

    @GetMapping("supers")
    public String displayHeroesVillains(Model model) {
        List<SuperPerson> superPersons = service.getAllSuperPersons();
        List<Organization> organizations = orgService.readAllOrganizations();
        List<SuperPowers> powers = spService.readAllSuperPowers();

        model.addAttribute("SuperPersons", superPersons);
        model.addAttribute("Powers", powers);
        model.addAttribute("Organizations", organizations);

        return "supers";
    }

    @PostMapping("addHeroVillain")
    public String addHeroVillain(HttpServletRequest request, @Valid SuperPerson superPerson, BindingResult result) {
        

        String[] powIds = request.getParameterValues("powers");
        String[] orgIds = request.getParameterValues("organizations");
        List<SuperPowers> powers = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();

        if (powIds != null) {
            for (String powId : powIds) {
                powers.add(spService.readSuperPowersById(Integer.parseInt(powId)));
            }
        }

        if (orgIds != null) {
            for (String orgId : orgIds) {
                organizations.add(orgService.readOrganizationById(Integer.parseInt(orgId)));
            }
        }

        superPerson.setName(request.getParameter("name"));
        superPerson.setDescription(request.getParameter("description"));
        superPerson.setIsVillain(Boolean.parseBoolean(request.getParameter("isVillain")));
        superPerson.setPowers(powers);
        superPerson.setOrganizations(organizations);

        service.addSuperPerson(superPerson);

        if (powIds != null) {

            for (String powId : powIds) {
                service.createSuperPower(superPerson.getSuperId(), Integer.parseInt(powId));
            }
        }

        if (orgIds != null) {

            for (String orgId : orgIds) {
                service.createNewMember(superPerson.getSuperId(), Integer.parseInt(orgId));
            }
        }
        return "redirect:/supers";
    }

    @PostMapping("editHeroVillain")
    public String editHeroVillain(HttpServletRequest request, @Valid SuperPerson editSuper, BindingResult result) {


        String[] orgIds = request.getParameterValues("organizations");
        String[] powIds = request.getParameterValues("powers");
        List<SuperPowers> powers = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();

        if (powIds != null) {
            for (String powId : powIds) {
                powers.add(spService.readSuperPowersById(Integer.parseInt(powId)));
            }
        }

        if (orgIds != null) {
            for (String orgId : orgIds) {
                organizations.add(orgService.readOrganizationById(Integer.parseInt(orgId)));
            }
        }

        SuperPerson editSuper = new SuperPerson();
        editSuper.setSuperId(Integer.parseInt(request.getParameter("superId")));
        editSuper.setName(request.getParameter("name"));
        editSuper.setDescription(request.getParameter("description"));
        editSuper.setIsVillain(Boolean.parseBoolean(request.getParameter("isVillain")));
        editSuper.setOrganizations(organizations);
        editSuper.setPowers(powers);

        service.deleteSuper(editSuper.getSuperId());
        service.deleteMember(editSuper.getSuperId());
        service.updateSuperPerson(editSuper);

        if (powIds != null) {
            for (String powId : powIds) {
                service.createSuperPower(editSuper.getSuperId(), Integer.parseInt(powId));
            }
        }

        if (orgIds != null) {

            for (String orgId : orgIds) {
                service.createNewMember(editSuper.getSuperId(), Integer.parseInt(orgId));
            }
        }

        return "redirect:/supers";
    }

    @GetMapping("deleteHeroVillain")
    public String deleteHeroVillain(HttpServletRequest request) {
        System.out.println(request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteSuper(id);
        service.deleteSuperPersonRelationshipSighting(id);
        service.deleteMember(id);
        service.deleteSuperPersonById(id);

        return "redirect:/supers";
    }

    @PostMapping("upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:supers";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            String pathString = path.toString();
            String fixedPath = pathString.substring(25);

            int superId = Integer.parseInt(request.getParameter("superId"));
            service.addImagePath(fixedPath, superId);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        return "redirect:/supers";
    }

}
