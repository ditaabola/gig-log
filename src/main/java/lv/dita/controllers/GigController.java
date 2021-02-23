package lv.dita.controllers;

import lv.dita.models.Gig;
import lv.dita.service.ArtistService;
import lv.dita.service.GigService;
import lv.dita.service.ManagerService;
import lv.dita.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class GigController {

    private final GigService gigService;
    private final ArtistService artistService;
    private final VenueService venueService;
    private final ManagerService managerService;

    @Autowired
    public GigController (GigService gigService, ArtistService artistService, VenueService venueService, ManagerService managerService) {
        this.gigService = gigService;
        this.artistService = artistService;
        this.venueService = venueService;
        this.managerService = managerService;
    }

    @RequestMapping("/gigs")
    public String findAllGigs(Model model) {
        final List<Gig> gigs = gigService.findAllGigs();

        model.addAttribute("gigs", gigs);
        return "list-gigs";
    }

    @RequestMapping("/searchGig")
    public String searchGig(@Param("keyword") String keyword, Model model) {
        final List<Gig> gigs = gigService.searchGigs(keyword);

        model.addAttribute("gigs", gigs);
        model.addAttribute("keyword", keyword);
        return "list-gigs";
    }

    @RequestMapping("/gig/{id}")
    public String findGigById(@PathVariable("id") Long id, Model model) {
        final Optional<Gig> gig = gigService.findGigById(id);

        model.addAttribute("gig", gig);
        return "list-gig";
    }

//    @GetMapping("/add")
//    public String showCreateForm(Gig gig, Model model) {
//        model.addAttribute("venueName", venueService.findAllVenues());
//        model.addAttribute("date", authorService.findAllAuthors());
//        model.addAttribute("type", publisherService.findAllPublishers());
//        return "add-gig";
//    }

    @RequestMapping("/add-gig")
    public String createGig(Gig gig, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-gig";
        }

        gigService.createGig(gig);
        model.addAttribute("gig", gigService.findAllGigs());
        return "redirect:/gigs";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Optional<Gig> gig = gigService.findGigById(id);

        model.addAttribute("gig", gig);
        return "update-gig";
    }

    @RequestMapping("/update-gig/{id}")
    public String updateGig(@PathVariable("id") Long id, Gig gig, BindingResult result, Model model) {
        if (result.hasErrors()) {
            gig.setId(id);
            return "update-gig";
        }

        gigService.updateGig(gig);
        model.addAttribute("gig", gigService.findAllGigs());
        return "redirect:/gigs";
    }

    @RequestMapping("/remove-gig/{id}")
    public String deleteGig(@PathVariable("id") Long id, Model model) {
        gigService.deleteGig(id);

        model.addAttribute("gig", gigService.findAllGigs());
        return "redirect:/gigs";
    }
}