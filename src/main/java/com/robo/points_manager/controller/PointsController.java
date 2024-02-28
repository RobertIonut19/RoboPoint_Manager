package com.robo.points_manager.controller;

import com.robo.points_manager.entity.PointsEntity;
import com.robo.points_manager.service.PointsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointsController {

    private final PointsService pointsService;

    public PointsController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @GetMapping
    public List<PointsEntity> findAllPoints() {
        return pointsService.findAllPoints();
    }

    @PostMapping
    public PointsEntity savePoints(@RequestBody PointsEntity points) {
        return pointsService.savePoints(points);
    }

    @PutMapping("/{roundNumber}")
    public PointsEntity updatePoints(@PathVariable("roundNumber") Long roundNumber, @RequestBody PointsEntity points) {
        return pointsService.updatePoints(roundNumber, points);
    }

    @DeleteMapping("/{roundNumber}")
    public void deletePoints(@PathVariable("roundNumber") Long roundNumber) {
        pointsService.deletePoints(roundNumber);
    }

}
