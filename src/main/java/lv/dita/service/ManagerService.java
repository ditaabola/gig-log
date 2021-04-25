package lv.dita.service;

import lv.dita.domain.Manager;
import lv.dita.model.ManagerDTO;

import java.util.List;

public interface ManagerService {

    public List<ManagerDTO> findAllManagers();

    public ManagerDTO findManagerById(Long id);

    public void createManager(ManagerDTO managerDTO);

    public void updateManager(Long id, ManagerDTO managerDTO);

    public void deleteManager(Long id);
}
