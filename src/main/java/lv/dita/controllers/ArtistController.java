package lv.dita.controllers;

import lv.dita.domain.Artist;
import lv.dita.service.impl.ArtistServiceImpl;
import lv.dita.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArtistController {

    private final ArtistServiceImpl artistService;
    private final ManagerServiceImpl managerService;

    private static final String MANAGERS = "managers";
    private static final String ARTIST = "artist";
    private static final String ARTISTS = "artists";

    @Autowired
    public ArtistController(ArtistServiceImpl artistService, ManagerServiceImpl managerService) {
        this.artistService = artistService;
        this.managerService = managerService;

    }

    @RequestMapping("/artists")
    public String findAllArtists(Model model) {

        model.addAttribute(ARTISTS, artistService.findAllArtists());
        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "list-artists";
    }

    @RequestMapping("/artist/{id}")
    public String findArtistById(@PathVariable("id") Long id, Model model) {

        model.addAttribute(ARTIST, artistService.findArtistById(id));
        return "list-artist";
    }

    @RequestMapping(value="/addArtist")
    public String showCreateForm(Model model) {
        Artist  artist = new Artist();

        model.addAttribute(ARTIST, artist);
        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "add-artist";
    }

    @RequestMapping("/add-artist")
    public String createArtist (Artist artist, Model model) {

        artistService.createArtist(artist);
        model.addAttribute(ARTIST, artistService.findAllArtists());
        return "redirect:/artists";
    }


    @RequestMapping(value="/updateArtist/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute(ARTIST, artistService.findArtistById(id));
        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "update-artist";
    }

    @RequestMapping("/update-artist/{id}")
    public String updateArtist(@PathVariable("id") Long id, Artist artistToUpdate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            artistToUpdate.setId(id);
            return "update-artist";
        }
        artistService.updateArtists(artistToUpdate);
        model.addAttribute(ARTIST, artistService.findAllArtists());
        return "redirect:/artists";
    }

    @RequestMapping("/delete-artist/{id}")
    public String deleteArtist(@PathVariable("id") Long id, Model model) {
        artistService.deleteArtist(id);

        model.addAttribute(ARTISTS, artistService.findAllArtists());
        return "redirect:/artists";
    }

}
