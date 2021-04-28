package lv.dita.service.impl;

import lv.dita.domain.Venue;
import lv.dita.exception.NotFoundException;
import lv.dita.model.VenueDTO;
import lv.dita.repositories.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
    VenueDTO dto = new VenueDTO();

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(venueServiceMock);
    }

    @Test
    void shouldFindVenuesListWhenAllVenues() {
        when(venueRepositoryMock.findAll()).thenReturn(Arrays.asList(venue, venue2));
        assertEquals(2, venueServiceMock.findAllVenues().size());
    }

    @Test
    void shouldFindEmptyListIfNoVenues() {
        when(venueRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, venueServiceMock.findAllVenues().size());
    }

    @Test
    void findVenueById() {
        Long id = 2l;
        venue2.setId(id);
        venue2.setName("LMS");
        when(venueRepositoryMock.findById(id)).thenReturn(Optional.of(venue2));
        assertEquals("LMS", venueServiceMock.findVenueById(id).getName());

    }

    @Test
    void shouldThrowExceptionIfCannotBeFound() {
        Long id = 3l;
        when(venueRepositoryMock.findById(id)).thenThrow(new NotFoundException(String.format("Venue not found with ID %d", id)));

        assertThatThrownBy(()-> venueServiceMock.findVenueById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Venue not found with ID %d", id));

        verify(venueRepositoryMock, never()).save(any());
    }

    @Test
    void shouldCreateVenue() {
        dto.setName("Depo");
        venueServiceMock.createVenue(dto);
        ArgumentCaptor<Venue> venueArgumentCaptor = ArgumentCaptor.forClass(Venue.class);
        verify(venueRepositoryMock).save(venueArgumentCaptor.capture());

        Venue createdVenue = venueArgumentCaptor.getValue();
        assertEquals("Depo", createdVenue.getName());

    }

    @Test
    void shouldUpdateVenue() {
        Venue venue = new Venue();
        Long id = 3l;
        venue.setId(id);
        venue.setName("Joda");
        when(venueRepositoryMock.findById(venue.getId())).thenReturn(Optional.of(venue));
        dto.setName("Moda");
        venueServiceMock.updateVenue(id, dto);
        ArgumentCaptor<Venue> venueArgumentCaptor = ArgumentCaptor.forClass(Venue.class);
        verify(venueRepositoryMock).save(venueArgumentCaptor.capture());

        venue = venueArgumentCaptor.getValue();
        assertEquals("Moda", venue.getName());
    }

    @Test
    void shouldDeleteVenue() {
        venue.setId(3l);
        when(venueRepositoryMock.findById(venue.getId())).thenReturn(Optional.of(venue));
        venueServiceMock.deleteVenue(venue.getId());
        verify(venueRepositoryMock, times(1)).deleteById(venue.getId());

    }
}