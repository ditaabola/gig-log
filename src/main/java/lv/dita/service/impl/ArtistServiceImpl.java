package lv.dita.service.impl;

import lv.dita.model.Artist;
import lv.dita.exception.NotFoundException;
import lv.dita.repositories.ArtistRepository;
import lv.dita.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAllArtists() {
        List<Artist> artistList = artistRepository.findAll();

        if (!artistList.isEmpty()) {
            return artistList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Artist findArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Artist not found with ID %d", id)));
    }

    @Override
    public void createArtist(Artist artist) {
            artistRepository.save(artist);
    }

    @Override
    public void updateArtists(Artist artist) {
            artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long id) {
        final Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Artist not found with ID %d", id)));

        artistRepository.deleteById(artist.getId());

    }
}
