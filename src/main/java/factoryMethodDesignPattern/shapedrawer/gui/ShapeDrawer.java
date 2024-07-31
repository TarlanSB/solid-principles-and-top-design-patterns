package factoryMethodDesignPattern.shapedrawer.gui;

import factoryMethodDesignPattern.shapedrawer.factory.ShapeFactory;
import factoryMethodDesignPattern.shapedrawer.shapes.ShapeType;
import factoryMethodDesignPattern.shapedrawer.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * The ShapeDrawer class extends JFrame, providing the main application window
 * for the shape drawing application. It initializes the GUI components and handles
 * the logic for drawing random shapes.
 */
public class ShapeDrawer extends JFrame {
    private final DrawingPanel drawingPanel; // Panel where shapes will be drawn
    private final JLabel shapeNameLabel; // Label to display the name of the currently drawn shape

    /**
     * Constructor for ShapeDrawer. It sets up the GUI components including the drawing panel,
     * shape name label, and the button to trigger drawing a new random shape.
     */
    public ShapeDrawer() {
        super("Shape Drawer"); // Set the title of the JFrame
        this.drawingPanel = new DrawingPanel(this); // Initialize the drawing panel
        
        // Create a button that, when clicked, will draw a random shape.
        JButton drawButton = new JButton("Draw a Random Shape");
        drawButton.addActionListener(this::drawRandomShape);

        // Initialize the label to display the name of the shape. Set its properties for better visibility.
        shapeNameLabel = new JLabel("No shape yet", SwingConstants.CENTER);
        shapeNameLabel.setFont(shapeNameLabel.getFont().deriveFont(18.0f)); // Make the font larger
        shapeNameLabel.setForeground(Color.BLACK); // Set font color
        shapeNameLabel.setOpaque(false); // Make the background of the label transparent
        
        // The label panel is used to display the shape name with a custom background opacity.
        JPanel labelPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Custom painting code to draw a semi-transparent background for the label.
                super.paintComponent(g);
                g.setColor(shapeNameLabel.getBackground()); // Use the label's background color
                g.fillRect(0, 0, getWidth(), getHeight()); // Fill the panel area with the color
            }
        };
        labelPanel.setOpaque(false); // Ensure the panel itself is transparent
        labelPanel.add(shapeNameLabel); // Add the label to this panel

        // Set up the layout of the JFrame and add the components
        this.setLayout(new BorderLayout());
        this.add(labelPanel, BorderLayout.NORTH); // Label at the top
        this.add(drawingPanel, BorderLayout.CENTER); // Drawing panel in the center
        this.add(drawButton, BorderLayout.SOUTH); // Button at the bottom

        // JFrame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the window is closed
        this.setSize(400, 400); // Set the initial size of the window
    }

    /**
     * Updates the text of the shape name label.
     * 
     * @param name The name of the shape to display in the label.
     */
    public void updateShapeName(String name) {
        shapeNameLabel.setText(name);
    }

    /**
     * Handles the action of drawing a new random shape when the button is clicked.
     * Selects a random shape type, determines the stroke thickness, and tells the drawing
     * panel to draw the new shape.
     * 
     * @param event The action event triggered by clicking the button.
     */
    private void drawRandomShape(ActionEvent event) {
        // Select a random shape type from the available types
        ShapeType[] shapeTypes = ShapeType.values();
        ShapeType selectedShapeType = shapeTypes[new Random().nextInt(shapeTypes.length)];
        
        // Get the current size of the drawing panel to ensure the shape fits within its bounds
        Dimension size = drawingPanel.getSize();
        // Determine a random stroke thickness between 1 and 10
        int strokeThickness = new Random().nextInt(10) + 1;
        
        // Use the factory to create a shape with the selected type, size, and stroke thickness
        Shape shape = ShapeFactory.createShape(selectedShapeType, size.width, size.height, strokeThickness);
        
        // Set the new shape to be drawn and update the label with its name
        drawingPanel.setShape(shape);
    }
    
    /**
     * The DrawingPanel class is a custom JPanel used for drawing shapes. It keeps a reference
     * to the current shape to draw and a reference back to the ShapeDrawer to update the shape name label.
     */
    private static class DrawingPanel extends JPanel {
        private Shape currentShape = null; // The current shape to be drawn
        private final ShapeDrawer shapeDrawer; // Reference to the containing ShapeDrawer

        DrawingPanel(ShapeDrawer shapeDrawer) {
            this.shapeDrawer = shapeDrawer;
        }

        /**
         * Sets the current shape to be drawn and updates the shape name label in the ShapeDrawer.
         * 
         * @param shape The new shape to set and draw.
         */
        public void setShape(Shape shape) {
            this.currentShape = shape;
            shapeDrawer.updateShapeName(shape.getName()); // Update the label with the shape's name
            repaint(); // Request a repaint to draw the new shape
        }

        /**
         * Custom painting method to draw the current shape.
         * 
         * @param g The Graphics object to be used for drawing.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Clear the panel
            if (currentShape != null) {
                currentShape.draw(g); // Draw the current shape if one exists
            }
        }
    }
}