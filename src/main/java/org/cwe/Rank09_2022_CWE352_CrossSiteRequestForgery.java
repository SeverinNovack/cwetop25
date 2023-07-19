package org.cwe;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class Rank09_2022_CWE352_CrossSiteRequestForgery extends HttpServlet {
    private static final String CSRF_TOKEN_HEADER = "X-CSRF-Token";
    private static final String CSRF_TOKEN_SESSION_ATTR = "csrfToken";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Generate and store CSRF token in the session
        HttpSession session = request.getSession();
        String csrfToken = generateCSRFToken();
        session.setAttribute(CSRF_TOKEN_SESSION_ATTR, csrfToken);

        // Include the CSRF token in the response
        response.setHeader(CSRF_TOKEN_HEADER, csrfToken);

        // Your other GET request handling logic here
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Bad Method: No CSRF token validation
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Good Method: CSRF token validation
        if (validateCSRFToken(request)) {
            // Valid CSRF token, process the request
            processRequest(request, response);
        } else {
            // Invalid CSRF token, handle the error
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        // Process the request (e.g., perform data updates)
    }

    private String generateCSRFToken() {
        // Generate a unique CSRF token
        return UUID.randomUUID().toString();
    }

    private boolean validateCSRFToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String csrfToken = (String) session.getAttribute(CSRF_TOKEN_SESSION_ATTR);

        // Retrieve the CSRF token from the request
        String requestCsrfToken = request.getHeader(CSRF_TOKEN_HEADER);

        return csrfToken != null && csrfToken.equals(requestCsrfToken);
    }
}
