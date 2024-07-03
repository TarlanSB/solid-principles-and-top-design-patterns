package singleDesignPattern.architecturalConsiderations;

public class SingletonGoFRunner {

    public static void main(String[] args) {
        SingletonGoF firstInstance = SingletonGoF.getInstance();
        SingletonGoF secondInstance = SingletonGoF.getInstance();
        System.out.println("First GoF instance hash: " + firstInstance.hashCode());
        System.out.println("Second GoF instance hash: " + secondInstance.hashCode());
    }
}

/*
 * Singleton Gof implementation
 * Note that this is a *lazy* instantation version of the singleton
 */
class SingletonGoF {
    private static SingletonGoF instance=null;

    private SingletonGoF() {
        // private constructor to prevent instantiation
    }

    public static SingletonGoF getInstance() {
        if (instance == null) {
            // note that this is created *on demand* which means that this is a 
            // lazy instantiation implementation which will not create an instance 
            // until the first client request
            instance = new SingletonGoF();
        }
        return instance;
    }
}