package com.tsb.builderDesignPattern.pizzabuilder.gui;

import com.tsb.builderDesignPattern.pizzabuilder.model.Pizza;
import com.tsb.builderDesignPattern.pizzabuilder.model.PizzaDirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PizzaBuilderGUI extends JFrame {
    private Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();
    private JLabel pizzaNameLabel;
    private JLabel pizzaSizeAndCrustLabel;
    private DefaultListModel<String> toppingsListModel;
    private JComboBox<String> pizzaTypeComboBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> crustComboBox;

    public PizzaBuilderGUI() {
        setTitle("Pizza Builder");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        initUI();
    }

    private void initUI() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        add(optionsPanel);

        JPanel visualizationPanel = new JPanel();
        visualizationPanel.setLayout(new BoxLayout(visualizationPanel, BoxLayout.Y_AXIS));
        add(visualizationPanel);

        pizzaNameLabel = new JLabel("Choose your pizza type");
        visualizationPanel.add(pizzaNameLabel);

        pizzaSizeAndCrustLabel = new JLabel("Size and Crust will appear here");
        visualizationPanel.add(pizzaSizeAndCrustLabel);

        toppingsListModel = new DefaultListModel<>();
        JList<String> toppingsList = new JList<>(toppingsListModel);
        visualizationPanel.add(new JScrollPane(toppingsList));

        // Pizza type selection
        optionsPanel.add(new JLabel("Pizza Type:"));
        pizzaTypeComboBox = new JComboBox<>(new String[]{"Select", "Neapolitan", "Chicago", "New York", "Sicilian", "Greek", "California"});
        optionsPanel.add(pizzaTypeComboBox);

        // Size and Crust selection
        optionsPanel.add(new JLabel("Size:"));
        sizeComboBox = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        optionsPanel.add(sizeComboBox);

        optionsPanel.add(new JLabel("Crust:"));
        crustComboBox = new JComboBox<>(new String[]{"Thin", "Hand-tossed", "Deep-dish"});
        optionsPanel.add(crustComboBox);

        JButton buildButton = new JButton("Build Pizza");
        buildButton.addActionListener(this::buildPizza);
        optionsPanel.add(buildButton);
    }

    private void buildPizza(ActionEvent e) {
        String selectedType = (String) pizzaTypeComboBox.getSelectedItem();
        if (selectedType != null && !selectedType.equals("Select")) {
            builder = new Pizza.PizzaBuilder(); // Reset builder
            switch (selectedType) {
                case "Neapolitan" -> PizzaDirector.constructNeapolitanPizza(builder);
                case "Chicago" -> PizzaDirector.constructChicagoPizza(builder);
                case "New York" -> PizzaDirector.constructNewYorkPizza(builder);
                case "Sicilian" -> PizzaDirector.constructSicilianPizza(builder);

                // Include other pizza types as needed
            }
            builder.size((String) sizeComboBox.getSelectedItem());
            builder.crust((String) crustComboBox.getSelectedItem());
            Pizza pizza = builder.build();
            pizzaNameLabel.setText(pizza.getName());
            pizzaSizeAndCrustLabel.setText("Size: " + pizza.getSize() + ", Crust: " + pizza.getCrust());
            updateToppingsList(pizza.getToppings());
        }
    }

    private void updateToppingsList(List<String> toppings) {
        toppingsListModel.clear();
        toppings.forEach(toppingsListModel::addElement);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaBuilderGUI().setVisible(true));
    }
}
