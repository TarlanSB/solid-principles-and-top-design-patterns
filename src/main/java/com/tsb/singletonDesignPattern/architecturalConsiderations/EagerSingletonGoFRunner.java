package com.tsb.singletonDesignPattern.architecturalConsiderations;

public class EagerSingletonGoFRunner {

    public static void main(String[] args) {
        EagerSingletonGoF firstInstance = EagerSingletonGoF.getInstance();
        EagerSingletonGoF secondInstance = EagerSingletonGoF.getInstance();
        System.out.println("First eager instance hash: " + firstInstance.hashCode());
        System.out.println("Second eager instance hash: " + secondInstance.hashCode());
    }
}

class EagerSingletonGoF {
    private static final EagerSingletonGoF instance = new EagerSingletonGoF();

    private EagerSingletonGoF() {
        // private constructor to prevent instantiation
    }

    public static EagerSingletonGoF getInstance() {
        return instance;
    }
}
