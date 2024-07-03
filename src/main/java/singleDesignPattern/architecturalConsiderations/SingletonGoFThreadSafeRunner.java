package singleDesignPattern.architecturalConsiderations;

public class SingletonGoFThreadSafeRunner {

    public static void main(String[] args) {
        SingletonGoFThreadSafe firstInstance = SingletonGoFThreadSafe.getInstance();
        SingletonGoFThreadSafe secondInstance = SingletonGoFThreadSafe.getInstance();
        System.out.println("First Thread-Safe instance hash: " + firstInstance.hashCode());
        System.out.println("Second Thread-Safe instance hash: " + secondInstance.hashCode());
    }
}

class SingletonGoFThreadSafe {
    // A private static variable to hold the single instance of the class
    private static SingletonGoFThreadSafe instance;

    // Private constructor to prevent instantiation from outside the class
    private SingletonGoFThreadSafe() {
    }

    // Public static method to provide a global access point to the single instance
    // and include synchronization to make it thread-safe
    public static synchronized SingletonGoFThreadSafe getInstance() {
        // Check if the instance is null, and if so, create the instance
        // This is done inside the synchronized block to prevent multiple threads
        // from creating multiple instances
        if (instance == null) {
            instance = new SingletonGoFThreadSafe();
        }

        // Return the single instance of the class
        return instance;
    }
}
