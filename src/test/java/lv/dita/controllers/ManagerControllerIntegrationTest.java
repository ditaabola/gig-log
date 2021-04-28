package lv.dita.controllers;

import lv.dita.domain.Manager;
import lv.dita.model.ManagerDTO;
import lv.dita.service.impl.ManagerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ManagerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ManagerServiceImpl managerService;

    @Test
    public void testManagerController () throws Exception{
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "managers", String.class)).isNotBlank();

    }

    @Test
    public void testAddManagerPageRedirectsToManagersAfterAddingManagerAndManagerNotNull(){
        Manager manager = new Manager();
        manager.setName("John");
        manager.setSurname("Manager");
        manager.setEmail("john@manager.com");

        ResponseEntity<String> responseEntity = this.testRestTemplate
                .postForEntity("http://localhost:"+port+"add-manager", manager, String.class);
        assertEquals(302, responseEntity.getStatusCodeValue());
        assertNotNull(manager);
    }

    @Test
    public void testFindAllManagers() {

        List<ManagerDTO> managers = managerService.findAllManagers();

        assertThat(managers.size(), is(greaterThanOrEqualTo(0)));
    }
}
