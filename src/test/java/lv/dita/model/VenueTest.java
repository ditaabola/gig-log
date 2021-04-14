package lv.dita.model;

import lv.dita.enums.GigType;
import lv.dita.enums.VenueType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VenueTest {



    @Test
    void getName() {
        Venue venue = new Venue();
        venue.setName("Depo");
        assertEquals("Depo", venue.getName());
    }

    @Test
    void getType() {
        Venue venue = new Venue();
        venue.setType(VenueType.PRIVATE_VENUE);
        assertEquals("Private venue", venue.getType().getDisplayValue());
    }

    @Test
    void getCountry() {
        Venue venue = new Venue();
        venue.setCountry("Latvia");
        assertEquals("Latvia", venue.getCountry());
    }

    @Test
    void getCity() {
        Venue venue = new Venue();
        venue.setCity("Riga");
        assertEquals("Riga", venue.getCity());
    }
}