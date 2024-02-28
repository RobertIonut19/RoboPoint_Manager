package org.robo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DML {
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

}
