package org.cwe;

import java.io.*;

public class Rank12_2022_CWE502_DeserializationOfUntrustedData {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        byte[] serializedData = getSerializedData(); // Simulated serialized data from an untrusted source

        bad(serializedData);
        System.out.println("------------------------");
        good(serializedData);
    }

    private static void bad(byte[] serializedData) throws IOException, ClassNotFoundException {
        // Bad approach: Deserialization without proper validation
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedData));
        Object obj = ois.readObject();

        System.out.println("Deserialized object: " + obj.toString());
    }

    private static void good(byte[] serializedData) throws IOException, ClassNotFoundException {
        // Good approach: Deserialization with input validation
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedData));

        // Perform input validation before deserialization
        if (isSafeToDeserialize(serializedData)) {
            Object obj = ois.readObject();
            System.out.println("Deserialized object: " + obj.toString());
        } else {
            System.out.println("Unsafe data. Deserialization not allowed.");
        }
    }

    private static byte[] getSerializedData() {
        // Simulated serialized data from an untrusted source
        return new byte[]{0x72, 0x00, 0x7b, 0x00, 0x00, 0x00, 0x02, 0x00, 0x02, 0x4c,
                0x00, 0x03, 0x74, 0x65, 0x73, 0x74, 0x74, 0x00, 0x12, 0x4c, 0x6a, 0x61,
                0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x4f, 0x62, 0x6a, 0x65,
                0x63, 0x74, 0x3b, 0x78, 0x70, 0x00, 0x00, 0x00, 0x01, 0x74, 0x00, 0x04,
                0x54, 0x65, 0x73, 0x74, 0x78};
    }

    private static boolean isSafeToDeserialize(byte[] serializedData) {
        // Perform input validation to determine if it is safe to deserialize
        // Add your own validation logic based on the requirements of your application
        // Example: Validate the serializedData against a whitelist of allowed classes or enforce a specific format
        return true;
    }
}

