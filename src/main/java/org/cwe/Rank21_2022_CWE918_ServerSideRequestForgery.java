package org.cwe;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class Rank21_2022_CWE918_ServerSideRequestForgery {
    public static void main(String[] args) {
        String url = "http://example.com"; // User-supplied URL

        // Bad method: Directly making a request to the user-supplied URL
        bad(url);

        System.out.println("------------------------");

        // Good method: Validating and restricting the allowed URLs
        good(url);
    }

    private static void bad(String url) {
        try {
            URL userURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) userURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Process the response
            // ...
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void good(String url) {
        // Whitelist of allowed URLs
        String[] allowedDomains = { "example.com", "trusted-site.com" };

        boolean isValidURL = Arrays.stream(allowedDomains).anyMatch(url::contains);

        if (isValidURL) {
            try {
                URL userURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) userURL.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Process the response
                // ...
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.err.println("Invalid URL: " + url);
        }
    }
}

