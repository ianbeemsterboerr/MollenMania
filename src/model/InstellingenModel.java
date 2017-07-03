package model;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.MalformedURLException;


/**
 * Bevat alle data voor de globale instellingen van de applicatie.
 * Created by Robert on 6/15/2017.
 */
public class InstellingenModel extends Application{
    private boolean kleurenBlindModus=false;
    private boolean soundState;
    private MediaPlayer player;
    /**
     * Zorgt dat het instellingenmodel leeft, en de muziek start met spelen.
     */
    public InstellingenModel(){
//        String musicFile = "src/view/sound/TestSound.mp3";
//        InputStream stream = ClassLoader().getResourceAsStream("src/view/sound/TestSound.mp3");
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mp = new MediaPlayer(sound);
//        playMusic();
      //  mp.setMute(true); //COMMENT DEZE UIT VOOR DE RELEASE!!
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
     * @throws MalformedURLException 
     */
    public void muteSound() throws MalformedURLException{
    	System.out.println("Toggle sound OFF");
    	this.player.setMute(true);
        soundState = false;
    }

    /**
     * Zet het geluid aan.
     * @throws MalformedURLException 
     */
    public void unmuteSound() throws MalformedURLException{
    	System.out.println("Toggle sound ON");
    	String urlJar = getClass().getResource("/sound/TestSound.mp3").toExternalForm();
    	Media media = new Media(urlJar); //replace /Movies/test.mp3 with your file
        this.player = new MediaPlayer(media); 
        player.play();
    	
        soundState = true;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
