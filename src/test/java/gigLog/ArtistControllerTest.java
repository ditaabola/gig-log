package gigLog;

import lv.dita.controllers.ArtistController;
import lv.dita.model.Artist;
import lv.dita.model.Manager;
import lv.dita.service.ArtistService;
import lv.dita.service.ManagerService;
import lv.dita.service.impl.ArtistServiceImpl;
import lv.dita.service.impl.ManagerServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArtistServiceImpl artistService;

    @MockBean
    private ManagerServiceImpl managerService;


    @Test
    public void shouldReturnArtistListView() throws Exception {
        Artist artist1 = new Artist(1L, "Juuk", "juuk@juuk.com");
        Artist artist2 = new Artist(2L, "Manta", "manta@juuk.com");
        Artist artist3 = new Artist(3L, "Sniedze", "sniedze@juuk.com");
        List<Artist> artistList = List.of(artist1, artist2, artist3);

        Manager manager = new Manager(1L, "John", "Manager", "john@manager.com");
        List<Manager> managerList = List.of(manager);

        when(artistService.findAllArtists()).thenReturn(artistList);
        when(managerService.findAllManagers()).thenReturn(managerList);

        this.mvc.perform(get("/artists"))
                .andExpect(view().name("list-artists"))
                .andExpect(model().attribute("artists", artistList))
                .andExpect(model().attribute("artists", Matchers.hasSize(3)))
                .andDo(print());
        }
    }


