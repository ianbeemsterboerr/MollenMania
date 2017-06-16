package controller;

import view.InstellingenView;
import view.main_menu;
import view.*;

/**
 * Created by Robert on 6/16/2017.
 */
public class MainMenuController {
    main_menu mainMenuView;

    public MainMenuController(InstellingenView instellingenView){
        mainMenuView=new main_menu(instellingenView,this);
    }

    public void joinGame(){
        System.out.println("MainMenuController: joinGame");
        new connect_host_view("0");
    }
    public void hostGame(){
        System.out.println("MainMenuController: hostGame");
        new host_controls_view();
    }
    public void hervatSpel(){
        System.out.println("MainMenuController: hervatSpel");
        new hervat_game_view();
    }
    public void closeGame(){
        System.exit(0);
    }
}
