package lv.dita.domain;

import lv.dita.enums.GigType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class GigTest {

    private final Gig gig = new Gig();

    @Test
    void shouldSetAndGetDate() {
        LocalDate date = LocalDate.of(2020, 1, 11);
        gig.setDate(date);
        assertEquals (date, gig.getDate());
    }

    @Test
    void shouldSetAndGetType() {
        gig.setType(GigType.CORPORATE_GIG);
        assertEquals("Corporate gig", gig.getType().getDisplayValue());

    }

    @Test
    void shouldSetAndGetArtist() {
        Artist artist = new Artist();
        artist.setName("Juuk");
        gig.setArtist(artist);
        assertEquals("Juuk", gig.getArtist().getName());

    }

    @Test
    void shouldSetAndGetVenue() {
        Venue venue = new Venue();
        venue.setName("Depo");
        gig.setVenue(venue);
        assertEquals("Depo", gig.getVenue().getName());

    }


}