package com.tsb.singletonDesignPattern.threadSafetyRevisitedBetterSolutions;

public class BillPughSingletonRunner {

    public static void main(String[] args) {
        SingletonBillPugh firstInstance = SingletonBillPugh.getInstance();
        SingletonBillPugh secondInstance = SingletonBillPugh.getInstance();
        System.out.println("First SingletonBillPugh instance hash: " + firstInstance.hashCode());
        System.out.println("Second SingletonBillPugh instance hash: " + secondInstance.hashCode());
    }
}


class SingletonBillPugh {
    
    // Private constructor to prevent instantiation
    private SingletonBillPugh() {
    }

    // Static inner class that contains the instance of the singleton class.
    // When the SingletonBillPugh class is loaded, SingletonHelper class is not loaded into memory
    // and only when someone calls the getInstance method, this class gets loaded and creates the Singleton instance.
    private static class SingletonHelper {
        private static final SingletonBillPugh INSTANCE = new SingletonBillPugh();
    }

    // Public static method that returns the instance of the class, this is the global access point
    // for outer world to get the instance of the singleton class.
    public static SingletonBillPugh getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

