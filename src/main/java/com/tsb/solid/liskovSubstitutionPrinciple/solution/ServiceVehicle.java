package com.tsb.solid.liskovSubstitutionPrinciple.solution;

public class ServiceVehicle {

    public static void serviceFuelVehicle(FuelVehicle vehicle) {
        vehicle.refuel();
        // some more servicing activities
    }

    public static void serviceElectricVehicle(ElectricVehicle vehicle) {
        vehicle.charge();
        // some more servicing activities
    }
}
