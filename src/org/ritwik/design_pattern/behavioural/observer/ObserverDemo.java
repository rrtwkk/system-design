package org.ritwik.design_pattern.behavioural.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        NewsChannel cnn = new NewsChannel("CNN");
        NewsChannel bbc = new NewsChannel("BBC");
        NewsChannel fox = new NewsChannel("Fox News");

        // Subscribe channels
        agency.addObserver(cnn);
        agency.addObserver(bbc);
        agency.addObserver(fox);

        // Publish news
        System.out.println("\n=== Publishing First News ===");
        agency.setNews("Stock market hits new high!");

        System.out.println("\n=== Unsubscribing Fox News ===");
        agency.removeObserver(fox);

        System.out.println("\n=== Publishing Second News ===");
        agency.setNews("New technology breakthrough announced!");
    }
}
