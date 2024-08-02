package com.tsb.singletonDesignPattern.architecturalConsiderations2;

public class CloningSingletonTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Retrieve the singleton instance
        ClonableSingleton instanceOne = ClonableSingleton.getInstance();
        // Attempt to clone the singleton instance
        ClonableSingleton instanceTwo = (ClonableSingleton) instanceOne.clone();

        // Print out the hash codes of the original and cloned instance
        // If cloning is not prevented properly, these could be different, demonstrating a flaw in singleton enforcement
        System.out.println("Instance 1 hash:" + instanceOne.hashCode());
        System.out.println("Instance 2 hash:" + instanceTwo.hashCode());
    }
}

class ClonableSingleton implements Cloneable  {
    // Static variable to hold the single instance of the class
    private static ClonableSingleton instance;

    // Private constructor to prevent instantiation from outside this class
    private ClonableSingleton() {}

    // Public method to provide global access to the singleton instance
    public static ClonableSingleton getInstance() {
        // Lazy initialization: if the instance doesn't exist, create it
        if (instance == null) {
            instance = new ClonableSingleton();
        }
        return instance;
    }

    // Override the Object clone method to allow cloning of the singleton instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return instance; // Return the same instance to enforce singleton
    }
    
}

