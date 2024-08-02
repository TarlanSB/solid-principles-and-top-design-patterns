package com.tsb.solid.dependencyInversionPrinciple.solution;

public class Switch {
    private Switchable device;
    private boolean isOn = false;

    public Switch(Switchable device) {
        this.device = device;
    }

    void toggle() {
        if (isOn) {
            device.turnOff();
            isOn = false;
            System.out.println("Switched off");
        } else {
            device.turnOn();
            isOn = true;
            System.out.println("Switched on");
        }
    }
}
