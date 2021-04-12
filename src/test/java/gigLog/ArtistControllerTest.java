package gigLog;

import lv.dita.controllers.ArtistController;
import lv.dita.model.Artist;
import lv.dita.service.ArtistService;
import lv.dita.service.ManagerService;
import lv.dita.service.impl.ArtistServiceImpl;
import lv.dita.service.impl.ManagerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
@ActiveProfiles("test")
public class ArtistControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArtistServiceImpl artistService;

    @MockBean
    private ManagerServiceImpl managerService;

    private List<Artist> artistList;

    @BeforeEach
    void setUp() {
        this.artistList = new ArrayList<>();
        this.artistList.add(new Artist(1L, "Juuk", "juuk@juuk.com"));
        this.artistList.add(new Artist(2L, "Manta", "manta@manta.com"));
        this.artistList.add(new Artist(3L, "Sniedze", "sniedze@sniedze.com"));
    }


    @Test
    public void shouldReturnAllArtists() throws Exception {

    }



}
