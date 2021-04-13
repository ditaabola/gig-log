package gigLog;

import lv.dita.exception.NotFoundException;
import lv.dita.model.Artist;
import lv.dita.model.Manager;
import lv.dita.repositories.ArtistRepository;
import lv.dita.repositories.ManagerRepository;
import lv.dita.service.impl.ArtistServiceImpl;
import lv.dita.service.impl.ManagerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTest {

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
    public void testIfFindByIdReturnsOneManager() {
        Mockito.when(managerRepository.findById(1L)).thenReturn(Optional.of(new Manager("Laura", "Themanager", "laura@laura.com")));

        Manager manager = managerService.findManagerById(1l);

        assertEquals("Laura", manager.getName());
    }

    @Test(expected = NotFoundException.class)
    public void testIfNotFoundExceptionErrorThrownIfNoManagerWithIdFound() {
        Mockito.when(managerRepository.findById(1L)).thenReturn(Optional.empty());

        Manager manager = managerService.findManagerById(1l);

    }


    @Test
    public void testIfManagerUpdated() {
        final Manager manager = new Manager(1L, "Carl", "Jaman", "carl@jaman.com");

        manager.setEmail("carl@carl.com");
        managerService.updateManager(manager);
        assertEquals("carl@carl.com", manager.getEmail());

    }

    @Test
    public void testIfManagerDeleted() {
        final Manager manager = new Manager(1L, "Carl", "Jaman", "carl@jaman.com");

        List<Manager> managerList = Arrays.asList(manager);
        Long managerId = manager.getId();
        Mockito.when(managerRepository.findById(1L)).thenReturn(Optional.of(manager));
        managerService.deleteManager(managerId);

        verify(managerRepository, times(1)).deleteById(managerId);
        }
}