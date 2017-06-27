package controller;

import view.*;

import java.io.IOException;

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

    public void startServer(){
        String IP = "145.101.89.116";
        System.out.println(this.getClass().toString()+": startServer");
        String home =System.getProperties().getProperty("java.home");
        System.out.println("serverstart: "+home);
        try {
            //java -Djava.rmi.server.hostname=145.101.89.116 C:/Users/Robert/IdeaProjects/mollenmania/Mol_server/bin/controller/run
            Runtime runTime = Runtime.getRuntime();
            Process process2 = runTime.exec("java -classpath C:/Users/Robert/IdeaProjects/mollenmania/Mol_server/bin//controller/run Run");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainMenuController getMainMenuController(){
        return mainMenuController;
    }
}
