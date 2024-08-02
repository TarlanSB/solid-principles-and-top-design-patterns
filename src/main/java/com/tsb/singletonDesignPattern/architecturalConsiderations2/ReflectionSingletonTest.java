package com.tsb.singletonDesignPattern.architecturalConsiderations2;

import java.lang.reflect.Constructor;

public class ReflectionSingletonTest {

    public static void main(String[] args) {
        // Get the singleton instance using the public getInstance method
        Singleton instanceOne = Singleton.getInstance();
        Singleton instanceTwo = null;

        try {
            // Get all declared constructors of the Singleton class
            // Note: Even private constructors can be accessed with getDeclaredConstructors
            Constructor[] constructors = Singleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // Make the constructor accessible
                constructor.setAccessible(true);
                // Create a new instance of Singleton using the private constructor
                instanceTwo = (Singleton) constructor.newInstance();
                // Only need to break the singleton pattern once to prove it can be done
                break;
            }
        } catch (Exception e) {
            // Handle any exceptions that occur, such as IllegalAccessException or InstantiationException
            e.printStackTrace();
        }

        // Print out the hash codes of the two instances
        // If Singleton is properly implemented with defenses against reflection, these should be the same
        System.out.println("Instance 1 hash:" + instanceOne.hashCode());
        System.out.println("Instance 2 hash:" + instanceTwo.hashCode());
    }
}

