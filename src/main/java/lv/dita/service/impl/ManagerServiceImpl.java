package lv.dita.service.impl;

import lv.dita.domain.Manager;
import lv.dita.exception.NotFoundException;
import lv.dita.model.ManagerDTO;
import lv.dita.repositories.ManagerRepository;
import lv.dita.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ManagerDTO> findAllManagers() {
        return managerRepository.findAll()
                .stream()
                .map(manager -> mapToDTO(manager, new ManagerDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ManagerDTO findManagerById (Long id) {
        return managerRepository.findById(id)
                .map(manager -> mapToDTO(manager, new ManagerDTO()))
                .orElseThrow(() -> new NotFoundException(String.format("Manager not found with ID %d", id)));
    }

    @Override
    public void createManager(ManagerDTO managerDTO) {
        Manager manager = new Manager();
        mapToEntity(managerDTO, manager);

        managerRepository.save(manager);
    }

    @Override
    public void updateManager(Long id, ManagerDTO managerDTO) {
        Manager manager = managerRepository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format("Manager not found with ID %d", id)));
        mapToEntity(managerDTO, manager);

        managerRepository.save(manager);
    }


    @Override
    public void deleteManager(Long id) {
        final Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Manager not found with ID %d", id)));

        managerRepository.deleteById(manager.getId());
    }

    private ManagerDTO mapToDTO(final Manager manager, final ManagerDTO managerDTO) {
        managerDTO.setId(manager.getId());
        managerDTO.setName(manager.getName());
        managerDTO.setSurname(manager.getSurname());
        managerDTO.setEmail(manager.getEmail());
        return managerDTO;
    }

    private Manager mapToEntity(final ManagerDTO managerDTO, final Manager manager) {
        manager.setName(managerDTO.getName());
        manager.setSurname(managerDTO.getSurname());
        manager.setEmail(managerDTO.getEmail());
        return manager;
    }
}