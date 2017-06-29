package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Het Subject interface uit het Observer Pattern. Alle modellen die remotely moeten worden aangepast door de verschillende clients moeten deze implementeren. In dit geval is dat alleen het SpelbordModel.
 * Created by Robert on 6/22/2017.
 */
public interface Spel_Interface extends Remote {
    /**
     * Geeft het Playboard_Model met alle niveaus erin terug van de server.
     * @return Playboard_Model
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public Playboard_Model getSpelbord() throws RemoteException;

    /**
     * Update het spelbord op de server.
     * @param spelbord Playboard_Model.
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public void setSpelbord(Playboard_Model spelbord) throws RemoteException;

    /**
     * Update alle spelborden op de server.
     * @param spelbord ArrayList<Playboard_Model.
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public void setSpelbord(ArrayList<Playboard_Model> spelbord) throws RemoteException;

    /**
     * Geeft een ArrayList van alle spelers in het spel.
     * @return ArrayList<Speler_Model>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public ArrayList<Speler_Model> getSpeler() throws RemoteException;

    /**
     * Set de spelers op de server.
     * @param spelers ArrayList<Speler_Model>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public void setSpelers(ArrayList<Speler_Model> spelers) throws RemoteException;

    /**
     * geeft het huidige niveau van het spel op de server.
     * @return int
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public int getHuidigNiveau() throws RemoteException;

    /**
     * Set het huidige niveau van het spel.
     * @param huidigNiveau int.
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public void setHuidigNiveau(int huidigNiveau) throws RemoteException;

    /**
     * Geeft de observerlist terug.
     * @return ArrayList<Player_Observer>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public ArrayList<Player_Observer> getPlayerObservers() throws RemoteException;

    /**
     * zet alle spelers in de observer list
     * @param playerObservers ArrayList<Player_Observer>
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public void setPlayerObservers(ArrayList<Player_Observer> playerObservers) throws RemoteException;

    /**
     * Geeft het maximaal aantal observers terug.
     * @return int
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public int getMaxAantalObservers() throws RemoteException;

    /**
     * Set het maximaal aantal observers in de list.
     * @param maxAantalObservers int
     * @throws RemoteException Wanneer de connectie tussen de client en de server is verbroken.
     */
    public void setMaxAantalObservers(int maxAantalObservers) throws RemoteException;
 
}
