package org.cwe;

public class Rank05_2022_CWE125_OutOfBoundsRead {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};

        bad(array);
        System.out.println("------------------------");
        good(array);
    }

    private static void bad(int[] array) {
        // Accessing elements outside the valid range of the array
        for (int i = 0; i <= array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void good(int[] array) {
        // Checking the array bounds before accessing elements
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

