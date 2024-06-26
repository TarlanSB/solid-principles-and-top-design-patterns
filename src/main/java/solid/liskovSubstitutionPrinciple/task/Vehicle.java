package solid.liskovSubstitutionPrinciple.task;

/**
 * 1. Identify methods that aren't applicable to all subclasses.
 * 2. Consider splitting the superclass or contract interface into more specific subclasses or interfaces.
 * 3. Ensure that each subclass or implementation can be used interchangeably with the superclass without causing any issues.
 */
abstract class Vehicle {
    abstract void refuel();

    abstract void move();
}

class ElectricCar extends Vehicle {

    @Override
    void refuel() {
        System.out.println("Charging battery...");
    }

    @Override
    void move() {
        System.out.println("Moving...");
    }
}

class PetrolCar extends Vehicle {

    @Override
    void refuel() {
        System.out.println("Refilling petrol...");
    }

    @Override
    void move() {
        System.out.println("Moving...");
    }

    public static void serviceVehicle(Vehicle vehicle) {
        vehicle.refuel();
        // some more servicing activities
    }
}


