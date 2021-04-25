package lv.dita.service;

import lv.dita.domain.Manager;

import java.util.List;

public interface ManagerService {

    public List<Manager> findAllManagers();

    public Manager findManagerById(Long id);

    public void createManager(Manager manager);

    public void updateManager(Manager manager);

    public void deleteManager(Long id);
}
