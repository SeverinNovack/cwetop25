package org.cwe;

import java.nio.ByteBuffer;

public class Rank01_2022_CWE787_OutOfBoundsWrite {

    private static final int BUFFER_SIZE = 3;

    public static void main(String[] args) {
        int numElements = 4;

        bad(numElements);
        System.out.println("------------------------");
        good(numElements);
    }

    private static void bad(int numElements) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        for (int i = 1; i <= numElements; i++) {
            buffer.put((byte) i);
        }
    }

    private static void good(int numElements) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        if (buffer.remaining() >= numElements) {
            for (int i = 1; i <= numElements; i++) {
                buffer.put((byte) i);
            }
        } else {
            System.err.println("Insufficient buffer capacity. Aborting write operation.");
        }
    }
}
