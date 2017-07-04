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

    public void connect(String serverIP, String clientIP, String bijnaam){
        if(clientIP!=""&&clientIP!=null&&clientIP!=" "){
            System.out.println(this.getClass().toString()+": rmi.server.hostname gezet");
            System.setProperty("java.rmi.server.hostname",clientIP);
        }
        new Mol_Client(serverIP,bijnaam,mainMenuController.getInstellingenPanelController());
    }
}
