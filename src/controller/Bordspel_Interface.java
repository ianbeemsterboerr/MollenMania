package controller;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.*;
import model.MolModel;
import model.Speler_Model;

/**
 * Interface stub die aan de client side gebruikt wordt om met de server te communiceren
 */
public interface Bordspel_Interface extends Remote {
	/**
	 * Stuurt een notificatie naar alle views waardoor zij updaten.
	 * @throws RemoteException Wanneer de client niet kan verbinden met de server.
	 */
	public void notifyObservers() throws RemoteException;

	/**
	 * Geeft de maximaal aantal mollen.
	 * @return int
	 * @throws RemoteException Wanneer de client niet kan verbinden met de server.
	 */
	public int getMaxMollen() throws RemoteException;

	/**
	 *
	 * @return
	 * @throws RemoteException
	 */
	public void addHervatSpelerKlaar(Speler_Model speler) throws RemoteException;
	
	public ArrayList<Speler_Model> getHervatSpelersList() throws RemoteException;
	
	public BeurtStatus getBeurtStatus() throws RemoteException;

	public void setBeurtStatus(BeurtStatus beurtStatus) throws RemoteException;

	public void veranderBeurt() throws  RemoteException;
	
	public int maxSpelers() throws RemoteException;
	
	public int beurtIndex() throws RemoteException;
	
	public int getHuidigeNiveauIndex() throws RemoteException;

	public void setMolCoord(Speler_Model speler, int[] coord, int molIndex) throws RemoteException;

	public void changeNiveauInt() throws RemoteException;

	public Spelbord_Model getSpelbordModel() throws RemoteException;

	public void deleteMollfromList()throws RemoteException;
	
	public void testMe() throws RemoteException;

	public ArrayList<MolModel> getMollen() throws  RemoteException;
	
	public boolean getHervatStatus() throws RemoteException;
	
	public void setHervatStatus(boolean status) throws RemoteException;

	/*
	 * ADD THINGS
	 */

	public void addObserver(Player_Observer po, String bijnaam) throws RemoteException;
	
	public void addSpeler(Speler_Model sm) throws RemoteException;
	
	public void addMolField(MolModel mol) throws RemoteException;

	public boolean setSpelerReady(Speler_Model sm) throws RemoteException;

	public void setSpelerInGame(String bijnaam) throws RemoteException;
	
	public void addMolltoList(int[] coordinaten)throws RemoteException;

	
	/*
	 * LISTS
	 */
	
	public ArrayList<Player_Observer> observer_list() throws RemoteException;
	
	public ArrayList<Speler_Model> playerList() throws RemoteException;
	
	public ArrayList<MolModel> molOnField() throws RemoteException;
	
}
