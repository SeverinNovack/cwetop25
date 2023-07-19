package org.cwe;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rank15_2022_CWE798_UseOfHardCodedCredentials extends HttpServlet {
    private static final String HARD_CODED_USERNAME = "admin";
    private static final String HARD_CODED_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (badAuthenticate(username, password)) {
            // Perform authentication logic
            response.getWriter().println("Authentication successful");
        } else {
            response.getWriter().println("Authentication failed");
        }
    }

    // Bad method (Vulnerable to CWE-798)
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (goodAuthenticate(username, password)) {
            // Perform authentication logic
            response.getWriter().println("Authentication successful");
        } else {
            response.getWriter().println("Authentication failed");
        }
    }

    private boolean badAuthenticate(String username, String password) {
        // Implement proper authentication logic here
        // This could involve checking against a database, calling an authentication service, etc.

        // Return true if authentication is successful, false otherwise
        return username.equals(HARD_CODED_USERNAME) && password.equals(HARD_CODED_PASSWORD);
    }

    // Good method (Avoids CWE-798)
    private boolean goodAuthenticate(String username, String password) {
        // SQLite database connection
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:/path/to/database.db")) {
            // Prepare the SQL statement
            String sql = "SELECT username FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                // Execute the query
                try (ResultSet rs = stmt.executeQuery()) {
                    // Check if the result set has any rows
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            // Handle any database-related errors
            e.printStackTrace();
        }

        return false;
    }
}
