package org.cwe;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/fileUpload")
@MultipartConfig
public class Rank10_2022_CWE434_UnrestrictedUploadOfFileWithDangerousType extends HttpServlet {

    private static final List<String> ALLOWED_FILE_TYPES = List.of("jpg", "jpeg", "png");

    // Good Method: File type validation
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        if (isFileTypeAllowed(fileName)) {
            // File type is allowed, process the file
            processFile(filePart, fileName);
            response.getWriter().println("File upload successful.");
        } else {
            // File type is not allowed, handle the error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file type.");
        }
    }

    // Bad Method: Unrestricted file upload
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        // Process the file without performing any file type validation
        processFile(filePart, fileName);
        response.getWriter().println("File upload successful.");
    }

    private void processFile(Part filePart, String fileName) {
        // Process the uploaded file
        // ...
    }

    private boolean isFileTypeAllowed(String fileName) {
        // Extract the file extension from the file name
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        // Check if the file extension is in the list of allowed file types
        return ALLOWED_FILE_TYPES.contains(extension);
    }
}
