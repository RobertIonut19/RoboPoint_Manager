package com.robo.points_manager.service.impl;

import com.robo.points_manager.entity.ControllerEntity;
import com.robo.points_manager.repository.ControllerRepository;
import com.robo.points_manager.service.ControllerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControllerServiceImpl implements ControllerService {

    private final ControllerRepository controllerRepository;

    public ControllerServiceImpl(ControllerRepository controllerRepository) {
        this.controllerRepository = controllerRepository;
    }

    @Override
    public List<ControllerEntity> findAllControllers() {
        return controllerRepository.findAll();
    }

    @Override
    public ControllerEntity findControllerById(Long id) {
        return controllerRepository.findById(id).orElse(null);
    }

    @Override
    public ControllerEntity saveController(ControllerEntity controller) {
        return controllerRepository.save(controller);
    }

    @Override
    public ControllerEntity updateController(Long id, ControllerEntity controller) {
        return controllerRepository.save(controller);
    }

    @Override
    public void deleteController(Long id) {
        controllerRepository.deleteById(id);
    }
}
