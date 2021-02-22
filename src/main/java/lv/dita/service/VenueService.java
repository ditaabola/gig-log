package lv.dita.service;

import lv.dita.models.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueService {

    public List<Venue> findAllVenues();

    public Optional<Venue> findVenueById (Long id);

    public void createVenue (Venue venue);

    public void updateVenue (Venue venue);

    public void deleteVenue (Long id);
}
