package controller;

import view.InstellingenView;
import view.MainMenuView;
import view.*;

/**
 * Created by Robert on 6/16/2017.
 */
public class MainMenuController {
    MainMenuView mainMenuView;

    public MainMenuController(InstellingenView instellingenView){
        mainMenuView=new MainMenuView(instellingenView,this);
    }

    public void joinGame(){
        System.out.println("MainMenuController: joinGame");
        mainMenuView.uitschakelen();
        new SpelJoinController(this);
        //new connect_host_view("0");
    }
    public void hostGame(){
        System.out.println("MainMenuController: hostGame");
        mainMenuView.uitschakelen();
        new HostSpelController(this);
        //new host_controls_view();
    }
    public void hervatSpel(){
        System.out.println("MainMenuController: hervatSpel");
        mainMenuView.uitschakelen();
        new hervat_game_view();
    }
    public void closeGame(){
        System.exit(0);
    }

    public void inschakelenKnoppen(){
        mainMenuView.inschakelen();
    }
}
