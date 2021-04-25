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

    @RequestMapping("/venues")
    public String findAllVenues(Model model) {

        model.addAttribute(VENUES, venueService.findAllVenues());
        return "list-venues";
    }

    @RequestMapping("/venue/{id}")
    public String findVenueById(@PathVariable("id") Long id, Model model) {

        model.addAttribute(VENUE, venueService.findVenueById(id));
        return "list-venue";
    }

    @RequestMapping("/addVenue")
    public String showCreateForm(Model model) {
        Venue venue = new Venue();

        model.addAttribute(VENUE, venue);
        return "add-venue";
    }

    @RequestMapping("/add-venue")
    public String createVenue (Venue venue, Model model) {

        venueService.createVenue(venue);
        model.addAttribute(VENUE, venueService.findAllVenues());
        return "redirect:/venues";
    }

    @RequestMapping("/updateVenue/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute(VENUE, venueService.findVenueById(id));
        return "update-venue";
    }

    @RequestMapping("/update-venue/{id}")
    public String updateVenue(@PathVariable("id") Long id, Venue venue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            venue.setId(id);
            return "update-venue";
        }
        venueService.updateVenue(venue);
        model.addAttribute(VENUE, venueService.findAllVenues());
        return "redirect:/venues";
    }


    @RequestMapping("/delete-venue/{id}")
    public String deleteVenue(@PathVariable("id") Long id, Model model) {
        venueService.deleteVenue(id);

        model.addAttribute(VENUE, venueService.findAllVenues());
        return "redirect:/venues";
    }
}
