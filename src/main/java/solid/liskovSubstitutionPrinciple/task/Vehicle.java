package solid.liskovSubstitutionPrinciple.task;

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


