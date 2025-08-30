package org.ritwik.design_pattern.behavioural.observer;

// Subject interface
public interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
