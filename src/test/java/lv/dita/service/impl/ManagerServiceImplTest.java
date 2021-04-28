package lv.dita.service.impl;

import lv.dita.domain.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.model.ManagerDTO;
import lv.dita.repositories.ManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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

    @BeforeEach

    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(managertServiceMock);
    }

    @Test
    void shouldFindManagerListWhenAllManagers() {
        when(managerRepositoryMock.findAll()).thenReturn(Arrays.asList(manager, manager2));
        assertEquals(2, managertServiceMock.findAllManagers().size());
    }

    @Test
    void shouldFindEmptyListIfNoManagers() {
        when(managerRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, managertServiceMock.findAllManagers().size());
    }

    @Test
    void shouldFindManagerById() {
        Long id = 2l;
        manager2.setId(id);
        manager2.setName("Lane");
        when(managerRepositoryMock.findById(id)).thenReturn(Optional.of(manager2));
        assertEquals("Lane", managertServiceMock.findManagerById(id).getName());
    }

    @Test
    void shouldThrowExceptionIfCannotBeFound() {
        Long id = 3l;
        when(managerRepositoryMock.findById(id)).thenThrow(new NotFoundException(String.format("Manager not found with ID %d", id)));

        assertThatThrownBy(()-> managertServiceMock.findManagerById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Manager not found with ID %d", id));

        verify(managerRepositoryMock, never()).save(any());
    }


    @Test
    void shouldCreateManagers() {
        dto.setName("TheManager");
        managertServiceMock.createManager(dto);
        ArgumentCaptor<Manager> managerArgumentCaptor = ArgumentCaptor.forClass(Manager.class);
        verify(managerRepositoryMock).save(managerArgumentCaptor.capture());

        Manager createdManager = managerArgumentCaptor.getValue();
        assertEquals("TheManager", createdManager.getName());
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
        manager.setId(3l);
        when(managerRepositoryMock.findById(manager.getId())).thenReturn(Optional.of(manager));
        managertServiceMock.deleteManager(manager.getId());
        verify(managerRepositoryMock, times(1)).deleteById(manager.getId());


    }
}