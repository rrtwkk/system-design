package org.ritwik.design_pattern.behavioural.state;

// State interface
public interface State {
    void insertCoin(VendingMachine context);

    void selectProduct(VendingMachine context);

    void dispenseProduct(VendingMachine context);
}
