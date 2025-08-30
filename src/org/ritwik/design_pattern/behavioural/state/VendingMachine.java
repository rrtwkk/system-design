package org.ritwik.design_pattern.behavioural.state;

// Context
public class VendingMachine {
    private State idleState;
    private State coinInsertedState;
    private State productSelectedState;

    private State currentState;
    private int productCount = 5;

    public VendingMachine() {
        idleState = new IdleState();
        coinInsertedState = new CoinInsertedState();
        productSelectedState = new ProductSelectedState();

        currentState = idleState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void insertCoin() {
        currentState.insertCoin(this);
    }

    public void selectProduct() {
        currentState.selectProduct(this);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct(this);
    }

    // Getters for states
    public State getIdleState() {
        return idleState;
    }

    public State getCoinInsertedState() {
        return coinInsertedState;
    }

    public State getProductSelectedState() {
        return productSelectedState;
    }

    public int getProductCount() {
        return productCount;
    }

    public void decrementProductCount() {
        productCount--;
    }
}
