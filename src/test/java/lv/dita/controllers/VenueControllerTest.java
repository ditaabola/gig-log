package lv.dita.controllers;

import lv.dita.enums.VenueType;
import lv.dita.model.VenueDTO;
import lv.dita.service.impl.VenueServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(VenueController.class)
@AutoConfigureMockMvc
public class VenueControllerTest {

    @Autowired
    VenueController venueController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VenueServiceImpl venueService;

    VenueDTO venueDTO1;
    VenueDTO venueDTO2;

    @Before
    public void setUpVenue() throws Exception{
        venueDTO1 = new VenueDTO();
        venueDTO1.setId(1L);
        venueDTO1.setName("Venue1");
        venueDTO1.setCountry("Latvia");
        venueDTO1.setCity("Liepaja");
        venueDTO1.setType(VenueType.PRIVATE_VENUE);

        venueDTO2 = new VenueDTO();
        venueDTO2.setName("Venue2");
        venueDTO2.setId(2L);
        venueDTO2.setCountry("Bulgaria");
        venueDTO2.setCity("Sofia");
        venueDTO1.setType(VenueType.ALTERNATIVE_CLUB);
    }

    @Test
    public void controllerInitializedCorrectly() {
        assertThat(venueController).isNotNull();
    }

    @Test
    public void shouldGetViewOfAllVenues() throws Exception {
        when(venueService.findAllVenues())
                .thenReturn(List.of(venueDTO1));

        this.mockMvc.perform(get("/venues"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("venues"))
                .andExpect(model().size(1))
                .andExpect(view().name("list-venues"))
                .andDo(print());
    }

    @Test
    public void shouldGetViewOfFindVenueById() throws Exception {

        when(venueService.findVenueById(1L)).thenReturn(venueDTO1);
        mockMvc.perform(get("/venue/{id}/", 1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("venue", Matchers.hasProperty("name", Matchers.equalTo("Venue1"))))
                .andExpect(model().attribute("venue", Matchers.hasProperty("country", Matchers.equalTo("Latvia"))))
                .andExpect(model().attribute("venue", Matchers.hasProperty("city", Matchers.equalTo("Liepaja"))))
                .andExpect(view().name("list-venue"))
                .andReturn();

        verify(venueService, times(1)).findVenueById(1L);
        verifyNoMoreInteractions(venueService);
    }

    @Test
    public void shouldGetAddVenueCreateForm() throws Exception {

        mockMvc.perform(get("/addVenue/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("venue"))
                .andExpect(view().name("add-venue"))
                .andReturn();
           }

    @Test
    public void shouldRedirectToAllVenuesViewWhenVenueCreated() throws Exception {

        venueService.createVenue(venueDTO1);
        mockMvc.perform(post("/add-venue"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/venues"));

    }

    @Test
    public void shouldGetUpdateVenueForm() throws Exception {
        when(venueService.findVenueById(1L)).thenReturn(venueDTO1);

        mockMvc.perform(get("/updateVenue/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("venue"))
                .andExpect(view().name("update-venue"))
                .andReturn();
    }

    @Test
    public void shouldRedirectToAllVenuesViewWhenVenueUpdated() throws Exception {
        Long id = 1l;
        venueService.updateVenue(id, venueDTO1);
        mockMvc.perform(post("/update-venue/{id}", id))
                .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/venues"));

    }

    @Test
    public void shouldRedirectToVenuesListAfterVenuetDeleted() throws Exception {
        Long id = 1l;
        venueService.deleteVenue(id);
        mockMvc.perform(get("/delete-venue/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/venues"));
    }

}
