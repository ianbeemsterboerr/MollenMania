package model;

import javafx.fxml.Initializable;

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

    /**
     * Maakt een SpelModel aan met alle informatie die daarvoor nodig is.
     * @param spelbord Playboard_Model. Het spelbord dat in het model staat.
     * @param spelerModels ArrayList<Speler_Model>. Alle spelermodels die in het spel zitten.
     * @param huidigNiveau int. Het huidige niveau.
     * @param playerObservers ArrayList<Player_Observer> Alle observers waarvan de views moeten worden geupdate.
     * @param maxAantalObservers int. het maximaal aantal observers.
     */
    public Spel_Model(Playboard_Model spelbord, ArrayList<Speler_Model> spelerModels, int huidigNiveau, ArrayList<Player_Observer> playerObservers, int maxAantalObservers) {
        this.spelbord = spelbord;
        this.spelerModels = spelerModels;
        this.huidigNiveau = huidigNiveau;
        this.playerObservers = playerObservers;
        this.maxAantalObservers = maxAantalObservers;
    }
    /**
     * Geeft het Playboard_Model met alle niveaus erin terug van de server.
     * @return Playboard_Model
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public Playboard_Model getSpelbord() throws RemoteException {
        return spelbord;
    }
    /**
     * Update het spelbord op de server.
     * @param spelbord Playboard_Model.
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public void setSpelbord(Playboard_Model spelbord) throws RemoteException {
        this.spelbord = spelbord;
    }
    /**
     * Update alle spelborden op de server.
     * @param spelbord ArrayList<Playboard_Model.
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public void setSpelbord(ArrayList<Playboard_Model> spelbord)  throws RemoteException{
        spelbord = spelbord;
    }
    /**
     * Geeft een ArrayList van alle spelers in het spel.
     * @return ArrayList<Speler_Model>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public ArrayList<Speler_Model> getSpeler()  throws RemoteException{
        return spelerModels;
    }
    /**
     * Set de spelers op de server.
     * @param spelers ArrayList<Speler_Model>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public void setSpelers(ArrayList<Speler_Model> spelers)  throws RemoteException{
        this.spelerModels = spelerModels;
    }
    /**
     * geeft het huidige niveau van het spel op de server.
     * @return int
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public int getHuidigNiveau() throws RemoteException{
        return huidigNiveau;
    }
    /**
     * Set het huidige niveau van het spel.
     * @param huidigNiveau int.
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public void setHuidigNiveau(int huidigNiveau) throws RemoteException {
        this.huidigNiveau = huidigNiveau;
    }
    /**
     * Geeft de observerlist terug.
     * @return ArrayList<Player_Observer>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public ArrayList<Player_Observer> getPlayerObservers()  throws RemoteException{
        return playerObservers;
    }
    /**
     * zet alle spelers in de observer list
     * @param playerObservers ArrayList<Player_Observer>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public void setPlayerObservers(ArrayList<Player_Observer> playerObservers) throws RemoteException{
        this.playerObservers = playerObservers;
    }
    /**
     * Geeft het maximaal aantal observers terug.
     * @return int
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public int getMaxAantalObservers() throws RemoteException {
        return maxAantalObservers;
    }
    /**
     * Set het maximaal aantal observers in de list.
     * @param maxAantalObservers int
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    @Override
    public void setMaxAantalObservers(int maxAantalObservers)  throws RemoteException{
        this.maxAantalObservers = maxAantalObservers;
    }

    /**
     * Zorgt dat de klasse initialiseerbaar is.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
