package com.robo.points_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "robot")
public class RobotEntity {

    @Id
    @Column(name = "serial_number")
    private Long serial_number;

    @Column(name = "activation_round")
    private Long activation_round;

    @Column(name = "deactivation_round")
    private Long deactivation_round;

    @Column(name = "active_state")
    private Boolean active_state;

    @Column(name = "health_status")
    private Boolean health_status;

    @Column(name = "points_generated")
    private Long points_generated;

    public RobotEntity() {
    }

    public RobotEntity(long serial_number, long activation_round, long deactivation_round, boolean active_state, boolean health_status, long points_generated) {
        this.serial_number = serial_number;
        this.activation_round = activation_round;
        this.deactivation_round = deactivation_round;
        this.active_state = active_state;
        this.health_status = health_status;
        this.points_generated = points_generated;
    }

    public Long getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(Long serial_number) {
        this.serial_number = serial_number;
    }

    public Long getActivation_round() {
        return activation_round;
    }

    public void setActivation_round(Long activation_round) {
        this.activation_round = activation_round;
    }

    public Long getDeactivation_round() {
        return deactivation_round;
    }

    public void setDeactivation_round(Long deactivation_round) {
        this.deactivation_round = deactivation_round;
    }

    public Boolean getActive_state() {
        return active_state;
    }

    public void setActive_state(Boolean active_state) {
        this.active_state = active_state;
    }

    public Boolean getHealth_status() {
        return health_status;
    }

    public void setHealth_status(Boolean health_status) {
        this.health_status = health_status;
    }

    public Long getPoints_generated() {
        return points_generated;
    }

    public void setPoints_generated(Long points_generated) {
        this.points_generated = points_generated;
    }
}
