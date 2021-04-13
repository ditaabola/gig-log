package lv.dita.service.impl;

import lv.dita.exception.NotFoundException;
import lv.dita.model.Venue;
import lv.dita.repositories.VenueRepository;
import lv.dita.service.VenueService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> findAllVenues() {
        List<Venue> venueList = (List<Venue>) venueRepository.findAll();

        if(!venueList.isEmpty()){
            return venueList;
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public Venue findVenueById(Long id) throws NotFoundException {
        Optional<Venue> optional = venueRepository.findById(id);
        Venue venue;
        if (optional.isPresent()) {
            venue = optional.get();
        } else {
            throw new NotFoundException (String.format("Venue not found with ID %d", id));
        }
        return venue;
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
