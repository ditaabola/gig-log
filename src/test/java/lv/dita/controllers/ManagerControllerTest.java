package lv.dita.controllers;

import lv.dita.domain.Artist;
import lv.dita.domain.Manager;
import lv.dita.model.ManagerDTO;
import lv.dita.service.impl.ManagerServiceImpl;
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
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ManagerController.class)
@AutoConfigureMockMvc
public class ManagerControllerTest {

    @Autowired
    ManagerController managerController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ManagerServiceImpl managerService;

    ManagerDTO manager1;
    ManagerDTO manager2;
    Manager manager3;
    Artist artist1;
    Artist artist2;
    Artist artist3;

    List<Artist> artists = new ArrayList<>();


    @Before
    public void setUpManager() throws Exception{
        manager1 = new ManagerDTO();
        manager1.setName("John");
        manager1.setSurname("Manager");
        manager1.setEmail("john@manager.com");
        manager2 = new ManagerDTO();
        manager2.setName("Laila");
        manager2.setSurname("Jamanger");
        manager2.setEmail("laila@jamanger.com");


    }

    @Test
    public void controllerInitializedCorrectly() {

        assertThat(managerController).isNotNull();
    }

    @Test
    public void shouldGetViewOfAllManagers() throws Exception {
        when(managerService.findAllManagers())
                .thenReturn(List.of(manager1, manager2));

        this.mockMvc.perform(get("/managers"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("managers"))
                .andExpect(model().size(1))
                .andExpect(view().name("list-managers"))
                .andDo(print());
    }

    @Test
    public void shouldGetViewOfFindManagerById() throws Exception {

        when(managerService.findManagerById(1L)).thenReturn(manager1);
        mockMvc.perform(get("/manager/{id}/", 1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("manager", Matchers.hasProperty("name", Matchers.equalTo("John"))))
                .andExpect(model().attribute("manager", Matchers.hasProperty("surname", Matchers.equalTo("Manager"))))
                .andExpect(model().attribute("manager", Matchers.hasProperty("email", Matchers.equalTo("john@manager.com"))))
                .andExpect(view().name("list-manager"))
                .andReturn();

        verify(managerService, times(1)).findManagerById(1L);
        verifyNoMoreInteractions(managerService);
    }

    @Test
    public void shouldGetAddManagerCreateForm() throws Exception {

        mockMvc.perform(get("/addManager/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("manager"))
                .andExpect(view().name("add-manager"))
                .andReturn();
           }

    @Test
    public void shouldRedirectToAllArtistsViewWhenManagerCreated() throws Exception {

        managerService.createManager(manager2);
        mockMvc.perform(post("/add-manager"))
                .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/managers"));

    }

    @Test
    public void shouldGetUpdateManagerForm() throws Exception {
        when(managerService.findManagerById(2L)).thenReturn(manager2);

        mockMvc.perform(get("/updateManager/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(view().name("update-manager"))
                .andExpect(model().attributeExists("manager"))
                .andReturn();
    }

    @Test
    public void shouldRedirectToAllManagersViewWhenManagerUpdated() throws Exception {
        Long id = 1l;
        managerService.updateManager(id, manager1);
        mockMvc.perform(post("/update-manager/{id}", id))
                .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/managers"));

    }

    @Test
    public void shouldRedirectToManagersListAfterManagerDeleted() throws Exception {
        Long id = 1l;
        managerService.deleteManager(id);
        mockMvc.perform(get("/delete-manager/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/managers"));
    }

}
