package org.cwe;

import java.util.Scanner;

public class Rank11_2022_CWE476_NULLPointerDereference {
    public static void main(String[] args) {
        String userInput = getUserInput();

        bad(userInput);
        System.out.println("------------------------");
        good(userInput);
    }

    private static void bad(String userInput) {
        // Bad approach: Null pointer dereference
        if (userInput.equals("admin")) {
            System.out.println("Welcome, admin!");
        }
    }

    private static void good(String userInput) {
        // Good approach: Null check before dereference
        if (userInput != null && userInput.equals("admin")) {
            System.out.println("Welcome, admin!");
        }
    }

    private static String getUserInput() {
        // Simulating null user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string (or leave it empty for null): ");
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            return null;
        } else {
            return input;
        }
    }
}

