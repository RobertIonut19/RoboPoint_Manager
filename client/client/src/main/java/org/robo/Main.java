package org.robo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // Robots Initialization
        Initializer initializer = new Initializer();

        // Controllers Initialization

        // Run
        Runner runner = new Runner();
        runner.runWithNoController();

    }
}