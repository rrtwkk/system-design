package org.ritwik.design_pattern.structural.bridge;

// Refined Abstraction
public class AdvancedRemote extends Remote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
        System.out.println("Device muted");
    }

    public void setChannel(int channel) {
        device.setChannel(channel);
    }
}
