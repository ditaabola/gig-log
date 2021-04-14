package lv.dita.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {

    @Test
    void setName() {
        Artist artist = new Artist();
        artist.setName("Juuk");
        assertEquals("Juuk", artist.getName());
    }

    @Test
    void setContactEmail() {
        Artist artist = new Artist();
        artist.setContactEmail("juuk@juuk.com");
        assertEquals("juuk@juuk.com", artist.getContactEmail());
    }

}