package org.ritwik.design_pattern.behavioural.observer;

// Concrete Observer
public class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }

    public String getName() {
        return name;
    }
}
