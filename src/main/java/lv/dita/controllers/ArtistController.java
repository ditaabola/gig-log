package lv.dita.controllers;

import lv.dita.domain.Artist;
import lv.dita.model.ArtistDTO;
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

    @GetMapping("/artists")
    public String findAllArtists(Model model) {

        model.addAttribute(ARTISTS, artistService.findAllArtists());
        return "list-artists";
    }

    @GetMapping("/artist/{id}")
    public String findArtistById(@PathVariable("id") Long id, Model model) {

        model.addAttribute(ARTIST, artistService.findArtistById(id));
        return "list-artist";
    }

    @GetMapping(value="/addArtist")
    public String showCreateForm(Model model) {
        Artist  artist = new Artist();

        model.addAttribute(ARTIST, artist);
        model.addAttribute(MANAGERS, managerService.findAllManagers());
        return "add-artist";
    }

    @PostMapping("/add-artist")
    public String createArtist (ArtistDTO artistDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error/404";
        }

        artistService.createArtist(artistDTO);
        model.addAttribute(ARTIST, artistService.findAllArtists());
        return "redirect:/artists";
    }


    @GetMapping(value="/updateArtist/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute(ARTIST, artistService.findArtistById(id));
        return "update-artist";
    }

    @PostMapping("/update-artist/{id}")
    public String updateArtist(@PathVariable("id") Long id, ArtistDTO artistDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            artistDTO.setId(id);
            return "error/404";
        }
        artistService.updateArtists(id, artistDTO);
        model.addAttribute(ARTIST, artistService.findAllArtists());
        return "redirect:/artists";
    }

    @GetMapping("/delete-artist/{id}")
    public String deleteArtist(@PathVariable("id") Long id, Model model) {
        artistService.deleteArtist(id);

        model.addAttribute(ARTISTS, artistService.findAllArtists());
        return "redirect:/artists";
    }

}
