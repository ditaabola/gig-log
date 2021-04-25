package lv.dita.domain;

import org.assertj.core.internal.bytebuddy.matcher.FilterableList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {

   private final Artist artist = new Artist();

    @Test
    void shouldSetAndGetNameForArtist() {
        artist.setName("Juuk");
        String name = artist.getName();
        assertEquals("Juuk", name);
    }

    @Test
    void shouldSetAndGetContactEmail() {
        artist.setContactEmail("juuk@juuk.com");
        String contactEmail = artist.getContactEmail();
        assertEquals("juuk@juuk.com", contactEmail);
    }

    @Test
    void shouldSetAndGetManager() {
        Manager manager = new Manager();
        manager.setSurname("Manager");
        artist.setManager(manager);
        assertEquals("Manager", artist.getManager().getSurname());
    }

//    @Test
//    void shouldHaveOneGigInListWhenGigAdded() {
//        Gig gig = new Gig();
//        Venue venue = new Venue();
//        venue.setName("LMA");
//        gig.setVenue(venue);
//        List<Gig> gigsList = new ArrayList<>();
//        gigsList.add(gig);
////        assertEquals(1, artist.getGigs().size());
//        assertEquals("LMA", artist.getGigs().get(0).getVenue());
//    }

}