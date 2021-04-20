package lv.dita.controllers;

import lv.dita.model.Artist;
import lv.dita.exception.NotFoundException;
import lv.dita.service.ArtistService;
import lv.dita.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArtistController {

    private final ArtistService artistService;
    private final ManagerService managerService;

    private static final String MANAGERS = "managers";
    private static final String ARTIST = "artist";
    private static final String ARTISTS = "artists";

    @Autowired
    public ArtistController(ArtistService artistService, ManagerService managerService) {
        this.artistService = artistService;
        this.managerService = managerService;
    }

    @GetMapping("/artists")
    public String findAllArtists(Model model) {

        model.addAttribute(ARTISTS, artistService.findAllArtists());
        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "list-artists";
    }

    @GetMapping("/artist/{id}")
    public String findArtistById(@PathVariable("id") Long id, Model model) {
        final Artist artist = artistService.findArtistById(id);

        model.addAttribute(ARTIST, artist);
        return "list-artist";
    }

    @GetMapping(value="/addArtist")
    public String showCreateForm(Model model) {
        Artist newArtist = new Artist();

        model.addAttribute(ARTIST, newArtist);
        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "add-artist";
    }

    @PostMapping("/add-artist")
    public String createArtist (Artist newArtist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }
        artistService.createArtist(newArtist);
        model.addAttribute(ARTIST, artistService.findAllArtists());
        return "redirect:/artists";
    }


    @GetMapping(value="/updateArtist/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final Artist foundArtist = artistService.findArtistById(id);

        model.addAttribute(ARTIST, foundArtist);
        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "update-artist";
    }

    @PostMapping("/update-artist/{id}")
    public String updateArtist(@PathVariable("id") Long id, Artist artistToUpdate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            artistToUpdate.setId(id);
            return "update-artist";
        }
        artistService.updateArtists(artistToUpdate);
        model.addAttribute(ARTIST, artistService.findAllArtists());
        return "redirect:/artists";
    }

    @GetMapping("/delete-artist/{id}")
    public String deleteArtist(@PathVariable("id") Long id, Model model) {
        artistService.deleteArtist(id);

        model.addAttribute(ARTIST, artistService.findAllArtists());
        return "redirect:/artists";
    }

}
