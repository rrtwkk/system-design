package org.ritwik.design_pattern.structural.facade;

// Subsystem class
public class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive: Reading " + size + " bytes from sector " + lba);
        return new byte[size];
    }
}
