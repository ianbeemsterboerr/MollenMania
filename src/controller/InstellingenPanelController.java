package controller;
import java.net.MalformedURLException;

import model.InstellingenModel;
import view.HandleidingView;
import view.InstInGameView;
import view.InstellingenView;

/**
 * Handelt alle user interaction af met het instellingenPanel.
 * Created by Robert on 6/15/2017.
 */
public class InstellingenPanelController {
    private InstellingenModel instellingenModel;
    private InstellingenView instellingenView;
    private HandleidingView handleidingView;
    private InstInGameView instInGameView;

    public InstellingenPanelController(){
        this.instellingenView = new InstellingenView(this);
        this.instellingenModel = new InstellingenModel();

        this.handleidingView = new HandleidingView();

    }

    /**
     *
     * @return Geeft de view van de instellingenpanel terug. Deze wordt gebruikt om in meerdere views te zetten.
     */
    public InstellingenView getInstellingenView(){
        return instellingenView;
    }

    /**
     * Maakt een view aan voor het gebruik wanneer een game begonnen is.
     * @param bordspel_controller Geeft de bordspelcontroller mee zodat de user input kan worden afgehandelt.
     * @return Geeft de view terug.
     */
    public InstInGameView createInstInGameView(Bordspel_Controller bordspel_controller){
        instInGameView=new InstInGameView(this, bordspel_controller);
        return instInGameView;
    }

    /**
     * Mute het geluid afhankelijk van of dit nog niet is gedaan. Anders unmute deze functie het geluid.
     * @throws MalformedURLException 
     */
    public void toggleMute() throws MalformedURLException{
        if (instellingenModel.getSoundState())
        instellingenModel.muteSound();
        else{
            instellingenModel.unmuteSound();
        }
    }

    /**
     * Zet kleurenblindheidsmodus aan of uit.
     */
    public void toggleKleurenblindModus(){
        if(instellingenModel.getKleurenBlindModus()){
            instellingenModel.setKleurenBlindModus(false);
        }else{
            instellingenModel.setKleurenBlindModus(true);
        }
        System.out.println(instellingenModel.getKleurenBlindModus());
    }

    /**
     * Laat de handleiding van het spel zien.
     */
    public void showHandleiding(){
        System.out.println("InstellingenPanelController: show Handleiding.");
        this.handleidingView.show();
    }
}
