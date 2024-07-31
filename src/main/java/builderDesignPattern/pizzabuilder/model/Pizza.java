package builderDesignPattern.pizzabuilder.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a pizza with a name, size, crust type, and list of toppings.
 */
public class Pizza {
    private final String name;
    private final String size;
    private final String crust;
    private final List<String> toppings;

    /**
     * Private constructor for pizza which is accessible only through the PizzaBuilder.
     */
    private Pizza(PizzaBuilder builder) {
        this.name = builder.name;
        this.size = builder.size;
        this.crust = builder.crust;
        this.toppings = builder.toppings;
    }

    // Getter methods to access the properties of Pizza
    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    /**
     * Returns a new list containing the toppings to ensure the list cannot be modified.
     */
    public List<String> getToppings() {
        return new ArrayList<>(toppings);
    }

    /**
     * Static inner class PizzaBuilder using the Builder design pattern to construct a Pizza object.
     */
    public static class PizzaBuilder {
        private String name;
        private String size;
        private String crust;
        private final List<String> toppings = new ArrayList<>();

        // Builder methods for setting properties
        public PizzaBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PizzaBuilder size(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder crust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder topping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        /**
         * Builds the Pizza object using the attributes set in the builder.
         */
        public Pizza build() {
            return new Pizza(this);
        }
    }
}