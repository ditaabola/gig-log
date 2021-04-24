package lv.dita.service;

import lv.dita.domain.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.model.ManagerDTO;

import java.util.List;

public interface ManagerService {

    public List<Manager> findAllManagers();

    public Manager findManagerById(Long id) throws NotFoundException;

    public void createManager(Manager manager);

    public void updateManager(Manager manager);

    public void deleteManager(Long id);
}
