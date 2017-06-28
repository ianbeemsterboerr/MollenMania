package controller;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.*;
import model.MolModel;
import model.Playboard_Model;
import model.Speler_Model;

public interface Bordspel_Interface extends Remote {
	
	/*
	 * General information needed from yomama's object on the server.
	 */
	
	public void notifyObservers() throws RemoteException;

	public int getMaxMollen() throws RemoteException;

	public BeurtStatus getBeurtStatus() throws RemoteException;

	public void setBeurtStatus(BeurtStatus beurtStatus) throws RemoteException;

	public void veranderBeurt() throws  RemoteException;
	
	public int maxSpelers() throws RemoteException;
	
	public int beurtIndex() throws RemoteException;
	
	public int getHuidigeNiveauIndex() throws RemoteException;

	public void setMolCoord(MolModel mol, int[] coord) throws RemoteException;

	public void changeNiveauInt() throws RemoteException;
	
	/*
	 * ADD THINGS
	 */

	public void addObserver(Player_Observer po) throws RemoteException;
	
	public void addSpeler(Speler_Model sm) throws RemoteException;
	
	public void addMolField(MolModel mol) throws RemoteException;

	public boolean setSpelerReady(Speler_Model sm) throws RemoteException;

	public void nextObserver() throws  RemoteException;
	
	public void addMolltoList(int[] coordinaten)throws RemoteException;

	
	/*
	 * LISTS
	 */
	
	public ArrayList<Player_Observer> observer_list() throws RemoteException;
	
	public ArrayList<Speler_Model> playerList() throws RemoteException;
	
	public ArrayList<MolModel> molOnField() throws RemoteException;
	
}
