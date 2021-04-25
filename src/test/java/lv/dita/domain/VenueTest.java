package lv.dita.domain;

import lv.dita.enums.VenueType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VenueTest {

    private final Venue venue = new Venue();

    @Test
    void shouldSetAndGetName() {
        venue.setName("LMA");
        assertEquals("LMA", venue.getName());
    }

    @Test
    void shouldSetAndGetType() {
        venue.setType(VenueType.PRIVATE_VENUE);
        assertEquals("Private venue", venue.getType().getDisplayValue());
    }

    @Test
    void shouldSetAndGetCountry() {
        venue.setCountry("Lithuania");
        assertEquals("Lithuania", venue.getCountry());
    }

    @Test
    void shouldSetAndGetCity() {
        venue.setCity("Liepaja");
        assertEquals("Liepaja", venue.getCity());
    }

}