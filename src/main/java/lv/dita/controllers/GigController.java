package lv.dita.controllers;

import lv.dita.model.Artist;
import lv.dita.model.Gig;
import lv.dita.model.Venue;
import lv.dita.exception.NotFoundException;
import lv.dita.service.ArtistService;
import lv.dita.service.GigService;
import lv.dita.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class GigController {

    private final GigService gigService;
    private final VenueService venueService;
    private final ArtistService artistService;

    @Autowired
    public GigController(GigService gigService, VenueService venueService, ArtistService artistService) {
        this.gigService = gigService;
        this.venueService = venueService;
        this.artistService = artistService;
    }

    @GetMapping("/gigs")
    public String findAllGigs(Model model) {
        final List<Gig> gigs = gigService.findAllGigs();
        final List<Venue> venues = venueService.findAllVenues();
        final List<Artist> artists = artistService.findAllArtists();

        model.addAttribute("gigs", gigs);
        model.addAttribute("venues", venues);
        model.addAttribute("artists", artists);
        return "list-gigs";
    }

    @GetMapping("/gig/{id}")
    public String findGigById(@PathVariable("id") Long id, Model model) throws NotFoundException {

        final Gig gig = gigService.findGigById(id);

        model.addAttribute("gig", gig);
        return "list-gig";
    }


    @GetMapping("/addGig")
    public String showCreateForm(Gig gig, Model model) {
        model.addAttribute("venues", venueService.findAllVenues());
        model.addAttribute("artists", artistService.findAllArtists());

        return "add-gig";
    }

    @PostMapping(value="/add-gig")
    public String createGig (Gig gig, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-gig";
        }
        gigService.createGig(gig);
        model.addAttribute("gig", gigService.findAllGigs());
        return "redirect:/gigs";
    }


    @GetMapping(value="/updateGig/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final Gig gig = gigService.findGigById(id);

        model.addAttribute("gig", gig);

        model.addAttribute("venues", venueService.findAllVenues());
        model.addAttribute("artists", artistService.findAllArtists());
        return "update-gig";
    }

    @PostMapping(value="/update-gig/{id}")
    public String updateGig(@PathVariable("id") Long id, Gig gig, BindingResult result, Model model) {
        if (result.hasErrors()) {
            gig.setId(id);
            return "update-gig";
        }
        gigService.updateGig(gig);
        model.addAttribute("gig", gigService.findAllGigs());
        return "redirect:/gigs";
    }



    @GetMapping("/delete-gig/{id}")
    public String deleteGig(@PathVariable("id") Long id, Model model) {
        gigService.deleteGig(id);

        model.addAttribute("venue", gigService.findAllGigs());
        return "redirect:/gigs";
    }
}
