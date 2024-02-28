package org.robo;

public class Robot {
    private Long serialNumber;
    private Long activationRound;
    private Boolean activeState;
    private Long deactivationRound;
    private Boolean healthStatus;
    private Long pointsGenerated;

    public Robot() {
    }

    public Robot(Long serialNumber, Long activationRound, Boolean activeState, Long deactivationRound, Boolean healthStatus, Long pointsGenerated) {
        this.serialNumber = serialNumber;
        this.activationRound = activationRound;
        this.activeState = activeState;
        this.deactivationRound = deactivationRound;
        this.healthStatus = healthStatus;
        this.pointsGenerated = pointsGenerated;
    }


}
