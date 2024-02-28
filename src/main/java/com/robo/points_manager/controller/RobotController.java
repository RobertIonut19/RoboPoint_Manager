package com.robo.points_manager.controller;

import com.robo.points_manager.entity.RobotEntity;
import com.robo.points_manager.service.RobotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/robots")
public class RobotController {

    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @GetMapping
    public List<RobotEntity> findAllRobots() {
        return robotService.findAllRobots();
    }

    @GetMapping("/{id}")
    public Optional<RobotEntity> findRobotById(@PathVariable("id") Long id) {
        return Optional.ofNullable(robotService.findRobotById(id));
    }

    @GetMapping("/points")
    public Long findOverallRobotPoints() {
        return robotService.findOverallRobotPoints();
    }

    @PostMapping
    public RobotEntity saveRobot(@RequestBody RobotEntity robot) {
        return robotService.saveRobot(robot);
    }

    @PutMapping("/{id}")
    public RobotEntity updateRobot(@PathVariable("id") Long id, @RequestBody RobotEntity robot) {
        return robotService.updateRobot(id, robot);
    }

    @DeleteMapping("/{id}")
    public void deleteRobot(@PathVariable("id") Long id) {
        robotService.deleteRobot(id);
    }
}
