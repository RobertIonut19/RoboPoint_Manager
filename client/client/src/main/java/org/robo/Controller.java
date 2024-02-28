package org.robo;

import org.json.JSONObject;

import java.util.Random;

public class Controller {
    private final DML dml;

    public Controller() {
        this.dml = new DML();
    }

    public long ControllerAction() {
        long numberOfReparations = 0;
        int contor = 1;
        int maxInteraction = 100;
        Random random = new Random();
        while(contor <= maxInteraction) {
            // Get a random robot (1-10_000)
            int serialNumber = random.nextInt(10_000) + 1;
            contor++;
            if (contor > maxInteraction)
                continue;
            // Get robot data
            JSONObject robotData = dml.getRobot(serialNumber);
            boolean activeState = robotData.getBoolean("active_state");
            boolean healthStatus = robotData.getBoolean("health_status");
            long activationRound = robotData.getLong("activation_round");
            long deactivationRound = robotData.getLong("deactivation_round");
            long pointsGenerated = robotData.getLong("points_generated");
            contor++;
            if (contor > maxInteraction)
                continue;
            if (activeState && !healthStatus) {
                // If robot is active and not healthy, deactivate it
                dml.updateRobot(serialNumber, activationRound, true, deactivationRound, true, pointsGenerated);
                numberOfReparations++;
            }
            contor++;
        }
        return numberOfReparations;
    }

    public long betterControllerAction(int lowerBoundary, int upperBoundary) {
        long numberOfReparations = 0;
        int contor = 1;
        int maxInteraction = 100;
        Random random = new Random();
        while(contor <= maxInteraction) {
            // Get a random robot (1-10_000)
            int serialNumber = random.nextInt(upperBoundary - lowerBoundary) + lowerBoundary;
            contor++;
            if (contor > maxInteraction)
                continue;
            // Get robot data
            JSONObject robotData = dml.getRobot(serialNumber);
            boolean activeState = robotData.getBoolean("active_state");
            boolean healthStatus = robotData.getBoolean("health_status");
            long activationRound = robotData.getLong("activation_round");
            long deactivationRound = robotData.getLong("deactivation_round");
            long pointsGenerated = robotData.getLong("points_generated");
            contor++;
            if (contor > maxInteraction)
                continue;
            if (activeState && !healthStatus) {
                // If robot is active and not healthy, deactivate it
                dml.updateRobot(serialNumber, activationRound, true, deactivationRound, true, pointsGenerated);
                numberOfReparations++;
            }
            contor++;
        }
        return numberOfReparations;
    }

}
