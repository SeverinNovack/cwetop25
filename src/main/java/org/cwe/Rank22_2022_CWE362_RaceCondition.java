package org.cwe;

import java.util.ArrayList;
import java.util.List;

public class Rank22_2022_CWE362_RaceCondition {
    private static List<String> names = new ArrayList<>();

    public static void main(String[] args) {
        int numThreads = 5;

        // Bad method: Race condition present
        bad(numThreads);

        System.out.println("------------------------");

        // Good method: Proper synchronization to avoid race condition
        good(numThreads);
    }

    private static void bad(int numThreads) {
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // Add name without synchronization
                    names.add("Name " + j);
                }
            });

            threads[i].start();
        }

        // Wait for all threads to complete
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Names (Bad): " + names.size());
    }

    private static void good(int numThreads) {
        Thread[] threads = new Thread[numThreads];
        Object lock = new Object(); // Synchronization lock

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // Add name with proper synchronization
                    synchronized (lock) {
                        names.add("Name " + j);
                    }
                }
            });

            threads[i].start();
        }

        // Wait for all threads to complete
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Names (Good): " + names.size());
    }
}