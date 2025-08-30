package org.ritwik.design_pattern.creational.abstract_factory;

// Client
public class AbstractFactoryDemo {
    private Button button;
    private Checkbox checkbox;

    public AbstractFactoryDemo(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.render();
        checkbox.render();
    }

    public static void main(String[] args) {
        // Create Windows UI
        System.out.println("Creating Windows UI:");
        GUIFactory windowsFactory = new WindowsFactory();
        AbstractFactoryDemo windowsApp = new AbstractFactoryDemo(windowsFactory);
        windowsApp.renderUI();

        System.out.println("\nCreating Mac UI:");
        GUIFactory macFactory = new MacFactory();
        AbstractFactoryDemo macApp = new AbstractFactoryDemo(macFactory);
        macApp.renderUI();
    }
}
