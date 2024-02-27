package com.robo.points_manager.repository;

import com.robo.points_manager.entity.PointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<PointsEntity, Long> {
}
