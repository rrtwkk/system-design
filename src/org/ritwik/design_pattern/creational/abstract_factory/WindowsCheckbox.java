package org.ritwik.design_pattern.creational.abstract_factory;

// Concrete Product
public class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Windows style checkbox");
    }

    @Override
    public void toggle() {
        System.out.println("Windows checkbox toggled");
    }
}
