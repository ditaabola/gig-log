package lv.dita.controllers;

import lv.dita.domain.Artist;
import lv.dita.model.ArtistDTO;
import lv.dita.service.impl.ArtistServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ArtistControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ArtistServiceImpl artistService;

    @Test
    public void testArtistController () throws Exception{
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "artists", String.class)).isNotBlank();

    }

    @Test
    public void testAddArtistPageRedirectsToArtistsAfterAddingArtistAndArtistNotNull(){
        Artist artist = new Artist();
        artist.setName("Juuk");
        artist.setContactEmail("juuk@juuk.com");
        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity("http://localhost:"+port+"add-artist", artist, String.class);
        assertEquals(302, responseEntity.getStatusCodeValue());
        assertNotNull(artist);
    }

    @Test
    public void testFindAllArtists() {
        List<ArtistDTO> artists = artistService.findAllArtists();

        assertThat(artists.size()).isGreaterThanOrEqualTo(0);
    }
}
