package solid.interfaceSegregationPrinciple.task;

/**
 * 1. Identify methods in the interface that are not relevant to all the classes implementing that particular interface.
 * 2. Split the interface into smaller, more specific interfaces.
 * 3. Have each class, then implement only the interfaces that it needs.
 */
public interface Worker {
    void work();

    void eat();
}

class HumanWorker implements Worker {

    @Override
    public void work() {
        //...
    }

    @Override
    public void eat() {
        //...
    }
}

class RobotWorker implements Worker{

    @Override
    public void work() {
        //...
    }

    @Override
    public void eat() {
        //...
    }
}
