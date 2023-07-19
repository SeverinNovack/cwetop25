package org.cwe;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/fileupload")
public class Rank20_2022_CWE276_IncorrectDefaultPermissions extends HttpServlet {

    // Directory path for file uploads
    private static final String UPLOAD_DIRECTORY = "C:\\uploads\\";

    // Bad method (Incorrect Default Permissions)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getHeader("X-File-Name");
        InputStream fileStream = request.getInputStream();

        // Create a file in the upload directory
        File file = new File(UPLOAD_DIRECTORY + fileName);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            // Read from the input stream and write to the file
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    // Good method (Correct Default Permissions)
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getHeader("X-File-Name");
        InputStream fileStream = request.getInputStream();

        // Create a file in the upload directory with restricted permissions
        File file = new File(UPLOAD_DIRECTORY + fileName);
        file.getParentFile().mkdirs();
        file.setReadable(false, true);
        file.setWritable(false, true);
        file.setExecutable(false, true);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            // Read from the input stream and write to the file
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
