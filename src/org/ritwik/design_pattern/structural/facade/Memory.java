package org.ritwik.design_pattern.structural.facade;

// Subsystem class
public class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory: Loading data at position " + position);
    }
}
