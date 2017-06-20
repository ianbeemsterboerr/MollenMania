package controller;

import view.*;

/**
 * Created by Robert on 6/18/2017.
 */
public class HostSpelController {
    private HostSpelView hostSpelView;
    private  MainMenuController mainMenuController;

    public HostSpelController(MainMenuController mainMenuController){
        this.hostSpelView = new HostSpelView(this);
        this.mainMenuController = mainMenuController;
    }

    public MainMenuController getMainMenuController(){
        return mainMenuController;
    }
}