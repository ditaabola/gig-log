package lv.dita.controllers;

import lv.dita.domain.Venue;
import lv.dita.enums.VenueType;
import lv.dita.model.VenueDTO;
import lv.dita.service.impl.VenueServiceImpl;
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
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class VenueControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    VenueServiceImpl venueService;

    @Test
    public void testVenueController () throws Exception{
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "venues", String.class)).isNotBlank();

    }

    @Test
    public void testAddVenuePageRedirectsToVenuesAfterAddingVenueAndVenueNotNull(){
        Venue venue = new Venue();
        venue.setName("Depoe");
        venue.setType(VenueType.PRIVATE_VENUE);
        venue.setCountry("Bolivia");
        venue.setCity("San Tropez");

        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity("http://localhost:"+port+"add-venue", venue, String.class);
        assertEquals(302, responseEntity.getStatusCodeValue());
        assertNotNull(venue);
    }

    @Test
    public void testFindAllVenues() {

        List<VenueDTO> venues = venueService.findAllVenues();

        assertThat(venues.size(), is(greaterThanOrEqualTo(0)));


    }
}
