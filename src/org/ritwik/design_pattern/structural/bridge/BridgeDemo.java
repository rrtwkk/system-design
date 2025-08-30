package org.ritwik.design_pattern.structural.bridge;

public class BridgeDemo {
    public static void main(String[] args) {
        Device tv = new Television();
        Device radio = new Radio();

        System.out.println("Testing basic remote with TV:");
        Remote basicRemote = new Remote(tv);
        basicRemote.togglePower();
        basicRemote.volumeUp();
        basicRemote.channelUp();

        System.out.println("\nTesting advanced remote with Radio:");
        AdvancedRemote advancedRemote = new AdvancedRemote(radio);
        advancedRemote.togglePower();
        advancedRemote.volumeUp();
        advancedRemote.setChannel(101);
        advancedRemote.mute();
    }
}
