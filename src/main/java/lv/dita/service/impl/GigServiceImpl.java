package lv.dita.service.impl;

import lv.dita.domain.Artist;
import lv.dita.domain.Gig;
import lv.dita.domain.Venue;
import lv.dita.exception.NotFoundException;
import lv.dita.model.GigDTO;
import lv.dita.repositories.ArtistRepository;
import lv.dita.repositories.GigRepository;
import lv.dita.repositories.VenueRepository;
import lv.dita.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class GigServiceImpl implements GigService {

    @Autowired
    private final GigRepository gigRepository;

    @Autowired
    private final ArtistRepository artistRepository;

    @Autowired
    private final VenueRepository venueRepository;

    public GigServiceImpl(GigRepository gigRepository, ArtistRepository artistRepository, VenueRepository venueRepository) {
        this.gigRepository = gigRepository;
        this.artistRepository = artistRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public List<GigDTO> findAllGigs() {
        return gigRepository.findAll()
                .stream()
                .map(gig -> mapToDTO(gig, new GigDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public GigDTO findGigById(Long id) {
        return gigRepository.findById(id)
                .map(gig -> mapToDTO(gig, new GigDTO()))
                .orElseThrow(() -> new NotFoundException(String.format("Gig not found with ID %d", id)));
    }


    @Override
    public void createGig(GigDTO gigDTO) {

        Gig gig = new Gig();
        mapToEntity(gigDTO, gig);
        gigRepository.save(gig);
    }

    @Override
    public void updateGig(Long id, GigDTO gigDTO) {
        Gig gig = gigRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Gig not found with ID %d", id)));
        mapToEntity(gigDTO, gig);
        gigRepository.save(gig);
    }

    @Override
    public void deleteGig(Long id) {
        final Gig gig = gigRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Gig not found with ID %d", id)));

        gigRepository.deleteById(gig.getId());
    }

    private GigDTO mapToDTO(final Gig gig, final GigDTO gigDTO) {
        gigDTO.setId(gig.getId());
        gigDTO.setDate(gig.getDate());
        gigDTO.setType(gig.getType());
        gigDTO.setArtist(gig.getArtist());
        gigDTO.setVenue(gig.getVenue());
        return gigDTO;
    }

    private Gig mapToEntity(final GigDTO gigDTO, final Gig gig) {
        gig.setDate(gigDTO.getDate());
        gig.setType(gigDTO.getType());
        if (gigDTO.getArtist() != null && (gig.getArtist() == null || !gig.getArtist().getId().equals(gigDTO.getArtist().getId()))) {
            final Artist artist = artistRepository.findById(gigDTO.getArtist().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "artist not found"));
            gig.setArtist(artist);
        }
        if (gigDTO.getVenue() != null && (gig.getVenue() == null || !gig.getVenue().getId().equals(gigDTO.getVenue()))) {
            final Venue venue = venueRepository.findById(gigDTO.getVenue().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "venue not found"));
            gig.setVenue(venue);
        }
        return gig;
    }
}
