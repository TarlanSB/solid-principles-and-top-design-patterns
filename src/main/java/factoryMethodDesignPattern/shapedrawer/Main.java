package factoryMethodDesignPattern.shapedrawer;

import factoryMethodDesignPattern.shapedrawer.gui.ShapeDrawer;

import javax.swing.SwingUtilities;

// Main class to run the application.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShapeDrawer().setVisible(true);
        });
    }
}

