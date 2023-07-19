package org.cwe;

import java.sql.*;

public class Rank03_2022_CWE89_SQLInjection {
    public static void main(String[] args) {
        String username = "admin"; // Value to be used in the SQL query

        good(username);
        System.out.println("------------------------");
        bad(username);
    }

    private static void good(String username) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM users WHERE username = ?";
            ResultSet resultSet;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                // Set the username as a parameter in the prepared statement
                statement.setString(1, username);

                // Execute the query
                resultSet = statement.executeQuery();
            }

            // Process the results
            while (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                System.out.println("Retrieved username: " + retrievedUsername);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred during the SQL query: " + e.getMessage());
        }
    }

    private static void bad(String username) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet resultSet;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                // Execute the query
                resultSet = statement.executeQuery();
            }

            // Process the results
            while (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                System.out.println("Retrieved username: " + retrievedUsername);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred during the SQL query: " + e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        // Code to establish and return a database connection
        return DriverManager.getConnection("");
    }
}
