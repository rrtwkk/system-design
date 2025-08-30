package org.ritwik.design_pattern.behavioural.state;

// Concrete State
public class IdleState implements State {
    @Override
    public void insertCoin(VendingMachine context) {
        System.out.println("Coin inserted. Please select a product.");
        context.setState(context.getCoinInsertedState());
    }

    @Override
    public void selectProduct(VendingMachine context) {
        System.out.println("Please insert a coin first.");
    }

    @Override
    public void dispenseProduct(VendingMachine context) {
        System.out.println("Please insert a coin and select a product first.");
    }
}
