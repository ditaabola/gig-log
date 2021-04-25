package lv.dita.service.impl;

import lv.dita.domain.Artist;
import lv.dita.domain.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.model.ArtistDTO;
import lv.dita.repositories.ArtistRepository;
import lv.dita.repositories.ManagerRepository;
import lv.dita.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ArtistDTO> findAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(artist -> mapToDTO(artist, new ArtistDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDTO findArtistById(Long id) {
        return artistRepository.findById(id)
                .map(artist -> mapToDTO(artist, new ArtistDTO()))
                .orElseThrow(() -> new NotFoundException(String.format("Artist not found with ID %d", id)));
    }

    @Override
    public void createArtist(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        mapToEntity(artistDTO, artist);
        artistRepository.save(artist);
    }

    @Override
    public void updateArtists(Long id, ArtistDTO artistDTO) {

        Artist artist = artistRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Artist not found with ID %d", id)));
        mapToEntity(artistDTO, artist);
        artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Artist not found with ID %d", id)));

        artistRepository.deleteById(artist.getId());

    }

    private ArtistDTO mapToDTO(final Artist artist, final ArtistDTO artistDTO) {
        artistDTO.setId(artist.getId());
        artistDTO.setName(artist.getName());
        artistDTO.setContactEmail(artist.getContactEmail());
        artistDTO.setManager(artist.getManager());
        return artistDTO;
    }

    private Artist mapToEntity(final ArtistDTO artistDTO, final Artist artist) {
        artist.setName(artistDTO.getName());
        artist.setContactEmail(artistDTO.getContactEmail());
        if (artistDTO.getManager() != null && (artist.getManager() == null || !artist.getManager().getId().equals(artistDTO.getManager()))) {
            final Manager manager = managerRepository.findById(artistDTO.getManager().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "manager not found"));
            artist.setManager(manager);
        }
        return artist;
    }
}
