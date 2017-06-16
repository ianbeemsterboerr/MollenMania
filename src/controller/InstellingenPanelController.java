package controller;
import model.InstellingenModel;
import view.InstellingenView;

/**
 * Created by Robert on 6/15/2017.
 */
public class InstellingenPanelController {
    private InstellingenModel instellingen;
    private InstellingenView knoppenPanel;

    public InstellingenPanelController(){
        this.knoppenPanel = new InstellingenView(this);
        this.instellingen = new InstellingenModel();
    }

    public void toggleVolume(){
        if(instellingen.getSoundState()){
            instellingen.setSoundState(false);
        } else {
            instellingen.setSoundState(true);
        }
        System.out.println(instellingen.getSoundState());
    }

    public void openHandleiding(){

    }

    public void toggleKleurenblindModus(){
        if(instellingen.getKleurenBlindModus()){
            instellingen.setKleurenBlindModus(false);
        }else{
            instellingen.setKleurenBlindModus(true);
        }
        System.out.println(instellingen.getKleurenBlindModus());
    }

}
