package lv.dita.service.impl;

import lv.dita.domain.Artist;
import lv.dita.exception.NotFoundException;
import lv.dita.repositories.ArtistRepository;
import lv.dita.repositories.ManagerRepository;
import lv.dita.service.ArtistService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ManagerRepository managerRepository;

    public ArtistServiceImpl(final ArtistRepository artistRepository,
                         final ManagerRepository managerRepository) {
        this.artistRepository = artistRepository;
        this.managerRepository = managerRepository;
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
