package controller;

import view.JoinSpelView;

/**
 * Heeft als verantwoordelijkheid om de SpelJoinView op te starten.
 * Created by Robert on 6/18/2017.
 */
public class SpelJoinController {

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
