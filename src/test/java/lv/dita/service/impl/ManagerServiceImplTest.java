package lv.dita.service.impl;

import lv.dita.model.Manager;
import lv.dita.repositories.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    Manager manager = new Manager (1l, "John", "Manager", "juuk@juuk.com");
    Manager manager2 = new Manager(2l, "Jake", "Jamanger", "manta@manta.com");

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
    void createManagers() {
        Manager createManager = new Manager(1l, "Sniedze", "Selfe", "sniedze@sniedze.juuk");
        Long id = createManager.getId();
        managerRepositoryMock.save(createManager);
        managerRepositoryMock.save(manager2);
        when(managerRepositoryMock.findById(id)).thenReturn(Optional.of(createManager));
        assertEquals("Sniedze", managertServiceMock.findManagerById(id).getName());

    }

    @Test
    void updateManagers() {
        Long id = 2l;
        when(managerRepositoryMock.findById(id)).thenReturn(Optional.of(manager2));
        manager2.setName("NewManager");
        managerRepositoryMock.save(manager2);
        assertEquals("NewManager", managertServiceMock.findManagerById(id).getName());
    }
}