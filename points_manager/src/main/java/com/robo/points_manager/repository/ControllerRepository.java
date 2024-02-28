package com.robo.points_manager.repository;

import com.robo.points_manager.entity.ControllerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerRepository extends JpaRepository<ControllerEntity, Long> {
}
