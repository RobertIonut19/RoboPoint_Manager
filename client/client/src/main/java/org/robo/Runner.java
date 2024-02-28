package org.robo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Runner {

    public Runner() {
    }

    public long doWorkload() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    public JSONObject getRobot(long serialNumber) {
        JSONObject robotData = null;
        try {
            URL url = new URL("http://localhost:8080/robots/" + serialNumber);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    // Parse JSON response into a JSONObject
                    robotData = new JSONObject(response.toString());
                }
            } else {
                System.out.println("GET request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return robotData;
    }

    public void updateRobot(long serialNumber, long activationRound, boolean activeState, long deactivationRound, boolean healthStatus, long pointsGenerated) {

        try {
            URL url = new URL("http://localhost:8080/robots/" + serialNumber);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject requestBody = new JSONObject();

            requestBody.put("serial_number", serialNumber);
            requestBody.put("activation_round", activationRound);
            requestBody.put("active_state", activeState);
            requestBody.put("deactivation_round", deactivationRound);
            requestBody.put("health_status", healthStatus);
            requestBody.put("points_generated", pointsGenerated);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePoints(long roundNumber, long pointsGenerated, long pointsConsumed) {
        try {
            URL url = new URL("http://localhost:8080/points/" + roundNumber);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject requestBody = new JSONObject();

            requestBody.put("round_number", roundNumber);
            requestBody.put("points_generated", pointsGenerated);
            requestBody.put("points_consumed", pointsConsumed);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int runWithNoController() {
        int contor = 1;
        int roundsNumber = 60;
        int robotCount = 10_000;
        for (; contor <= roundsNumber; contor++) {
            long pointsGeneratedPerRound = 0;
            long pointsConsumedPerRound = 0;
            for(int serialNumber = 1; serialNumber < robotCount; serialNumber ++) {
                JSONObject robotData = getRobot(serialNumber);
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
                            pointsGenerated -= 10;
                            pointsConsumedPerRound += 10;
                        } else {
                            pointsGenerated += 1;
                            pointsGeneratedPerRound += 1;
                        }
                    }
                    //update robot
                    updateRobot(serialNumber, activationRound, activeState, deactivationRound, healthStatus, pointsGenerated);

                }
            }
            //update points table
            updatePoints(contor, pointsGeneratedPerRound, pointsConsumedPerRound);

        }
        return contor;
    }
}
