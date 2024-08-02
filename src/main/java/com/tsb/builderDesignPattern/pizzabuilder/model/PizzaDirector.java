package com.tsb.builderDesignPattern.pizzabuilder.model;

/**
 * Class responsible for constructing different types of pizzas using the PizzaBuilder.
 */
public class PizzaDirector {

    /**
     * Constructs a Neapolitan Pizza by setting specific characteristics.
     */
    public static void constructNeapolitanPizza(Pizza.PizzaBuilder builder) {
        builder.name("Neapolitan Pizza")
               .size("Medium")
               .crust("Thin")
               .topping("Tomatoes")
               .topping("Mozzarella Cheese")
               .topping("Basil");
    }

    /**
     * Constructs a Chicago Pizza by setting specific characteristics.
     */
    public static void constructChicagoPizza(Pizza.PizzaBuilder builder) {
        builder.name("Chicago Pizza")
               .size("Large")
               .crust("Deep-dish")
               .topping("Tomato Sauce")
               .topping("Mozzarella Cheese")
               .topping("Pepperoni")
               .topping("Green Peppers")
               .topping("Onions");
    }

    /**
     * Constructs a New York Pizza by setting specific characteristics.
     */
    public static void constructNewYorkPizza(Pizza.PizzaBuilder builder) {
        builder.name("New York Pizza")
               .size("Large")
               .crust("Thin")
               .topping("Tomato Sauce")
               .topping("Mozzarella Cheese")
               .topping("Pepperoni");
    }

    /**
     * Constructs a Sicilian Pizza by setting specific characteristics.
     */
    public static void constructSicilianPizza(Pizza.PizzaBuilder builder) {
        builder.name("Sicilian Pizza")
               .size("Large")
               .crust("Thick")
               .topping("Tomato Sauce")
               .topping("Mozzarella Cheese")
               .topping("Anchovies")
               .topping("Black Olives");
    }

    // Add additional methods for other types of pizzas if needed
}