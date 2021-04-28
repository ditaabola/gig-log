package lv.dita.controllers;

import lv.dita.domain.Artist;
import lv.dita.domain.Venue;
import lv.dita.enums.GigType;
import lv.dita.model.GigDTO;
import lv.dita.service.impl.ArtistServiceImpl;
import lv.dita.service.impl.GigServiceImpl;
import lv.dita.service.impl.VenueServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GigController.class)
@AutoConfigureMockMvc
public class GigControllerTest {

    @Autowired
    GigController gigController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GigServiceImpl gigService;

    @MockBean
    VenueServiceImpl venueService;

    @MockBean
    ArtistServiceImpl artistService;

    GigDTO gigDTO1;
    GigDTO gigDTO2;
    Artist artist;
    Venue venue;

    @Before
    public void setUpGig() throws Exception{
        gigDTO1 = new GigDTO();
        artist = new Artist();
        artist.setName("Juuk");
        venue = new Venue();
        venue.setName("Mala");
        gigDTO1.setId(1L);
        gigDTO1.setType(GigType.PRIVATE_GIG);
        gigDTO1.setArtist(artist);
        gigDTO1.setVenue(venue);

        gigDTO2 = new GigDTO();
        gigDTO2.setType(GigType.LIVE_CONCERT);
        gigDTO2.setDate(LocalDate.of(2020, 11, 20));
    }

    @Test
    public void controllerInitializedCorrectly() {
        assertThat(gigController).isNotNull();
    }

    @Test
    public void shouldGetViewOfAllGigs() throws Exception {
        when(gigService.findAllGigs())
                .thenReturn(List.of(gigDTO1));

        this.mockMvc.perform(get("/gigs"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gigs"))
                .andExpect(view().name("list-gigs"))
                .andDo(print());
    }

    @Test
    public void shouldGetViewOfFindGigsById() throws Exception {

        when(gigService.findGigById(1L)).thenReturn(gigDTO1);
        mockMvc.perform(get("/gig/{id}/", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("list-gig"))
                .andReturn();

        verify(gigService, times(1)).findGigById(1L);
        verifyNoMoreInteractions(gigService);
    }

    @Test
    public void shouldGetAddGigCreateForm() throws Exception {

        mockMvc.perform(get("/addGig/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gig"))
                .andExpect(view().name("add-gig"))
                .andReturn();
           }

    @Test
    public void shouldRedirectToAllGigsViewWhenGigCreated() throws Exception {

        gigService.createGig(gigDTO1);
        mockMvc.perform(post("/add-gig"))
                .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/gigs"));

    }

    @Test
    public void shouldGetUpdateGigForm() throws Exception {
        when(gigService.findGigById(1L)).thenReturn(gigDTO1);

        mockMvc.perform(get("/updateGig/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gig"))
                .andExpect(view().name("update-gig"))
                .andReturn();
    }

    @Test
    public void shouldRedirectToAllGigsWhenGigUpdated() throws Exception {
        Long id = 1l;
        gigService.updateGig(id, gigDTO1);
        mockMvc.perform(post("/update-gig/{id}", id))
                .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/gigs"));

    }

    @Test
    public void shouldRedirectToGigsListAfterGigDeleted() throws Exception {
        Long id = 1l;
        gigService.deleteGig(id);
        mockMvc.perform(get("/delete-gig/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/gigs"));
    }

}
