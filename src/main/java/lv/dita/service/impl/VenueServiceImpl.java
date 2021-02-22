package lv.dita.service.impl;

import lv.dita.models.Venue;
import lv.dita.repositories.VenueRepository;
import lv.dita.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<Venue> findAllVenues() {
        return (List<Venue>) venueRepository.findAll();
    }

    @Override
    public Optional<Venue> findVenueById(Long id) {
        return venueRepository.findById(id);
    }

    @Override
    public void createVenue(Venue venue) {
        venueRepository.save(venue);
    }

    @Override
    public void updateVenue(Venue venue) {
        venueRepository.save(venue);
    }

    @Override
    public void deleteVenue(Long id) {
        final Optional<Venue> venue = venueRepository.findById(id);

        venueRepository.deleteById(venue.get().getId());

    }
}
