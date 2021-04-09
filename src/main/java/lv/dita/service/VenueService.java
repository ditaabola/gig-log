package lv.dita.service;

import lv.dita.entity.Venue;
import lv.dita.exception.NotFoundException;

import java.util.List;

public interface VenueService {

    public List<Venue> findAllVenues();

    public Venue findVenueById (Long id) throws NotFoundException;

    public void createVenue (Venue venue);

    public void updateVenue (Venue venue);

    public void deleteVenue (Long id);
}
