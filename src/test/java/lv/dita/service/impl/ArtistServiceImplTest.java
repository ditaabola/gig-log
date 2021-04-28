package lv.dita.service.impl;

import lv.dita.domain.Artist;
import lv.dita.exception.NotFoundException;
import lv.dita.model.ArtistDTO;
import lv.dita.repositories.ArtistRepository;
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
class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepositoryMock;

    @InjectMocks
    private ArtistServiceImpl artistServiceMock;

    @Mock
    Artist artist = new Artist();
    Artist artist2 = new Artist();
    ArtistDTO dto = new ArtistDTO();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(artistServiceMock);
    }

    @Test
    void shouldFindAllArtistsListWhenFindAllArtists() {
        when(artistRepositoryMock.findAll()).thenReturn(Arrays.asList(artist, artist2));
        assertEquals(2, artistServiceMock.findAllArtists().size());
    }

    @Test
    void shouldReturnEmptyListWhenNoArtists() {
        when(artistRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, artistServiceMock.findAllArtists().size());
    }

    @Test
    void shouldFindArtistById() {
        Long id = 2l;
        artist2.setId(id);
        artist2.setName("Sniedze");
        when(artistRepositoryMock.findById(id)).thenReturn(Optional.of(artist2));
        assertEquals("Sniedze", artistServiceMock.findArtistById(id).getName());
    }

    @Test
    void shouldThrowExceptionIfCannotBeFound() {
        Long id = 3l;
        when(artistRepositoryMock.findById(id)).thenThrow(new NotFoundException(String.format("Artist not found with ID %d", id)));

        assertThatThrownBy(()-> artistServiceMock.findArtistById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Artist not found with ID %d", id));

        verify(artistRepositoryMock, never()).save(any());
    }

    @Test
    void shouldCreateArtist() {
        dto.setName("ROM");
        artistServiceMock.createArtist(dto);
        ArgumentCaptor<Artist> artistArgumentCaptor = ArgumentCaptor.forClass(Artist.class);
        verify(artistRepositoryMock).save(artistArgumentCaptor.capture());

        Artist createdArtist = artistArgumentCaptor.getValue();
        assertEquals("ROM", createdArtist.getName());

    }

    @Test
    void shouldUpdateArtist() {
        Artist artist = new Artist();
        Long id = 3l;
        artist.setId(id);
        artist.setName("Juuk");
        when(artistRepositoryMock.findById(artist.getId())).thenReturn(Optional.of(artist));
        dto.setName("Banda");
        artistServiceMock.updateArtists(id, dto);
        ArgumentCaptor<Artist> artistArgumentCaptor = ArgumentCaptor.forClass(Artist.class);
        verify(artistRepositoryMock).save(artistArgumentCaptor.capture());

        artist = artistArgumentCaptor.getValue();
        assertEquals("Banda", artist.getName());
    }

    @Test
    void shouldDeleteArtist() {
        artist.setId(2L);
        when(artistRepositoryMock.findById(artist.getId())).thenReturn(Optional.of(artist));
        artistServiceMock.deleteArtist(artist.getId());


    }

}