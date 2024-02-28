package org.robo;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import org.json.JSONObject;

public class Initializer {

    private int robotsCount;
    private int controllerCount;
    private int roundsNumber;
    public Initializer(int controllerCount) {
        this.robotsCount = 10_000;
        this.controllerCount = controllerCount;
        this.roundsNumber = 60;

        initializeRobots();
        initializePoints();
        initializeControllers();
    }
    public void initializeRobots() {
        int id = 1;
        try {
            while (id <= robotsCount) {
                // Generate random activation and deactivation rounds
                Random random = new Random();
                int activationRound = random.nextInt(30) + 1;
                int deactivationRound = random.nextInt(31) + 30;

                URL url = new URL("http://localhost:8080/robots");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create JSON body
                JSONObject requestBody = new JSONObject();
                requestBody.put("serial_number", id);
                requestBody.put("activation_round", activationRound);
                requestBody.put("deactivation_round", deactivationRound);
                requestBody.put("health_status", true);
                requestBody.put("points_generated", 0);
                requestBody.put("active_state", activationRound == 1);

                // Write JSON to connection output stream
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = requestBody.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                System.out.println("Response code: " + responseCode);
                id += 1;

                // Disconnect the connection after each request
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializePoints() {
        int contor = 1;
        try {
            while (contor <= roundsNumber) {
                // Create URL object (URL to the API endpoint
                URL url = new URL("http://localhost:8080/points");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create JSON body
                JSONObject requestBody = new JSONObject();
                requestBody.put("round_number", contor);
                requestBody.put("points_generated", 0);
                requestBody.put("points_consumed", 0);

                // Write JSON to connection output stream
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = requestBody.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                System.out.println("Response code: " + responseCode);

                // Disconnect the connection after each request
                connection.disconnect();

                contor += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeControllers() {
        int id = 1;
        try {
            while (id <= controllerCount) {
                URL url = new URL("http://localhost:8080/controllers");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create JSON body
                JSONObject requestBody = new JSONObject();
                requestBody.put("id", id);
                requestBody.put("interactions", 0);
                requestBody.put("points_consumed", 0);

                // Write JSON to connection output stream
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = requestBody.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                System.out.println("Response code: " + responseCode);
                id += 1;

                // Disconnect the connection after each request
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
