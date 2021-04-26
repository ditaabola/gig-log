package lv.dita.service.impl;

import lv.dita.domain.Artist;
import lv.dita.domain.Gig;
import lv.dita.domain.Manager;
import lv.dita.enums.GigType;
import lv.dita.model.ArtistDTO;
import lv.dita.model.ManagerDTO;
import lv.dita.repositories.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerServiceImplTest {

    @Mock
    private ManagerRepository managerRepositoryMock;

    @InjectMocks
    private ManagerServiceImpl managertServiceMock;

    @Mock
    Manager manager = new Manager ();
    Manager manager2 = new Manager();
    ManagerDTO dto = new ManagerDTO();

    @Test
    void findAllManager() {
        when(managerRepositoryMock.findAll()).thenReturn(Arrays.asList(manager, manager2));
        assertEquals(2, managertServiceMock.findAllManagers().size());
    }

    @Test
    void findEmptyListIfNoManagers() {
        when(managerRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, managertServiceMock.findAllManagers().size());
    }

    @Test
    void findManagerById() {
        Long id = 2l;
        when(managerRepositoryMock.findById(id)).thenReturn(Optional.of(manager2));
        assertEquals(manager2.getName(), managertServiceMock.findManagerById(id).getName());
    }

    @Test
    void shouldCreateManagers() {
        Manager manager = new Manager();
        Long id = 1l;
        manager.setId(id);
        manager.setName("Manager");
        managerRepositoryMock.save(manager);
        when(managerRepositoryMock.findById(id)).thenReturn(Optional.of(manager));
        assertEquals("Manager", managertServiceMock.findManagerById(id).getName());
    }

    @Test
    void shouldUpdateManagers() {
        Manager manager = new Manager();
        Long id = 3l;
        manager.setId(id);
        manager.setName("Jamanger");
        when(managerRepositoryMock.findById(manager.getId())).thenReturn(Optional.of(manager));
        dto.setName("Manager");
        managertServiceMock.updateManager(id, dto);
        ArgumentCaptor<Manager> managerArgumentCaptor = ArgumentCaptor.forClass(Manager.class);
        verify(managerRepositoryMock).save(managerArgumentCaptor.capture());

        manager = managerArgumentCaptor.getValue();
        assertEquals("Manager", manager.getName());
    }

    @Test
    void shouldDeleteManager() {
        Manager manager = new Manager();
        manager.setId(3l);
        manager.setSurname("Manager");
        when(managerRepositoryMock.findById(manager.getId())).thenReturn(Optional.of(manager));
        managertServiceMock.deleteManager(manager.getId());
    }
}