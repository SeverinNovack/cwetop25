package org.cwe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rank17_2022_CWE77_CommandInjection {
    public static void main(String[] args) {
        String userInput = "google.com & cmd /c echo malicious content > malicious.txt"; // User input vulnerable to OS command injection

        bad(userInput);

        System.out.println("------------------------");

        good(userInput);
    }

    private static void bad(String userInput) {
        // Bad example: Vulnerable to OS command injection
        String command = "cmd /C ping " + userInput;
        executeCommand(command);
    }

    private static void good(String userInput) {
        // Good example: Secure against OS command injection
        String sanitizedInput = sanitizeInput(userInput);
        String command = "cmd /C ping " + sanitizedInput;
        executeCommand(command);
    }

    private static void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String sanitizeInput(String input) {
        // Sanitize input by removing any potentially dangerous characters
        return input.replaceAll("[^a-zA-Z0-9.-]", "");
    }
}
