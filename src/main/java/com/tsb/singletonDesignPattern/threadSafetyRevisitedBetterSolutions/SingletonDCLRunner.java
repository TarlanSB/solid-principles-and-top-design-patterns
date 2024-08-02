package com.tsb.singletonDesignPattern.threadSafetyRevisitedBetterSolutions;

public class SingletonDCLRunner {

    public static void main(String[] args) {
        SingletonDCL firstInstance = SingletonDCL.getInstance();
        SingletonDCL secondInstance = SingletonDCL.getInstance();
        System.out.println("First SingletonDCL instance hash: " + firstInstance.hashCode());
        System.out.println("Second SingletonDCL instance hash: " + secondInstance.hashCode());
    }
}


class SingletonDCL {
    // volatile keyword ensures that multiple threads handle the uniqueInstance variable correctly
    // when it is being initialized to the Singleton instance.
    private volatile static SingletonDCL uniqueInstance;

    private SingletonDCL() {
        // private constructor to prevent instantiation
    }

    public static SingletonDCL getInstance() {
        if (uniqueInstance == null) { // First check (no locking)
            synchronized (SingletonDCL.class) {
                if (uniqueInstance == null) { // Second check (with locking)
                    uniqueInstance = new SingletonDCL();
                }
            }
        }
        return uniqueInstance;
    }
}
