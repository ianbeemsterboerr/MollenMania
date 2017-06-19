package controller;
import model.InstellingenModel;
import view.InstellingenView;

/**
 * Created by Robert on 6/15/2017.
 */
public class InstellingenPanelController {
    private InstellingenModel instellingenModel;
    private InstellingenView instellingenView;

    public InstellingenPanelController(){
        this.instellingenView = new InstellingenView(this);
        this.instellingenModel = new InstellingenModel();
    }
    public InstellingenView getInstellingenView(){
        return instellingenView;
    }

    public void toggleVolume(){
        if(instellingenModel.getSoundState()){
            instellingenModel.setSoundState(false);
        } else {
            instellingenModel.setSoundState(true);
        }
        System.out.println(instellingenModel.getSoundState());
    }

    public void openHandleiding(){

    }

    public void toggleKleurenblindModus(){
        if(instellingenModel.getKleurenBlindModus()){
            instellingenModel.setKleurenBlindModus(false);
        }else{
            instellingenModel.setKleurenBlindModus(true);
        }
        System.out.println(instellingenModel.getKleurenBlindModus());
    }

}