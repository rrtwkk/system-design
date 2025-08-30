package org.ritwik.design_pattern.behavioural.state;

public class StateDemo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // Normal flow
        System.out.println("=== Normal Purchase Flow ===");
        machine.insertCoin();
        machine.selectProduct();
        machine.dispenseProduct();

        // Try to select without coin
        System.out.println("\n=== Try selecting without coin ===");
        machine.selectProduct();

        // Another normal flow
        System.out.println("\n=== Another Purchase ===");
        machine.insertCoin();
        machine.selectProduct();
        machine.dispenseProduct();

        // Double coin insertion
        System.out.println("\n=== Double coin insertion ===");
        machine.insertCoin();
        machine.insertCoin();
        machine.selectProduct();
    }
}
