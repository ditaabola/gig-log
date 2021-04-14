package lv.dita.service.impl;

import lv.dita.model.Manager;
import lv.dita.repositories.ManagerRepository;
import lv.dita.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceImplTest {

    @InjectMocks
    private ManagerServiceImpl managerService;

    @Mock
    private ManagerRepository managerRepository;


    @Test
    public void testIfEmptyListReturnedWhenNoManagersCreated() {
        Mockito.when(managerRepository.findAll()).thenReturn(new ArrayList<>());
            assertEquals(new ArrayList<>(), managerService.findAllManagers());
    }

    @Test
    public void testIfFindAllReturnsAllCreatedManagers() {

        Mockito.when(managerRepository.findAll()).thenReturn(Arrays.asList(
                new Manager(1L, "John", "Manager", "john@manager.com"),
                new Manager(2L, "Jane", "Manager","jane@manager.com"),
                new Manager(3L, "Jake", "Manager", "jake@manager.com")
        ));

        List<Manager> allManagersList = managerService.findAllManagers();

        assertEquals(3, allManagersList.size());
        assertEquals("John", allManagersList.get(0).getName());
        assertEquals("Manager", allManagersList.get(1).getSurname());
        assertEquals("jake@manager.com", allManagersList.get(2).getEmail());

    }

    @Test
    public void createManager() {
    }

    @Test
    public void updateManager() {
    }

    @Test
    public void deleteManager() {
    }

}