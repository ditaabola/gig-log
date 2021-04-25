package lv.dita.controllers;

import lv.dita.GigLogApplication;
import lv.dita.domain.Artist;
import lv.dita.domain.Manager;
import lv.dita.model.ArtistDTO;
import lv.dita.model.ManagerDTO;
import lv.dita.service.impl.ArtistServiceImpl;
import lv.dita.service.impl.ManagerServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.web.context.WebApplicationContext;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import java.util.Collection;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ArtistController.class)
@AutoConfigureMockMvc
public class ArtistControllerTest {

    @Autowired
    private WebApplicationContext wac;

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
    }


    @Test
    public void shouldGetViewOfAllArtists() throws Exception {
        this.mockMvc.perform(get("/list-artists"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("artists"))
                .andExpect(view().name("list-artists"));
    }

    @Test
    public void shouldGetViewOfFindArtistById() throws Exception {
        assertThat(this.artistService).isNotNull();
        when(artistService.findArtistById(1L)).thenReturn(artist1);
        MvcResult result= mockMvc.perform(get("/artist/{id}/", 1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artist", hasProperty("name", is("Juuk"))))
                .andExpect(model().attribute("artist", hasProperty("contactEmail", is("juuk@juuk.com"))))
                .andExpect(view().name("list-artist"))
                .andReturn();

        verify(artistService, times(1)).findArtistById(1L);
        verifyNoMoreInteractions(artistService);
    }


}
