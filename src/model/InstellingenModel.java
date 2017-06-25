package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Robert on 6/15/2017.
 */
public class InstellingenModel {
    private boolean kleurenBlindModus=false;
    private boolean soundState=true;
    private Clip clip;
    String musicFile = "src/view/sound/TestSound.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());

    public InstellingenModel(){
        try {
            this.clip= AudioSystem.getClip();
            playMusic();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public boolean getSoundState() {
        return soundState;
    }

    public void setSoundState(boolean soundState) {
        this.soundState = soundState;
        playMusic();
    }

    public boolean getKleurenBlindModus() {
        return kleurenBlindModus;
    }

    public void setKleurenBlindModus(boolean kleurenBlindModus) {
        this.kleurenBlindModus = kleurenBlindModus;
    }

    private void playMusic(){
        if(soundState){
            try{

                MediaPlayer mp = new MediaPlayer(sound);
                mp.play();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        } else {
            clip.close();
            clip.stop();
        }
    }
}
