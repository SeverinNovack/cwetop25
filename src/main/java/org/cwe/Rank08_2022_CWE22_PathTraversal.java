package org.cwe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Rank08_2022_CWE22_PathTraversal {
    private static final String BASE_DIRECTORY = "C:\\www\\data";

    public static void main(String[] args) {
        String userFileInput = "..\\..\\..\\..\\Windows\\System32\\config\\SAM";

        bad(userFileInput);
        System.out.println("------------------------");
        good(userFileInput);
    }

    private static void bad(String userInput) {
        // Bad approach: Performing file operation without proper validation or sanitization
        String filePath = BASE_DIRECTORY + "\\" + userInput;
        File file = new File(filePath);

        try (InputStream inputStream = new FileInputStream(file)) {
            // Process the file
            // ...
            System.out.println("Successfully processed file: " + file.getName());
        } catch (IOException e) {
            System.err.println("Error accessing file: " + file.getName());
            e.printStackTrace();
        }
    }

    private static void good(String userInput) {
        // Good approach: Applying input validation and path resolution to prevent path traversal
        Path basePath = Paths.get(BASE_DIRECTORY).toAbsolutePath().normalize();
        Path resolvedPath = basePath.resolve(userInput).normalize();

        if (!resolvedPath.startsWith(basePath)) {
            System.err.println("Invalid file path: " + userInput);
            return;
        }

        File file = resolvedPath.toFile();

        try (InputStream inputStream = new FileInputStream(file)) {
            // Process the file
            // ...
            System.out.println("Successfully processed file: " + file.getName());
        } catch (IOException e) {
            System.err.println("Error accessing file: " + file.getName());
            e.printStackTrace();
        }
    }
}