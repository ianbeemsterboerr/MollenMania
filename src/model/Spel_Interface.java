package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Robert on 6/22/2017.
 */
public interface Spel_Interface extends Remote {
    public Playboard_Model getSpelbord() throws RemoteException;
    public void setSpelbord(Playboard_Model spelbord) throws RemoteException;
    public void setSpelbord(ArrayList<Playboard_Model> spelbord) throws RemoteException;
    public ArrayList<Speler_Model> getSpeler() throws RemoteException;
    public void setSpelers(ArrayList<Speler_Model> spelers) throws RemoteException;
    public int getHuidigNiveau() throws RemoteException;
    public void setHuidigNiveau(int huidigNiveau) throws RemoteException;
    public ArrayList<Player_Observer> getPlayerObservers() throws RemoteException;
    public void setPlayerObservers(ArrayList<Player_Observer> playerObservers) throws RemoteException;
    public int getMaxAantalObservers() throws RemoteException;
    public void setMaxAantalObservers(int maxAantalObservers) throws RemoteException;
 
}
