package lv.dita.controllers;

import lv.dita.model.Venue;
import lv.dita.exception.NotFoundException;
import lv.dita.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/venues")
    public String findAllVenues(Model model) {
        final List<Venue> venues = venueService.findAllVenues();

        model.addAttribute("venues", venues);
        return "list-venues";
    }

    @GetMapping("/venue/{id}")
    public String findVenue(@PathVariable("id") Long id, Model model) throws NotFoundException {

        final Venue venue = venueService.findVenueById(id);

        model.addAttribute("venue", venue);
        return "list-venue";
    }

    @GetMapping("/updateVenue/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final Venue venue = venueService.findVenueById(id);

        model.addAttribute("venue", venue);
        return "update-venue";
    }

    @PostMapping("/update-venue/{id}")
    public String updateVenue(@PathVariable("id") Long id, Venue venue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            venue.setId(id);
            return "update-venue";
        }
        venueService.updateVenue(venue);
        model.addAttribute("venue", venueService.findAllVenues());
        return "redirect:/venues";
    }

    @GetMapping("/addVenue")
    public String showCreateForm(Venue venue) {
        return "add-venue";
    }

    @PostMapping("/add-venue")
    public String createVenue (Venue venue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-venue";
        }
        venueService.createVenue(venue);
        model.addAttribute("venue", venueService.findAllVenues());
        return "redirect:/venues";
    }


    @GetMapping("/delete-venue/{id}")
    public String deleteVenue(@PathVariable("id") Long id, Model model) {
        venueService.deleteVenue(id);

        model.addAttribute("venue", venueService.findAllVenues());
        return "redirect:/venues";
    }
}
