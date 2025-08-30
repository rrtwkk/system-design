package org.ritwik.design_pattern.structural.decorator;

public class DecoratorDemo {
    public static void main(String[] args) {
        // Simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        // Coffee with milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        // Coffee with milk and sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        // Coffee with milk, sugar, and whipped cream
        coffee = new WhipDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        // Another combination
        Coffee specialCoffee = new WhipDecorator(
                new MilkDecorator(
                        new SimpleCoffee()));
        System.out.println("\nSpecial: " + specialCoffee.getDescription() + " - $" + specialCoffee.getCost());
    }
}
