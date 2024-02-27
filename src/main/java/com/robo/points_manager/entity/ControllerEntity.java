package com.robo.points_manager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "controller")
public class ControllerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "interactions")
    private Long interactions;

    @Column(name = "points_consumed")
    private Long points_consumed;

    public ControllerEntity() {
    }

    public ControllerEntity(long id, long interactions, long points_consumed) {
        this.id = id;
        this.interactions = interactions;
        this.points_consumed = points_consumed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInteractions() {
        return interactions;
    }

    public void setInteractions(long interactions) {
        this.interactions = interactions;
    }

    public long getPoints_consumed() {
        return points_consumed;
    }

    public void setPoints_consumed(long points_consumed) {
        this.points_consumed = points_consumed;
    }
}
