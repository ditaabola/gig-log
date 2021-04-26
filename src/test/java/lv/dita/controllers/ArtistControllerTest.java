package lv.dita.controllers;

import lv.dita.domain.Artist;
import lv.dita.domain.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.model.ArtistDTO;
import lv.dita.service.impl.ArtistServiceImpl;
import lv.dita.service.impl.ManagerServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
@AutoConfigureMockMvc
public class ArtistControllerTest {

    @Autowired
    ArtistController artistController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ArtistServiceImpl artistService;

    @MockBean
    ManagerServiceImpl managerService;

    ArtistDTO artist1;
    ArtistDTO artist3;
    Manager manager1;

    @Before
    public void setUpArtist() throws Exception{
        artist1 = new ArtistDTO();
        manager1 = new Manager();
        manager1.setSurname("Manager");
        artist1.setId(1L);
        artist1.setName("Juuk");
        artist1.setContactEmail("juuk@juuk.com");
        artist1.setManager(manager1);

        artist3 = new ArtistDTO();
        artist3.setName("Manta");
        artist3.setContactEmail("manta@manta.com");
    }

    @Test
    public void controllerInitializedCorrectly() {
        assertThat(artistController).isNotNull();
    }

    @Test
    public void shouldGetViewOfAllArtists() throws Exception {
        when(artistService.findAllArtists())
                .thenReturn(List.of(artist3));

        this.mockMvc.perform(get("/artists"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("artists"))
                .andExpect(model().size(1))
                .andExpect(view().name("list-artists"))
                .andDo(print());
    }

    @Test
    public void shouldGetViewOfFindArtistById() throws Exception {
        assertThat(this.artistService).isNotNull();
        when(artistService.findArtistById(1L)).thenReturn(artist1);
        mockMvc.perform(get("/artist/{id}/", 1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artist", Matchers.hasProperty("name", Matchers.equalTo("Juuk"))))
                .andExpect(model().attribute("artist", Matchers.hasProperty("contactEmail", Matchers.equalTo("juuk@juuk.com"))))
                .andExpect(view().name("list-artist"))
                .andReturn();

        verify(artistService, times(1)).findArtistById(1L);
        verifyNoMoreInteractions(artistService);
    }

    @Test
    public void shouldGetAddArtistCreateForm() throws Exception {

        mockMvc.perform(get("/addArtist/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("artist"))
                .andExpect(view().name("add-artist"))
                .andReturn();
           }

    @Test
    public void shouldRedirectToAllArtistsViewWhenArtistCreated() throws Exception {

        artistService.createArtist(artist1);
        mockMvc.perform(post("/add-artist"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void shouldGetUpdateArtistForm() throws Exception {
        when(artistService.findArtistById(1L)).thenReturn(artist1);

        mockMvc.perform(get("/updateArtist/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("artist"))
                .andExpect(view().name("update-artist"))
                .andReturn();
    }

    @Test
    public void shouldRedirectToAllArtistsViewWhenArtistUpdated() throws Exception {
        Long id = 1l;
        artistService.updateArtists(id, artist1);
        mockMvc.perform(post("/update-artist/{id}", id))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void shouldRedirectToArtistsListAfterArtistDeleted() throws Exception {
        Long id = 1l;
        artistService.deleteArtist(id);
        mockMvc.perform(get("/delete-artist/{id}", id))
                .andExpect(status().is3xxRedirection());
    }

}
