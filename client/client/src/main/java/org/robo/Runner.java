package org.robo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Runner {

    private final DML dml;

    public Runner() {
        this.dml = new DML();
    }

    public long doWorkload() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    public int runWithNoController() {
        int contor = 1;
        int roundsNumber = 60;
        int robotCount = 10_000;
        int totalPoints = 0;
        for (; contor <= roundsNumber; contor++) {
            long pointsGeneratedPerRound = 0;
            long pointsConsumedPerRound = 0;
            for(int serialNumber = 1; serialNumber < robotCount; serialNumber ++) {
                JSONObject robotData = dml.getRobot(serialNumber);
                if (robotData != null) {
                    boolean activeState = robotData.getBoolean("active_state");
                    boolean healthStatus = robotData.getBoolean("health_status");

                    long activationRound = robotData.getLong("activation_round");
                    long deactivationRound = robotData.getLong("deactivation_round");
                    long pointsGenerated = robotData.getLong("points_generated");

                    if (!activeState && contor >= activationRound) {
                        activeState = true;
                    }
                    if (activeState && contor > deactivationRound) {
                        activeState = false;
                    }

                    if (healthStatus) {
                        long workload = doWorkload();
                        if (workload > 90) {
                            healthStatus = false;
                            totalPoints -= 10;
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                        } else {
                            pointsGenerated += 1;
                            pointsGeneratedPerRound += 1;
                            totalPoints += 1;
                        }
                    }
                    else {
                        pointsGenerated -= 10;
                        pointsConsumedPerRound += 10;
                        totalPoints -= 10;

                    }
                    //update robot
                    dml.updateRobot(serialNumber, activationRound, activeState, deactivationRound, healthStatus, pointsGenerated);

                }
            }
            //update points table
            dml.updatePoints(contor, pointsGeneratedPerRound, pointsConsumedPerRound);

        }
        return totalPoints;
    }

    public long runWithNControllers(int n) {
        int roundContor = 1;
        int roundsNumber = 60;
        int robotCount = 10_000;
        int totalPoints = 0;
        for(; roundContor <= roundsNumber; ++roundContor) {

            long pointsGeneratedPerRound = 0;
            long pointsConsumedPerRound = 0;

            for(int serialNumber = 1; serialNumber < robotCount; ++serialNumber) {
                JSONObject robotData = dml.getRobot(serialNumber);
                if(robotData != null) {
                    boolean activeState = robotData.getBoolean("active_state");
                    boolean healthStatus = robotData.getBoolean("health_status");
                    long activationRound = robotData.getLong("activation_round");
                    long deactivationRound = robotData.getLong("deactivation_round");
                    long pointsGenerated = robotData.getLong("points_generated");
                    if(!activeState && roundContor >= activationRound) {
                        activeState = true;
                    }
                    if(activeState && roundContor > deactivationRound) {
                        activeState = false;
                    }
                    if(healthStatus) {
                        long workload = doWorkload();
                        if(workload > 90) {
                            healthStatus = false;
                            totalPoints -= 10;
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                        } else {
                            pointsGenerated += 1;
                            pointsGeneratedPerRound += 1;
                            totalPoints += 1;
                        }
                    } else {
                        if (activeState) {
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                            totalPoints -= 10;
                        }
                    }
                    dml.updateRobot(serialNumber, activationRound, activeState, deactivationRound, healthStatus, pointsGenerated);
                }
            }

            for (int i = 0; i < n; ++i) {
                Controller controller = new Controller();
                controller.ControllerAction();
            }
            pointsConsumedPerRound += n * 20L;
            dml.updatePoints(roundContor, pointsGeneratedPerRound, pointsConsumedPerRound);

        }
        return totalPoints;
    }

    public long runBetterWithNControllers(int n) {
        int roundContor = 1;
        int roundsNumber = 60;
        int robotCount = 10_000;
        int totalPoints = 0;
        for(; roundContor <= roundsNumber; ++roundContor) {

            long pointsGeneratedPerRound = 0;
            long pointsConsumedPerRound = 0;

            for(int serialNumber = 1; serialNumber < robotCount; ++serialNumber) {
                JSONObject robotData = dml.getRobot(serialNumber);
                if(robotData != null) {
                    boolean activeState = robotData.getBoolean("active_state");
                    boolean healthStatus = robotData.getBoolean("health_status");
                    long activationRound = robotData.getLong("activation_round");
                    long deactivationRound = robotData.getLong("deactivation_round");
                    long pointsGenerated = robotData.getLong("points_generated");
                    if(!activeState && roundContor >= activationRound) {
                        activeState = true;
                    }
                    if(activeState && roundContor > deactivationRound) {
                        activeState = false;
                    }
                    if(healthStatus) {
                        long workload = doWorkload();
                        if(workload > 90) {
                            healthStatus = false;
                            totalPoints -= 10;
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                        } else {
                            pointsGenerated += 1;
                            pointsGeneratedPerRound += 1;
                            totalPoints += 1;
                        }
                    } else {
                        if (activeState) {
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                            totalPoints -= 10;
                        }
                    }
                    dml.updateRobot(serialNumber, activationRound, activeState, deactivationRound, healthStatus, pointsGenerated);
                }
            }

            for (int i = 0; i < n; ++i) {
                Controller controller = new Controller();
                //split range 1-10_000 into n ranges
                int lowerBoundary = (i * robotCount) / n + 1;
                int upperBoundary = ((i + 1) * robotCount) / n;
                controller.betterControllerAction(lowerBoundary, upperBoundary);
            }
            pointsConsumedPerRound += n * 20L;
            dml.updatePoints(roundContor, pointsGeneratedPerRound, pointsConsumedPerRound);

        }
        return totalPoints;
    }

    public long runBetterWithNControllers2(int n) {
        int roundContor = 1;
        int roundsNumber = 60;
        int robotCount = 10_000;
        int totalPoints = 0;
        for(; roundContor <= roundsNumber; ++roundContor) {

            long pointsGeneratedPerRound = 0;
            long pointsConsumedPerRound = 0;

            for(int serialNumber = 1; serialNumber < robotCount; ++serialNumber) {
                JSONObject robotData = dml.getRobot(serialNumber);
                if(robotData != null) {
                    boolean activeState = robotData.getBoolean("active_state");
                    boolean healthStatus = robotData.getBoolean("health_status");
                    long activationRound = robotData.getLong("activation_round");
                    long deactivationRound = robotData.getLong("deactivation_round");
                    long pointsGenerated = robotData.getLong("points_generated");
                    if(!activeState && roundContor >= activationRound) {
                        activeState = true;
                    }
                    if(activeState && roundContor > deactivationRound) {
                        activeState = false;
                    }
                    if(healthStatus) {
                        long workload = doWorkload();
                        if(workload > 90) {
                            healthStatus = false;
                            totalPoints -= 10;
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                        } else {
                            pointsGenerated += 1;
                            pointsGeneratedPerRound += 1;
                            totalPoints += 1;
                        }
                    } else {
                        if (activeState) {
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                            totalPoints -= 10;
                        }
                    }
                    dml.updateRobot(serialNumber, activationRound, activeState, deactivationRound, healthStatus, pointsGenerated);
                }
            }

            if (roundContor % 5 == 0) {
                for (int i = 0; i < n; ++i) {
                    Controller controller = new Controller();
                    //split range 1-10_000 into n ranges
                    int lowerBoundary = (i * robotCount) / n + 1;
                    int upperBoundary = ((i + 1) * robotCount) / n;
                    controller.betterControllerAction(lowerBoundary, upperBoundary);
                }
                pointsConsumedPerRound += n * 20L;
            }
            dml.updatePoints(roundContor, pointsGeneratedPerRound, pointsConsumedPerRound);

        }
        return totalPoints;
    }
}
