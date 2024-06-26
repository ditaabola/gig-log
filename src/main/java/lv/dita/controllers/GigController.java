package lv.dita.controllers;

import lv.dita.domain.Gig;
import lv.dita.model.GigDTO;
import lv.dita.service.ArtistService;
import lv.dita.service.GigService;
import lv.dita.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class GigController {

    private final GigService gigService;
    private final VenueService venueService;
    private final ArtistService artistService;

    private static final String GIGS = "gigs";
    private static final String GIG = "gig";
    private static final String VENUES = "venues";
    private static final String ARTISTS = "artists";

    @Autowired
    public GigController(GigService gigService, VenueService venueService, ArtistService artistService) {
        this.gigService = gigService;
        this.venueService = venueService;
        this.artistService = artistService;
    }

    @GetMapping("/gigs")
    public String findAllGigs(Model model) {

        model.addAttribute(GIGS, gigService.findAllGigs());
        return "list-gigs";
    }

    @GetMapping("/gig/{id}")
    public String findGigById(@PathVariable("id") Long id, Model model){

        model.addAttribute(GIG, gigService.findGigById(id));
        return "list-gig";
    }


    @GetMapping("/addGig")
    public String showCreateForm(Model model) {
        Gig gig = new Gig();

        model.addAttribute(GIG, gig);
        model.addAttribute(VENUES, venueService.findAllVenues());
        model.addAttribute(ARTISTS, artistService.findAllArtists());

        return "add-gig";
    }

    @PostMapping(value="/add-gig")
    public String createGig (GigDTO gigDTO, Model model) {

        gigService.createGig(gigDTO);
        model.addAttribute(GIG, gigService.findAllGigs());
        return "redirect:/gigs";
    }


    @GetMapping(value="/updateGig/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute(GIG, gigService.findGigById(id));
        model.addAttribute(VENUES, venueService.findAllVenues());
        model.addAttribute(ARTISTS, artistService.findAllArtists());
        return "update-gig";
    }

    @PostMapping(value="/update-gig/{id}")
    public String updateGig(@PathVariable("id") Long id, GigDTO gigDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            gigDTO.setId(id);
            return "update-gig";
        }
        gigService.updateGig(id, gigDTO);
        model.addAttribute(GIG, gigService.findAllGigs());
        return "redirect:/gigs";
    }



    @GetMapping("/delete-gig/{id}")
    public String deleteGig(@PathVariable("id") Long id, Model model) {
        gigService.deleteGig(id);

        model.addAttribute(GIG, gigService.findAllGigs());
        return "redirect:/gigs";
    }
}
