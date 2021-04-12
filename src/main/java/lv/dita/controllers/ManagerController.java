package lv.dita.controllers;

import lv.dita.model.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController (ManagerService managerService) {
        this.managerService = managerService;
    }


    @RequestMapping("/managers")
    public String findAllManagers(Model model) {
        final List<Manager> managers = managerService.findAllManagers();

        model.addAttribute("managers", managers);
        return "list-managers";
    }

    @RequestMapping("/manager/{id}")
    public String findManagerById(@PathVariable("id") Long id, Model model) throws NotFoundException {

        final Manager manager = managerService.findManagerById(id);

        model.addAttribute("manager", manager);
        return "list-manager";
    }

    @GetMapping("/updateManager/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final Manager manager = managerService.findManagerById(id);

        model.addAttribute("manager", manager);
        return "update-manager";
    }

    @PostMapping(value = "/update-manager/{id}")
    public String updateManager(@PathVariable("id") Long id, Manager manager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            manager.setId(id);
            return "update-manager";
        }
        managerService.updateManager(manager);
        model.addAttribute("manager", managerService.findAllManagers());
        return "redirect:/managers";
    }

    @GetMapping("/addManager")
    public String showCreateForm(Manager manager) {
        return "add-manager";
    }

    @PostMapping("/add-manager")
    public String createManager (Manager manager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-manager";
        }
        managerService.createManager(manager);
        model.addAttribute("manager", managerService.findAllManagers());
        return "redirect:/managers";
    }


    @RequestMapping("/remove-manager/{id}")
    public String deleteManager(@PathVariable("id") Long id, Model model) {
        managerService.deleteManager(id);

        model.addAttribute("manager", managerService.findAllManagers());
        return "redirect:/managers";
    }



}
