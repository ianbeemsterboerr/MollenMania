package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by Robert on 6/15/2017.
 */
public class InstellingenModel {
    private boolean kleurenBlindModus=false;
    private boolean soundState=true;

    public boolean getSoundState() {
        return soundState;
    }

    public void setSoundState(boolean soundState) {
        this.soundState = soundState;
        //playMusic();
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
                //deze moet nog veranderd
                AudioInputStream stream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Robert\\Music\\sabaton\\sparta.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.start();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
