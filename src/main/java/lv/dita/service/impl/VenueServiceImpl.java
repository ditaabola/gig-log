package lv.dita.service.impl;

import lv.dita.exception.NotFoundException;
import lv.dita.domain.Venue;
import lv.dita.model.VenueDTO;
import lv.dita.repositories.VenueRepository;
import lv.dita.service.VenueService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<VenueDTO> findAllVenues() {
        return venueRepository.findAll()
                .stream()
                .map(venue -> mapToDTO(venue, new VenueDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public VenueDTO findVenueById(Long id) {
        return venueRepository.findById(id)
                .map(venue -> mapToDTO(venue, new VenueDTO()))
                .orElseThrow(() -> new NotFoundException(String.format("Venue not found with ID %d", id)));
    }

    @Override
    public void createVenue(VenueDTO venueDTO) {
        Venue venue = new Venue();
        mapToEntity(venueDTO, venue);

        venueRepository.save(venue);
    }

    @Override
    public void updateVenue(Long id, VenueDTO venueDTO) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Venue not found with ID %d", id)));
        mapToEntity(venueDTO, venue);

        venueRepository.save(venue);
    }

    @Override
    public void deleteVenue(Long id) {
        final Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Venue not found with ID %d", id)));

        venueRepository.deleteById(venue.getId());
    }

    private VenueDTO mapToDTO(final Venue venue, final VenueDTO venueDTO) {
        venueDTO.setId(venue.getId());
        venueDTO.setType(venue.getType());
        venueDTO.setName(venue.getName());
        venueDTO.setCountry(venue.getCountry());
        venueDTO.setCity(venue.getCity());
        return venueDTO;
    }

    private Venue mapToEntity(final VenueDTO venueDTO, final Venue venue) {
        venue.setType(venueDTO.getType());
        venue.setName(venueDTO.getName());
        venue.setCountry(venueDTO.getCountry());
        venue.setCity(venueDTO.getCity());
        return venue;
    }
}
