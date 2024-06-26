package solid.liskovSubstitutionPrinciple.solution;

public class ElectricCar extends ElectricVehicle {
    @Override
    void charge() {
        System.out.println("Charging battery...");
    }

    @Override
    void move() {
        System.out.println("Moving...");
    }
}
