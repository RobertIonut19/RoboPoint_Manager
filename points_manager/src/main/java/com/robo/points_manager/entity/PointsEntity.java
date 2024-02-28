package com.robo.points_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "points")
public class PointsEntity {

    @Id
    @Column(name = "round_number")
    private Long round_number;

    @Column(name= "points_generated")
    private Long points_generated;

    @Column(name = "points_consumed")
    private Long points_consumed;

    public PointsEntity() {
    }

    public PointsEntity(long round_number, long points_generated, long points_consumed) {
        this.round_number = round_number;
        this.points_generated = points_generated;
        this.points_consumed = points_consumed;
    }

    public Long getRound_number() {
        return round_number;
    }

    public void setRound_number(Long round_number) {
        this.round_number = round_number;
    }

    public Long getPoints_generated() {
        return points_generated;
    }

    public void setPoints_generated(Long points_generated) {
        this.points_generated = points_generated;
    }

    public Long getPoints_consumed() {
        return points_consumed;
    }

    public void setPoints_consumed(Long points_consumed) {
        this.points_consumed = points_consumed;
    }
}
