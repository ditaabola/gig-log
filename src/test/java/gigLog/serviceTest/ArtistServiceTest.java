package gigLog.serviceTest;

import lv.dita.exception.NotFoundException;
import lv.dita.model.Artist;
import lv.dita.repositories.ArtistRepository;
import lv.dita.service.ArtistService;
import lv.dita.service.impl.ArtistServiceImpl;
import org.assertj.core.api.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
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
    public void ifNoArtistsCreatedEmptyListReturned() {
        Mockito.when(artistRepository.findAll()).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), artistService.findAllArtists());
    }


    @Test
    public void findAllArtistsTest() {

        Mockito.when(artistRepository.findAll()).thenReturn(Arrays.asList(
                new Artist(1L, "Juuk", "juuk@juuk.com"),
                new Artist(2L, "Manta", "manta@juuk.com"),
                new Artist(3L, "Sniedze", "sniedze@juuk.com")
        ));

        List<Artist> allArtistsList = artistService.findAllArtists();

        assertEquals(3, allArtistsList.size());
        assertEquals("Juuk", allArtistsList.get(0).getName());
        assertEquals("Manta", allArtistsList.get(1).getName());
        assertEquals("Sniedze", allArtistsList.get(2).getName());

    }

    @Test
    public void findOneArtistTest() throws NotFoundException {
        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(new Artist("Banda", "banda@banda.com")));

        Artist artist = artistService.findArtistById(1l);

        assertEquals("Banda", artist.getName());
    }


    @Test(expected = NotFoundException.class)
    public void findArtistNotFound() throws NotFoundException {
        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.empty());

        Artist artist = artistService.findArtistById(1l);

        assertEquals("Banda", artist.getName());
    }


    @Test
    public void updateArtistTest() {
        final Artist artist = new Artist(1L, "Juuk", "juuk@juuk.com");

        given(artistRepository.save(artist)).willReturn(artist);

        final Artist updateArtist = artistService.updateArtists(artist);



        verify(artistRepository).save(any(Artist.class));
    }

    @Test
    public void deleteArtistTest() {

        Artist artist = new Artist(1L,"Voila","voila@voila.com");
//        artistService.createArtist(artist);
        artistRepository.save(artist);

        artistService.deleteArtist(1L);
        verify(artistRepository, times(1)).delete(artist);

    }

//    @Test
//    public void createdArtistHasNameTest() {
//
//        Artist artist = new Artist(1L,"Voila","voila@voila.com");
//        when(artistRepository.save(artist)).thenReturn(artist);
//        artistService.createArtist(artist);
//        assertEquals(1L, result.getId());
//        assertEquals("Todo Sample 8", result.getText());
//        assertEquals(true, result.isCompleted());
}