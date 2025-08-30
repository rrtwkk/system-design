package org.ritwik.design_pattern.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

// Concrete Subject
public class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String latestNews;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer added. Total observers: " + observers.size());
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removed. Total observers: " + observers.size());
    }

    @Override
    public void notifyObservers() {
        System.out.println("Notifying " + observers.size() + " observers...");
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }

    public void setNews(String news) {
        this.latestNews = news;
        System.out.println("News Agency: Breaking news - " + news);
        notifyObservers();
    }
}
