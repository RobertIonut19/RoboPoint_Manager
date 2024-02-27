package com.robo.points_manager.service.impl;

import com.robo.points_manager.entity.RobotEntity;
import com.robo.points_manager.repository.RobotRepository;
import com.robo.points_manager.service.RobotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotServiceImpl implements RobotService {
    private final RobotRepository robotRepository;

    public RobotServiceImpl(RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }

    @Override
    public RobotEntity saveRobot(RobotEntity robot) {
        return robotRepository.save(robot);
    }
    @Override
    public void deleteRobot(Long id) {
        robotRepository.deleteById(id);
    }

    @Override
    public RobotEntity updateRobot(Long id, RobotEntity robot) {
        return robotRepository.save(robot);
    }

    @Override
    public List<RobotEntity> findAllRobots() {
        return robotRepository.findAll();
    }

    @Override
    public RobotEntity findRobotById(Long id) {
        return robotRepository.findById(id).orElse(null);
    }
}
