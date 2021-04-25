package lv.dita.service.impl;

import lv.dita.enums.GigType;
import lv.dita.domain.Gig;
import lv.dita.repositories.GigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GigServiceImplTest {

    @Mock
    private GigRepository gigRepositoryMock;

    @InjectMocks
    private GigServiceImpl gigServiceMock;

    @Mock
    Gig gig = new Gig ();
    Gig gig2= new Gig ();

    @Test
    void findAllGigs() {
        when(gigRepositoryMock.findAll()).thenReturn(Arrays.asList(gig, gig2));
        assertEquals(2, gigServiceMock.findAllGigs().size());
    }

    @Test
    void findEmptyListIfNoGigs() {
        when(gigRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, gigServiceMock.findAllGigs().size());
    }

    @Test
    void findGigById() {
        Long id = 2l;
        when(gigRepositoryMock.findById(id)).thenReturn(Optional.of(gig2));
        assertEquals("Live concert", gigServiceMock.findGigById(id).getType().getDisplayValue());
    }

    @Test
    void createGigs() {
        Gig createdGig = new Gig();
        Long id = createdGig.getId();
        gigRepositoryMock.save(createdGig);
        when(gigRepositoryMock.findById(id)).thenReturn(Optional.of(createdGig));
        assertEquals("Live concert", gigServiceMock.findGigById(id).getType().getDisplayValue());

    }

    @Test
    void updateGigs() {
        Long id = 2l;
        when(gigRepositoryMock.findById(id)).thenReturn(Optional.of(gig2));
        gig2.setDate(LocalDate.of(2020, 3, 11));
        //gig2.getVenue().setName("LMA");
        gigRepositoryMock.save(gig2);
        assertEquals(LocalDate.of(2020, 3, 11), gigServiceMock.findGigById(id).getDate());
        //assertEquals(1l, gigServiceMock.findGigById(id).getVenue().getId());
    }
}