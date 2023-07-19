package org.cwe;

import org.owasp.encoder.Encode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Rank02_2022_CWE79_CrossSiteScripting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userInput = request.getParameter("<script>alert('Hello, CWE-20!');</script>");
        bad(userInput, response);
        System.out.println("------------------------");
        good(userInput, response);
    }

    private void bad(String message, HttpServletResponse response)
            throws IOException {
        // Bad approach: No input validation, directly displaying user input on the webpage
        response.getWriter().println("<h1>User Message (Bad): " + message + "</h1>");
    }

    private void good(String message, HttpServletResponse response)
            throws IOException {
        // Good approach: Input validation and output encoding
        response.getWriter().println("<h1>User Message (Good): " + Encode.forHtml(message) + "</h1>");
    }
}

