package lv.dita.controllers;

import lv.dita.entity.Artist;
import lv.dita.entity.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.service.ArtistService;
import lv.dita.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class ArtistController {

    private final ArtistService artistService;
    private final ManagerService managerService;

    @Autowired
    public ArtistController(ArtistService artistService, ManagerService managerService) {
        this.artistService = artistService;
        this.managerService = managerService;
    }

    @RequestMapping("/artists")
    public String findAllArtists(Model model) {
        final List<Artist> artists = artistService.findAllArtists();
        final List<Manager> managers = managerService.findAllManagers();

        model.addAttribute("artists", artists);
        model.addAttribute("managers", managers);
        return "list-artists";
    }

    @RequestMapping("/artist/{id}")
    public String findArtistById(@PathVariable("id") Long id, Model model) throws NotFoundException {

        final Artist artist = artistService.findArtistById(id);

        model.addAttribute("artist", artist);
        return "list-artist";
    }

    @GetMapping("/addArtist")
    public String showCreateForm(Artist artist, Model model) {

        model.addAttribute("managers", managerService.findAllManagers());
        return "add-artist";
    }

    @GetMapping("/updateArtist/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final Artist artist = artistService.findArtistById(id);

        model.addAttribute("artist", artist);
        return "update-artist";
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
