package lv.dita.service.impl;

import lv.dita.model.Artist;
import lv.dita.exception.NotFoundException;
import lv.dita.repositories.ArtistRepository;
import lv.dita.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAllArtists() {
        List<Artist> artistList = (List<Artist>) artistRepository.findAll();

        if(!artistList.isEmpty()) {
            return artistList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Artist findArtistById(Long id) throws NotFoundException {
        Optional<Artist> optional = artistRepository.findById(id);
        Artist artist;
        if (optional.isPresent()) {
            artist = optional.get();
        } else {
            throw new NotFoundException (String.format("Artist not found with ID %d", id));
        }
        return artist;
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtists(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long id) {
        final Optional<Artist> artist = artistRepository.findById(id);
            artistRepository.deleteById(artist.get().getId());
    }

}
