package lv.dita.controllers;

import lv.dita.models.Artist;
import lv.dita.service.ArtistService;
import lv.dita.service.GigService;
import lv.dita.service.ManagerService;
import lv.dita.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @RequestMapping("/artists")
    public String findAllArtists(Model model) {
        final List<Artist> artists = artistService.findAllArtists();

        model.addAttribute("artists", artists);
        return "list-artists";
    }

    @RequestMapping("/artist{id}")
    public String findArtistById(@PathVariable("id") Long id, Model model) {
        final Optional<Artist> artist = artistService.findArtistById(id);

        model.addAttribute("artist", artist);
        return "list-artist";
    }

    @RequestMapping("/update-artist/{id}")
    public String updateArtist(@PathVariable("id") Long id, Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            artist.setId(id);
            return "update-artist";
        }

        artistService.updateArtists(artist);
        model.addAttribute("artist", artistService.findAllArtists());
        return "redirect:/artists";
    }

    @GetMapping("/addArtist")
    public String showCreateForm(Artist artist) {
        return "add-artist";
    }

    @RequestMapping("/add-artist")
    public String createArtist (Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }
        artistService.createArtist(artist);
        model.addAttribute("artist", artistService.findAllArtists());
        return "redirect:/artists";
    }


    @RequestMapping("/remove-artist/{id}")
    public String deleteArtist(@PathVariable("id") Long id, Model model) {
        artistService.deleteArtist(id);

        model.addAttribute("artist", artistService.findAllArtists());
        return "redirect:/artists";
    }

}