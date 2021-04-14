package lv.dita.controllers;

import lv.dita.model.Manager;
import lv.dita.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ManagerController {

    private final ManagerService managerService;

    private static final String MANAGERS = "managers";
    private static final String MANAGER = "manager";

    @Autowired
    public ManagerController (ManagerService managerService) {
        this.managerService = managerService;
    }


    @GetMapping("/managers")
    public String findAllManagers(Model model) {

        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "list-managers";
    }

    @GetMapping("/manager/{id}")
    public String findManagerById(@PathVariable("id") Long id, Model model) {

        model.addAttribute(MANAGER, managerService.findManagerById(id));
        return "list-manager";

    }

    @GetMapping("/updateManager/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute(MANAGER, managerService.findManagerById(id));
        return "update-manager";
    }

    @PostMapping(value = "/update-manager/{id}")
    public String updateManager(@PathVariable("id") Long id, Manager manager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            manager.setId(id);
            return "update-manager";
        }
        managerService.updateManager(manager);
        model.addAttribute(MANAGER, managerService.findAllManagers());
        return "redirect:/managers";
    }

    @GetMapping("/addManager")
    public String showCreateForm(Model model) {
        Manager newManager = new Manager();

        model.addAttribute(MANAGER, newManager);
        return "add-manager";
    }

    @PostMapping("/add-manager")
    public String createManager (Manager manager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-manager";
        }
        managerService.createManager(manager);
        model.addAttribute(MANAGER, managerService.findAllManagers());
        return "redirect:/managers";
    }


    @GetMapping("/delete-manager/{id}")
    public String deleteManager(@PathVariable("id") Long id, Model model) {
        managerService.deleteManager(id);

        model.addAttribute(MANAGER, managerService.findAllManagers());
        return "redirect:/managers";
    }

}
