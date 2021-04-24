package lv.dita.service.impl;

import lv.dita.enums.VenueType;
import lv.dita.domain.Venue;
import lv.dita.repositories.VenueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VenueServiceImplTest {

    @Mock
    private VenueRepository venueRepositoryMock;

    @InjectMocks
    private VenueServiceImpl venueServiceMock;

    @Mock
    Venue venue = new Venue();
    Venue venue2 = new Venue();

    @Test
    void findAllVenues() {
        when(venueRepositoryMock.findAll()).thenReturn(Arrays.asList(venue, venue2));
        assertEquals(2, venueServiceMock.findAllVenues().size());
    }

    @Test
    void findEmptyListIfNoVenues() {
        when(venueRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, venueServiceMock.findAllVenues().size());
    }

    @Test
    void findVenueById() {
        Long id = 2l;
        when(venueRepositoryMock.findById(id)).thenReturn(Optional.of(venue2));
        assertEquals("Alternative club", venueServiceMock.findVenueById(id).getType().getDisplayValue());
    }

    @Test
    void createVenue() {
        Venue createdVenue = new Venue();
        Long id = createdVenue.getId();
        venueRepositoryMock.save(createdVenue);
        when(venueRepositoryMock.findById(id)).thenReturn(Optional.of(createdVenue));
        assertEquals("Private venue", venueServiceMock.findVenueById(id).getType().getDisplayValue());

    }

    @Test
    void updateGigs() {
        Long id = 2l;
        when(venueRepositoryMock.findById(id)).thenReturn(Optional.of(venue2));
        venue2.setCity("Valmiera");
        venueRepositoryMock.save(venue2);
        assertEquals("Valmiera", venueServiceMock.findVenueById(id).getCity());
    }
}