package lv.dita.controllers;

import lv.dita.domain.Venue;
import lv.dita.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class VenueController {

    private final VenueService venueService;

    private static final String VENUE = "venue";
    private static final String VENUES = "venues";

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/venues")
    public String findAllVenues(Model model) {

        model.addAttribute(VENUES, venueService.findAllVenues());
        return "list-venues";
    }

    @GetMapping("/venue/{id}")
    public String findVenueById(@PathVariable("id") Long id, Model model) {

        model.addAttribute(VENUE, venueService.findVenueById(id));
        return "list-venue";
    }

    @GetMapping("/updateVenue/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute(VENUE, venueService.findVenueById(id));
        return "update-venue";
    }

    @PostMapping("/update-venue/{id}")
    public String updateVenue(@PathVariable("id") Long id, Venue venue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            venue.setId(id);
            return "update-venue";
        }
        venueService.updateVenue(venue);
        model.addAttribute(VENUE, venueService.findAllVenues());
        return "redirect:/venues";
    }

    @GetMapping("/addVenue")
    public String showCreateForm(Model model) {
        Venue newVenue = new Venue();

        model.addAttribute(VENUE, newVenue);

        return "add-venue";
    }

    @PostMapping("/add-venue")
    public String createVenue (Venue venue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-venue";
        }
        venueService.createVenue(venue);
        model.addAttribute(VENUE, venueService.findAllVenues());
        return "redirect:/venues";
    }


    @GetMapping("/delete-venue/{id}")
    public String deleteVenue(@PathVariable("id") Long id, Model model) {
        venueService.deleteVenue(id);

        model.addAttribute(VENUE, venueService.findAllVenues());
        return "redirect:/venues";
    }
}
