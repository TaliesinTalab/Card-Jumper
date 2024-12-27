package game.App.App;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;
import java.net.URL;

    /**
    * To handle sound playback, including sound effets and looping background music
    */

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[15];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/background_music.wav");
        soundURL[1] = getClass().getResource("/sound/door_open.wav");
        soundURL[2] = getClass().getResource("/sound/key_sound.wav");
        soundURL[3] = getClass().getResource("/sound/boots_sound.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
    }


    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {

        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
    public boolean isRunning() {
        return clip.isRunning();
    }


}

