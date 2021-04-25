package lv.dita.service;

import lv.dita.model.VenueDTO;
import java.util.List;

public interface VenueService {

    public List<VenueDTO> findAllVenues();

    public VenueDTO findVenueById (Long id);

    public void createVenue (VenueDTO venueDTO);

    public void updateVenue (Long id, VenueDTO venueDTO);

    public void deleteVenue (Long id);
}
