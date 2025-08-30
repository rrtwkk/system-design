package org.ritwik.design_pattern.structural.adapter;

// Concrete Adaptee
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // Do nothing - VLC player doesn't support MP4
    }
}
