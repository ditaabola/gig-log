package lv.dita.service.impl;

import lv.dita.enums.GigType;
import lv.dita.domain.Gig;
import lv.dita.exception.NotFoundException;
import lv.dita.model.GigDTO;
import lv.dita.repositories.GigRepository;
import org.junit.Before;
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
class GigServiceImplTest {

    @Mock
    private GigRepository gigRepositoryMock;

    @InjectMocks
    private GigServiceImpl gigServiceMock;

    @Mock
    Gig gig = new Gig ();
    Gig gig2 = new Gig ();
    GigDTO dto = new GigDTO();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(gigServiceMock);
    }

    @Test
    void shouldFindGigsListWhenFindAllGigs() {
        when(gigRepositoryMock.findAll()).thenReturn(Arrays.asList(gig, gig2));
        assertEquals(2, gigServiceMock.findAllGigs().size());
    }

    @Test
    void shouldFindEmptyListWhenNoGigs() {
        when(gigRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, gigServiceMock.findAllGigs().size());
    }

   @Test
    void shouldFindGigById() {
        Long id = 2l;
        gig2.setId(id);
        gig2.setType(GigType.LIVE_CONCERT);
        when(gigRepositoryMock.findById(id)).thenReturn(Optional.of(gig2));
        assertEquals("Live concert", gigServiceMock.findGigById(id).getType().getDisplayValue());
    }

    @Test
    void shouldThrowExceptionIfCannotBeFound() {
        Long id = 3l;
        when(gigRepositoryMock.findById(id)).thenThrow(new NotFoundException(String.format("Gig not found with ID %d", id)));

        assertThatThrownBy(()-> gigServiceMock.findGigById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Gig not found with ID %d", id));

        verify(gigRepositoryMock, never()).save(any());
    }

    @Test
    void shouldCreateGig() {
        dto.setType(GigType.CORPORATE_GIG);
        gigServiceMock.createGig(dto);
        ArgumentCaptor<Gig> gigArgumentCaptor = ArgumentCaptor.forClass(Gig.class);
        verify(gigRepositoryMock).save(gigArgumentCaptor.capture());

        Gig createdGig = gigArgumentCaptor.getValue();
        assertEquals("Corporate gig", createdGig.getType().getDisplayValue());

    }

    @Test
    void shouldUpdateGig() {
        Gig gig = new Gig();
        Long id = 3l;
        gig.setId(id);
        gig.setType(GigType.PRIVATE_GIG);
        when(gigRepositoryMock.findById(gig.getId())).thenReturn(Optional.of(gig));
        dto.setType(GigType.CORPORATE_GIG);
        gigServiceMock.updateGig(id, dto);
        ArgumentCaptor<Gig> gigArgumentCaptor = ArgumentCaptor.forClass(Gig.class);
        verify(gigRepositoryMock).save(gigArgumentCaptor.capture());

        gig = gigArgumentCaptor.getValue();
        assertEquals("Corporate gig", gig.getType().getDisplayValue());
    }

    @Test
    void shouldDeleteGig() {
        Gig gig = new Gig();
        gig.setId(3l);
        gig.setType(GigType.CORPORATE_GIG);
        when(gigRepositoryMock.findById(gig.getId())).thenReturn(Optional.of(gig));
        gigServiceMock.deleteGig(gig.getId());
    }

}