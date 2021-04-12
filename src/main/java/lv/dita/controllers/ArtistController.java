package lv.dita.controllers;

import lv.dita.model.Artist;
import lv.dita.model.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.service.ArtistService;
import lv.dita.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/artists")
    public String findAllArtists(Model model) {
        final List<Artist> artists = artistService.findAllArtists();
        final List<Manager> managers = managerService.findAllManagers();

        model.addAttribute("artists", artists);
        model.addAttribute("managers", managers);
        return "list-artists";
    }

    @GetMapping(value="/artist/{id}")
    public String findArtistById(@PathVariable("id") Long id, Model model) throws NotFoundException {

        final Artist artist = artistService.findArtistById(id);

        model.addAttribute("artist", artist);
        return "list-artist";
    }

    @GetMapping(value="/addArtist")
    public String showCreateForm(Model model) {
        Artist artist = new Artist();

        model.addAttribute("artist", artist);
        model.addAttribute("managers", managerService.findAllManagers());
        return "add-artist";
    }

    @PostMapping("/add-artist")
    public String createArtist (Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }
        artistService.createArtist(artist);
        model.addAttribute("artist", artistService.findAllArtists());
        return "redirect:/artists";
    }


    @GetMapping(value="/updateArtist/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final Artist artist = artistService.findArtistById(id);

        model.addAttribute("artist", artist);
        model.addAttribute("managers", managerService.findAllManagers());
        return "update-artist";
    }

    @PostMapping("/update-artist/{id}")
    public String updateArtist(@PathVariable("id") Long id, Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            artist.setId(id);
            return "update-artist";
        }
        artistService.updateArtists(artist);
        model.addAttribute("artist", artistService.findAllArtists());
        return "redirect:/artists";
    }

    @GetMapping("/delete-artist/{id}")
    public String deleteArtist(@PathVariable("id") Long id, Model model) {
        artistService.deleteArtist(id);

        model.addAttribute("artist", artistService.findAllArtists());
        return "redirect:/artists";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Artist> page = artistService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Artist> artists = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("artists", artists);
        return "list-artists";
    }

}
