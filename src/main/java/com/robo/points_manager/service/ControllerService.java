package com.robo.points_manager.service;

import com.robo.points_manager.entity.ControllerEntity;

import java.util.List;

public interface ControllerService {
    List<ControllerEntity> findAllControllers();
    ControllerEntity findControllerById(Long id);
    ControllerEntity saveController(ControllerEntity controller);
    ControllerEntity updateController(Long id, ControllerEntity controller);
    void deleteController(Long id);
}
