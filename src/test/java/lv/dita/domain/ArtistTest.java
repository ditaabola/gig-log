package lv.dita.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {

   private final Artist artist = new Artist();

    @Test
    void setName() {
        artist.setName("Manta");
        assertEquals("Manta", artist.getName());
    }

    @Test
    void getName() {

        String name = artist.getName();
        assertEquals("Juuk", name);
    }

    @Test
    void setContactEmail() {
        Artist artist = new Artist();
        artist.setContactEmail("juuk@juuk.com");
        assertEquals("juuk@juuk.com", artist.getContactEmail());
    }

    @Test
    void getContactEmail() {
        Artist artist = new Artist();
        String contactEmail = artist.getContactEmail();
        assertEquals("juuk@juuk.com", contactEmail);
    }

}