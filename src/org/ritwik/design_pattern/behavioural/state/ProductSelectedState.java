package org.ritwik.design_pattern.behavioural.state;

// Concrete State
public class ProductSelectedState implements State {
    @Override
    public void insertCoin(VendingMachine context) {
        System.out.println("Product already selected. Dispensing...");
        dispenseProduct(context);
    }

    @Override
    public void selectProduct(VendingMachine context) {
        System.out.println("Product already selected. Dispensing...");
        dispenseProduct(context);
    }

    @Override
    public void dispenseProduct(VendingMachine context) {
        System.out.println("Product dispensed! Enjoy your purchase.");
        context.decrementProductCount();
        System.out.println("Products remaining: " + context.getProductCount());
        context.setState(context.getIdleState());
    }
}
