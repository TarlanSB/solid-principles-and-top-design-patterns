package com.tsb.factoryMethodDesignPattern.shapedrawer;

import com.tsb.factoryMethodDesignPattern.shapedrawer.gui.ShapeDrawer;

import javax.swing.SwingUtilities;

// Main class to run the application.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShapeDrawer().setVisible(true);
        });
    }
}

