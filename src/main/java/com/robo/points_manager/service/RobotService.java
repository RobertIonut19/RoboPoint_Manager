package com.robo.points_manager.service;

import com.robo.points_manager.entity.RobotEntity;

import java.util.List;

public interface RobotService {
   List<RobotEntity> findAllRobots();
    RobotEntity findRobotById(Long id);
    RobotEntity saveRobot(RobotEntity robot);
    RobotEntity updateRobot(Long id, RobotEntity robot);
    void deleteRobot(Long id);
}
