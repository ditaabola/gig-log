package lv.dita.service.impl;

import lv.dita.domain.Artist;
import lv.dita.repositories.ArtistRepository;
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
class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepositoryMock;

    @InjectMocks
    private ArtistServiceImpl artistServiceMock;

    @Mock
    Artist artist = new Artist();
    Artist artist2 = new Artist();
    Artist artist3 = new Artist();

    @Test
    void findAllArtists() {
        when(artistRepositoryMock.findAll()).thenReturn(Arrays.asList(artist, artist2));
        assertEquals(2, artistServiceMock.findAllArtists().size());
    }

    @Test
    void findEmptyListIfNoArtists() {
        when(artistRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, artistServiceMock.findAllArtists().size());
    }

    @Test
    void findArtistById() {
        Long id = 2l;
        artist2.setId(id);
        when(artistRepositoryMock.findById(id)).thenReturn(Optional.of(artist2));
        assertEquals(artist2.getName(), artistServiceMock.findArtistById(id).getName());
    }

    @Test
    void createArtists() {
        Artist createdArtist = new Artist();
        Long id = createdArtist.getId();
        createdArtist.setName("Sniedze");
        artistRepositoryMock.save(createdArtist);
        when(artistRepositoryMock.findById(id)).thenReturn(Optional.of(createdArtist));
        assertEquals("Sniedze", artistServiceMock.findArtistById(id).getName());
    }

    @Test
    void updateArtists() {
        Long id = 2l;
        when(artistRepositoryMock.findById(id)).thenReturn(Optional.of(artist2));
        artist2.setName("Banda");
        artistRepositoryMock.save(artist2);
        assertEquals("Banda", artistServiceMock.findArtistById(id).getName());
    }

}