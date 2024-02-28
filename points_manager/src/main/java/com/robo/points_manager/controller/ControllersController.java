package com.robo.points_manager.controller;

import com.robo.points_manager.entity.ControllerEntity;
import com.robo.points_manager.service.ControllerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controllers")
public class ControllersController {

    private final ControllerService controllerService;

    public ControllersController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping
    public List<ControllerEntity> findAllControllers() {
        return controllerService.findAllControllers();
    }

    @GetMapping("/{id}")
    public Optional<ControllerEntity> findControllerById(@PathVariable("id") Long id) {
        return Optional.ofNullable(controllerService.findControllerById(id));
    }

    @PostMapping
    public ControllerEntity saveController(@RequestBody ControllerEntity controllerEntity) {
        return controllerService.saveController(controllerEntity);
    }

    @PutMapping("/{id}")
    public ControllerEntity updateController(@PathVariable("id") Long id, @RequestBody ControllerEntity controllerEntity) {
        return controllerService.updateController(id, controllerEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteController(@PathVariable("id") Long id) {
        controllerService.deleteController(id);
    }
}
