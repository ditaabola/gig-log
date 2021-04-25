package lv.dita.controllers;

import lv.dita.domain.Venue;
import lv.dita.model.VenueDTO;
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

    @GetMapping("/addVenue")
    public String showCreateForm(Model model) {
        Venue venue = new Venue();

        model.addAttribute(VENUE, venue);
        return "add-venue";
    }

    @PostMapping("/add-venue")
    public String createVenue (VenueDTO venueDTO, Model model) {

        venueService.createVenue(venueDTO);
        model.addAttribute(VENUE, venueService.findAllVenues());
        return "redirect:/venues";
    }

    @GetMapping("/updateVenue/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute(VENUE, venueService.findVenueById(id));
        return "update-venue";
    }

    @PostMapping("/update-venue/{id}")
    public String updateVenue(@PathVariable("id") Long id, VenueDTO venueDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            venueDTO.setId(id);
            return "update-venue";
        }
        venueService.updateVenue(id, venueDTO);
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
