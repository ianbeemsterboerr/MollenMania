package controller;

import view.JoinSpelView;

/**
 * Created by Robert on 6/18/2017.
 */
public class SpelJoinController {
    //joinSpelView
    //speler?
    private JoinSpelView joinSpelView;
    private  MainMenuController mainMenuController;
    public SpelJoinController(MainMenuController mainMenuController){

        this.joinSpelView = new JoinSpelView(this);
        this.mainMenuController = mainMenuController;

    }

    public MainMenuController getMainMenuController(){
        return mainMenuController;
    }
}
