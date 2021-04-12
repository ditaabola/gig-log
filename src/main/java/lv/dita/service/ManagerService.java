package lv.dita.service;

import lv.dita.model.Manager;
import lv.dita.exception.NotFoundException;
import java.util.List;

public interface ManagerService {

    public List<Manager> findAllManagers();

    public Manager findManagerById(Long id) throws NotFoundException;

    public void createManager(Manager manager);

    public void updateManager(Manager manager);

    public void deleteManager(Long id);
}
