package lv.dita.service.impl;


import lv.dita.exception.NotFoundException;
import lv.dita.model.ArtistDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistServiceIntegrationTest {

    @Autowired
    private ArtistServiceImpl artistService;

    @Test
    void shouldFindAnExistingArtistById() {
        ArtistDTO artist = artistService.findArtistById(1L);
        assertThat(artist).isNotNull();
        assertThat(artist.getName().equals("Juuk"));
    }

    @Test(expected = NotFoundException.class)
    void shouldThrowNotFoundExceptionWhenFindByIdReturnsNoArtist() {
        ArtistDTO artist = artistService.findArtistById(7L);
        assertThat(artist).isNull();

    }


}
