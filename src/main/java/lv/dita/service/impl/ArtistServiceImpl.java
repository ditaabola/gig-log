package lv.dita.service.impl;

import lv.dita.models.Artist;
import lv.dita.repositories.ArtistRepository;
import lv.dita.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> findArtistById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public void createArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public void updateArtists(Artist artist) {
    }

    @Override
    public void deleteArtist(Long id) {
        final Optional<Artist> artist = artistRepository.findById(id);
        artistRepository.deleteById(artist.get().getId());
    }

}
