package lv.dita.service.impl;

import lv.dita.domain.Artist;
import lv.dita.domain.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.model.ManagerDTO;
import lv.dita.repositories.ManagerRepository;
import lv.dita.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> findAllManagers() {
        List<Manager> managerList = (List<Manager>) managerRepository.findAll();

        if(!managerList.isEmpty()){
            return managerList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Manager findManagerById (Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Manager not found with ID %d", id)));
    }


    @Override
    public void createManager(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public void updateManager(Manager manager) {
        managerRepository.save(manager);
    }


    @Override
    public void deleteManager(Long id) {
        final Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Manager not found with ID %d", id)));

        managerRepository.deleteById(manager.getId());
    }
}