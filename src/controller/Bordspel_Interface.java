package controller;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Speler_Model;

public interface Bordspel_Interface extends Remote {
	
	public void addObserver(Player_Observer po) throws RemoteException;
	
	public void addSpeler(Speler_Model sm) throws RemoteException;
	
	public void getSpelerName() throws RemoteException;
	
	public int getSpelerListSize() throws RemoteException;
	
	public ArrayList<Speler_Model> playerList() throws RemoteException;
}
