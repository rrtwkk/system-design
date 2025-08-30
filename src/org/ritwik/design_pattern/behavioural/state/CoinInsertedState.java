package org.ritwik.design_pattern.behavioural.state;

// Concrete State
public class CoinInsertedState implements State {
    @Override
    public void insertCoin(VendingMachine context) {
        System.out.println("Coin already inserted. Please select a product.");
    }

    @Override
    public void selectProduct(VendingMachine context) {
        if (context.getProductCount() > 0) {
            System.out.println("Product selected. Dispensing...");
            context.setState(context.getProductSelectedState());
        } else {
            System.out.println("Product out of stock. Returning coin.");
            context.setState(context.getIdleState());
        }
    }

    @Override
    public void dispenseProduct(VendingMachine context) {
        System.out.println("Please select a product first.");
    }
}
