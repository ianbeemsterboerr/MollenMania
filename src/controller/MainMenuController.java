package controller;

import view.InstellingenView;
import view.MainMenuView;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Bevat alle functies die de main menu moet kunnen.
 * Created by Robert on 6/16/2017.
 */
public class MainMenuController {
    private MainMenuView mainMenuView;
    private String serverFilePath = "";
    private  InstellingenPanelController instellingenPanelController;

    /**
     * Krijgt een instellingenView mee zodat de instellingen aan kunnen worden gepast.
     * @param instellingenPanelController
     */
    public MainMenuController(InstellingenPanelController instellingenPanelController){
        this.instellingenPanelController=instellingenPanelController;
        mainMenuView=new MainMenuView(instellingenPanelController.getInstellingenView(),this);
    }

    /**
     * Start het proces om een spel te joinen.
     */
    public void joinGame(){
        System.out.println("MainMenuController: joinGame");
        new SpelJoinController(this);
    }

    /**
     * Maakt een nieuwe HostSpelController aan zodat een spel gehost kan worden.
     */
    public void hostGame(){
        new HostSpelController(this);
//        System.out.println("MainMenuController: hostGame");
//        try {
//            Process process = Runtime.getRuntime().exec(serverFilePath);
//        } catch (FileNotFoundException e){
//            System.out.println("FileNotFoundException");
//        } catch (IOException e){
//            System.out.println("input output exception");
//        }
//        mainMenuView.uitschakelen();
//        new HostSpelController(this);
        //new host_controls_view();
    }

    /**
     * Start een SpelHervattenController op zodat een spel hervat kan worden.
     */
    public void hervatSpel(){
        System.out.println("MainMenuController: hervatSpel");
        mainMenuView.uitschakelen();
        new SpelHervattenController();
    }

    /**
     * Sluit de game af.
     */
    public void closeGame(){
        System.exit(0);
    }

    /**
     * Zorgt ervoor dat de knoppen in het Main Menu klikbaar worden.
     */
    public void inschakelenKnoppen(){
        mainMenuView.inschakelen();
    }

    /**
     *
     * @return geeft de instellingenPanel terug.
     */
    public InstellingenPanelController getInstellingenPanelController(){
        return this.instellingenPanelController;
    }
}
