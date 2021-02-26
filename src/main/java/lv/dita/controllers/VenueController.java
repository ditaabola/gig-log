package lv.dita.controllers;

import lv.dita.models.Venue;
import lv.dita.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@Controller
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @RequestMapping("/venues")
    public String findAllVenues(Model model) {
        final List<Venue> venues = venueService.findAllVenues();

        model.addAttribute("venues", venues);
        return "list-venues";
    }

    @RequestMapping("/venue/{id}")
    public String findVenueById(@PathVariable("id") Long id, Model model) {
        final Optional<Venue> venue = venueService.findVenueById(id);

        model.addAttribute("venue", venue);
        return "list-venue";
    }

    @GetMapping("/addVenue")
    public String showCreateForm(Venue venue) {
        return "add-venue";
    }

    @RequestMapping("/add-venue")
    public String createVenue(Venue venue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-venue";
        }

        venueService.createVenue(venue);
        model.addAttribute("venue", venueService.findAllVenues());
        return "redirect:/venues";
    }

    @GetMapping("/updateVenue/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Optional<Venue> venue = venueService.findVenueById(id);

        model.addAttribute("venue", venue);
        return "update-venue";
    }

    @RequestMapping("/update-venue/{id}")
    public String updateVenue(@PathVariable("id") Long id, Venue venue, BindingResult result, Model model) {
        if (result.hasErrors()) {
            venue.setId(id);
            return "update-venue";
        }

        venueService.updateVenue(venue);
        model.addAttribute("venue", venueService.findAllVenues());
        return "redirect:/venues";
    }

    @RequestMapping("/remove-venue/{id}")
    public String deleteVenue(@PathVariable("id") Long id, Model model) {
        venueService.deleteVenue(id);

        model.addAttribute("venue", venueService.findAllVenues());
        return "redirect:/venues";
    }
}
