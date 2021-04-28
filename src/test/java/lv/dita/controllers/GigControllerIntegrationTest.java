package lv.dita.controllers;

import lv.dita.domain.Artist;
import lv.dita.domain.Gig;
import lv.dita.domain.Venue;
import lv.dita.enums.GigType;
import lv.dita.model.GigDTO;
import lv.dita.service.impl.GigServiceImpl;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GigControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    GigServiceImpl gigService;

    @Test
    public void testGigController () throws Exception{
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "gigs", String.class)).isNotBlank();

    }

    @Test
    public void testAddArtistPageRedirectsToGigsAfterAddingGigAndGigNotNull(){
        Gig gig = new Gig();
        Artist artist = new Artist();
        artist.setName("Juuk");
        Venue venue = new Venue();
        venue.setName("Depo");

        gig.setArtist(artist);
        gig.setVenue(venue);
        gig.setDate(LocalDate.of(2020, 11, 20));
        gig.setType(GigType.PRIVATE_GIG);
        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity("http://localhost:"+port+"add-gig", gig, String.class);
        assertEquals(302, responseEntity.getStatusCodeValue());
        assertNotNull(gig);
    }

    @Test
    public void testFindAllGigs() {
        List<GigDTO> gigs = gigService.findAllGigs();

        assertThat(gigs.size(), is(greaterThanOrEqualTo(0)));
    }
}
