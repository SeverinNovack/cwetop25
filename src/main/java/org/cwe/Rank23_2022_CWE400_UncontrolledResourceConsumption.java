package org.cwe;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
class Rank23_2022_CWE400_UncontrolledResourceConsumption_Bad extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Check if the request is a multipart/form-data
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                // Create a factory for creating DiskFileItem instances
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // Set the repository directory for storing uploaded files
                String repositoryPath = "path/to/repository";
                File repository = new File(repositoryPath);
                factory.setRepository(repository);

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Flaw: No maximum file size configuration

                // Parse the request to extract file items
                List<FileItem> items = upload.parseRequest(request);

                // Process each file item
                for (FileItem item : items) {
                    // Handle each file item as required
                    if (!item.isFormField()) {
                        // Process the uploaded file
                        String fileName = item.getName();
                        File uploadedFile = new File(repository, fileName);
                        item.write(uploadedFile);
                        // Perform further processing with the uploaded file
                    }
                }

                response.getWriter().println("File(s) uploaded successfully.");
            } catch (Exception e) {
                response.getWriter().println("Error uploading file: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid request: Not a multipart/form-data");
        }
    }
}

@WebServlet("/upload")
@MultipartConfig
class Rank23_2022_CWE400_UncontrolledResourceConsumption_Good extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Check if the request is a multipart/form-data
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                // Create a factory for creating DiskFileItem instances
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // Set the repository directory for storing uploaded files
                String repositoryPath = "path/to/repository";
                File repository = new File(repositoryPath);
                factory.setRepository(repository);

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Good: Configure the maximum file sizes
                upload.setFileSizeMax(104857600); // 100MB
                upload.setSizeMax(1073741824); // 1GB

                // Parse the request to extract file items
                List<FileItem> items = upload.parseRequest(request);

                // Process each file item
                for (FileItem item : items) {
                    // Handle each file item as required
                    if (!item.isFormField()) {
                        // Process the uploaded file
                        String fileName = item.getName();
                        File uploadedFile = new File(repository, fileName);
                        item.write(uploadedFile);
                        // Perform further processing with the uploaded file
                    }
                }

                response.getWriter().println("File(s) uploaded successfully.");
            } catch (Exception e) {
                response.getWriter().println("Error uploading file: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid request: Not a multipart/form-data");
        }
    }
}

