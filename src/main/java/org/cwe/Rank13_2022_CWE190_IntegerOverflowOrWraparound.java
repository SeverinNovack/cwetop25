package org.cwe;

public class Rank13_2022_CWE190_IntegerOverflowOrWraparound {
    public static void main(String[] args) {
        int a = 2147483647;
        int b = 2147483647;

        bad(a, b);
        System.out.println("------------------------");
        good(a, b);
    }

    private static void good(int a, int b) {
        if (a > Integer.MAX_VALUE - b) {
            System.err.println("Integer overflow or wraparound detected!");
        } else {
            int result = a + b;
            System.out.println("Result of addition: " + result);
        }
    }

    private static void bad(int a, int b) {
        int result = a + b;
        System.out.println("Result of addition: " + result);
    }
}

