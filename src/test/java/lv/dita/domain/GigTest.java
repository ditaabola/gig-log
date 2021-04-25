package lv.dita.domain;

import lv.dita.enums.GigType;
import lv.dita.enums.VenueType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GigTest {

    private final Gig gig = new Gig();

    @Test
    void setDate() {
        LocalDate date = LocalDate.of(2020, 1, 11);
        gig.setDate(date);
        assertEquals (date, gig.getDate());
    }

    @Test
    void getDate() {
        LocalDate date = gig.getDate();
        assertEquals (LocalDate.now(), date);
    }

    @Test
    void setType() {
        gig.setType(GigType.CORPORATE_GIG);
        assertEquals("Corporate gig", gig.getType().getDisplayValue());

    }

    @Test
    void getType() {
        String type = gig.getType().getDisplayValue();
        assertEquals("Private gig", type);
    }
}