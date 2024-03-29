package com.robo.points_manager.repository;

import com.robo.points_manager.entity.RobotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<RobotEntity, Long> {
}
