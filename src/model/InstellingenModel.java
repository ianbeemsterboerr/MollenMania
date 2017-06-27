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
 * Bevat alle data voor de globale instellingen van de applicatie.
 * Created by Robert on 6/15/2017.
 */
public class InstellingenModel {
    private boolean kleurenBlindModus=false;
    private boolean soundState;
    private Clip clip;
    String musicFile = "src/view/sound/TestSound.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mp = new MediaPlayer(sound);

    /**
     * Zorgt dat het instellingenmodel leeft, en de muziek start met spelen.
     */
    public InstellingenModel(){
        playMusic();
        mp.setMute(true); //COMMENT DEZE UIT VOOR DE RELEASE!!
    }

    /**
     * Geeft de status van de muziek terug.
     * @return true of false. true voor aan.
     */
    public boolean getSoundState() {
        return soundState;
    }

    /**
     * Geeft de status van de kleurenblindheidsmodus terug.
     * @return true of false. true voor aan.
     */
    public boolean getKleurenBlindModus() {
        return kleurenBlindModus;
    }

    /**
     * Zet de kleurenblindheidsmodus aan of uit.
     * @param kleurenBlindModus
     */
    public void setKleurenBlindModus(boolean kleurenBlindModus) {
        this.kleurenBlindModus = kleurenBlindModus;
    }

    /**
     * Zet het geluid uit.
     */
    public void muteSound(){
        mp.setMute(true);
        soundState = false;
    }

    /**
     * Zet het geluid aan.
     */
    public void unmuteSound(){
        mp.setMute(false);
        soundState = true;
    }

    /**
     * Start de muziek.
     */
    private void playMusic(){
                mp.play();
                soundState = true;
    }
}
