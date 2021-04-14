package lv.dita.model;

import lv.dita.enums.GigType;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GigTest {

    @Test
    void setDate() {
        Gig gig = new Gig();
        LocalDate date = LocalDate.now();
        gig.setDate(date);
        assertEquals (LocalDate.now(), gig.getDate());

    }

    @Test
    void setType() {
        Gig gig = new Gig();
        gig.setType(GigType.PRIVATE_GIG);
        assertEquals("Private gig", gig.getType().getDisplayValue());

    }
}