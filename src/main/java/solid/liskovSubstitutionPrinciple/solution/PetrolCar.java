package solid.liskovSubstitutionPrinciple.solution;

public class PetrolCar extends FuelVehicle {
    @Override
    void refuel() {
        System.out.println("Refilling petrol...");
    }

    @Override
    void move() {
        System.out.println("Moving...");
    }
}
