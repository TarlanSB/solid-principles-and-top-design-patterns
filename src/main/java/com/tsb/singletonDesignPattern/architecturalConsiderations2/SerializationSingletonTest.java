package com.tsb.singletonDesignPattern.architecturalConsiderations2;

import java.io.*;

public class SerializationSingletonTest {
    public static void main(String[] args) {
        // Retrieve the singleton instance using the public getInstance method
        Singleton instanceOne = Singleton.getInstance();
        Singleton instanceTwo = null;

        try {
            // Serialize the singleton instance to a file named "singleton.ser"
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
            out.writeObject(instanceOne);
            out.close();  // Ensure all data is flushed and stream is closed

            // Deserialize the singleton instance from the file
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"));
            instanceTwo = (Singleton) in.readObject();
            in.close();  // Close the stream after reading the object

        } catch (Exception e) {
            // Print any exceptions that occur during serialization or deserialization
            e.printStackTrace();
        }

        // Print out the hash codes of the original and deserialized instance
        // If Singleton is properly implemented to handle serialization, these should be the same
        System.out.println("Instance 1 hash: " + instanceOne.hashCode());
        System.out.println("Instance 2 hash: " + instanceTwo.hashCode());
    }
}
