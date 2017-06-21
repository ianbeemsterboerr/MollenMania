package controller;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Spelbord_Model;
import model.Speler_Model;

public interface Bordspel_Interface extends Remote {
	
	public void addObserver(Player_Observer po) throws RemoteException;
	
	public void addSpeler(Speler_Model sm) throws RemoteException;
	
	public ArrayList<Player_Observer> observer_list() throws RemoteException;
	
	public ArrayList<Speler_Model> playerList() throws RemoteException;
	
	public Spelbord_Model spelModel() throws RemoteException;
	
	public String getClientHost() throws RemoteException;
	
 }
