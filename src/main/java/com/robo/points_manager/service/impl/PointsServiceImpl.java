package com.robo.points_manager.service.impl;

import com.robo.points_manager.entity.PointsEntity;
import com.robo.points_manager.service.PointsService;
import com.robo.points_manager.repository.PointsRepository;
import org.springframework.stereotype.Service;

@Service
public class PointsServiceImpl implements PointsService {

    private final PointsRepository pointsRepository;

    public PointsServiceImpl(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }
    @Override
    public PointsEntity savePoints(PointsEntity points) {
        return pointsRepository.save(points);
    }

    @Override
    public void deletePoints(Long roundNumber) {
        pointsRepository.deleteById(roundNumber);
    }

    @Override
    public PointsEntity updatePoints(Long roundNumber, PointsEntity points) {
        return pointsRepository.save(points);
    }
}