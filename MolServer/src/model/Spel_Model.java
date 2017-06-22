package model;

import javafx.fxml.Initializable;
import controller.Player_Observer;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Wessel on 15-6-2017.
 */
public class Spel_Model implements Initializable, Spel_Interface {

    private Playboard_Model spelbord;
    private ArrayList<Speler_Model> spelerModels;
    private int huidigNiveau;
    private ArrayList<Player_Observer> playerObservers;
    private int maxAantalObservers;

    public Spel_Model(Playboard_Model spelbord, ArrayList<Speler_Model> spelerModels, int huidigNiveau, ArrayList<Player_Observer> playerObservers, int maxAantalObservers) {
        this.spelbord = spelbord;
        this.spelerModels = spelerModels;
        this.huidigNiveau = huidigNiveau;
        this.playerObservers = playerObservers;
        this.maxAantalObservers = maxAantalObservers;
    }

    @Override
    public Playboard_Model getSpelbord() throws RemoteException {
        return spelbord;
    }

    @Override
    public void setSpelbord(Playboard_Model spelbord) throws RemoteException {
        this.spelbord = spelbord;
    }

    @Override
    public void setSpelbord(ArrayList<Playboard_Model> spelbord)  throws RemoteException{
        spelbord = spelbord;
    }

    @Override
    public ArrayList<Speler_Model> getSpeler()  throws RemoteException{
        return spelerModels;
    }

    @Override
    public void setSpelers(ArrayList<Speler_Model> spelers)  throws RemoteException{
        this.spelerModels = spelerModels;
    }

    @Override
    public int getHuidigNiveau() throws RemoteException{
        return huidigNiveau;
    }

    @Override
    public void setHuidigNiveau(int huidigNiveau) throws RemoteException {
        this.huidigNiveau = huidigNiveau;
    }

    @Override
    public ArrayList<Player_Observer> getPlayerObservers()  throws RemoteException{
        return playerObservers;
    }

    @Override
    public void setPlayerObservers(ArrayList<Player_Observer> playerObservers) throws RemoteException{
        this.playerObservers = playerObservers;
    }

    @Override
    public int getMaxAantalObservers() throws RemoteException {
        return maxAantalObservers;
    }

    @Override
    public void setMaxAantalObservers(int maxAantalObservers)  throws RemoteException{
        this.maxAantalObservers = maxAantalObservers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
