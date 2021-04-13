package gigLog;

import lv.dita.exception.NotFoundException;
import lv.dita.model.Artist;
import lv.dita.repositories.ArtistRepository;
import lv.dita.service.impl.ArtistServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Mock
    private ArtistRepository artistRepository;

    @Test
    public void testIfEmptyListReturnedWhenNoArtistsCreated() {
        Mockito.when(artistRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), artistService.findAllArtists());
    }


    @Test
    public void testIfFindAllReturnsAllCreatedArtists() {

        Mockito.when(artistRepository.findAll()).thenReturn(Arrays.asList(
                new Artist(1L, "Juuk", "juuuk@juuk.com"),
                new Artist(2L, "Manta", "manta@juuk.com"),
                new Artist(3l, "Sniedze", "sniedze@juuk.com")
        ));

        List<Artist> allArtistsList = artistService.findAllArtists();

        assertEquals(3, allArtistsList.size());
        assertEquals("Juuk", allArtistsList.get(0).getName());
        assertEquals("Manta", allArtistsList.get(1).getName());
        assertEquals("Sniedze", allArtistsList.get(2).getName());

    }

    @Test
    public void testIfFindByIdReturnsOneArtist() {
        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(new Artist("Banda", "banda@banda.com")));

        Artist artist = artistService.findArtistById(1l);

        assertEquals("Banda", artist.getName());
    }

    @Test(expected = NotFoundException.class)
    public void testIfNotFoundExceptionErrorThrownIfNoArtistWithIdFound() {
        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.empty());

        Artist artist = artistService.findArtistById(1l);

    }


    @Test
    public void testIfArtistUpdated() {
        final Artist artist = new Artist(1L, "Juuk", "juuk@juuk.com");

        artist.setName("Banda");
        artistService.updateArtists(artist);
        assertEquals("Banda", artist.getName());

    }

    @Test
    public void testIfArtistDeleted() {
        final Artist artist = new Artist(1L, "Juuk", "juuk@juuk.com");
        Long artistId = artist.getId();
        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        artistService.deleteArtist(artistId);

        verify(artistRepository, times(1)).deleteById(artistId);
        }
}