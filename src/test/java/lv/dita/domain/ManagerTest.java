package lv.dita.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private final Manager manager = new Manager();

    @Mock
    List<Artist> artistList = new ArrayList<>();

    @Test
    void shouldSetAndGetName() {
        manager.setName("John");
        assertEquals("John", manager.getName());
    }

    @Test
    void shouldSetAndGetSurName() {
        manager.setSurname("Bravo");
        assertEquals("Bravo", manager.getSurname());
    }

    @Test
    void shouldSetAndGetEmail() {
        manager.setEmail("this@email.com");
        assertEquals("this@email.com", manager.getEmail());
    }

//    @Test
//    void shouldGetOneArtistNameIfOneArtistInList() {
//        Artist artist = new Artist();
//        artist.setName("Juuk");
//        artistList.add(artist);
//
////        assertEquals(1, manager.getArtistList().size());
//        assertEquals("Juuk", manager.getArtistList().get(0).getName());
//    }


}