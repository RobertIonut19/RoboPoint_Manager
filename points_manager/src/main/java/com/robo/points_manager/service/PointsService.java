package com.robo.points_manager.service;

import com.robo.points_manager.entity.PointsEntity;

import java.util.List;

public interface PointsService {
    PointsEntity savePoints(PointsEntity points);
    void deletePoints(Long roundNumber);
    PointsEntity updatePoints(Long roundNumber, PointsEntity points);

    List<PointsEntity> findAllPoints();

}
