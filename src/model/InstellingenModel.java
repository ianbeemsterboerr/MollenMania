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
    private boolean soundState;
    private Clip clip;
    String musicFile = "src/view/sound/TestSound.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mp = new MediaPlayer(sound);

    public InstellingenModel(){

        playMusic();
        mp.setMute(true); //COMMENT DEZE UIT VOOR DE RELEASE!!
    }

    public boolean getSoundState() {
        return soundState;
    }


    public boolean getKleurenBlindModus() {
        return kleurenBlindModus;
    }

    public void setKleurenBlindModus(boolean kleurenBlindModus) {
        this.kleurenBlindModus = kleurenBlindModus;
    }
    public void muteSound(){
        mp.setMute(true);
        soundState = false;
    }
    public void unmuteSound(){
        mp.setMute(false);
        soundState = true;
    }
    private void playMusic(){
                mp.play();
                soundState = true;
    }
}
