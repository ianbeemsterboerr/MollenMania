package controller;

import view.*;

import java.io.IOException;

/**
 * Start de server op.
 * Created by Robert on 6/18/2017.
 */
public class HostSpelController {
    private HostSpelView hostSpelView;
    private  MainMenuController mainMenuController;

    /**
     *
     * @param mainMenuController De mainmenucontroller die alle user input afhandelt.
     */
    public HostSpelController(MainMenuController mainMenuController){
        this.hostSpelView = new HostSpelView(this);
        this.mainMenuController = mainMenuController;
    }

    /**
     * Start de server op met de door de user ingevoerde IP. Dit is de IP waar andere clients kunnen connecten.
     */
    public void startServer(){
        String IP = "145.101.89.116";
        System.out.println(this.getClass().toString()+": startServer");
        String home =System.getProperties().getProperty("java.home");
        System.out.println("serverstart: "+home);
        try {
            //java -Djava.rmi.server.hostname=145.101.89.116 C:/Users/Robert/IdeaProjects/mollenmania/Mol_server/bin/controller/run
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec("Javavm -D"+IP);
            Process process2 = runTime.exec("java -classpath C:/Users/Robert/IdeaProjects/mollenmania/Mol_server/bin//controller/run Run");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainMenuController getMainMenuController(){
        return mainMenuController;
    }
}
