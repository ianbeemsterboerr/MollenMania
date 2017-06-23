package controller;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.MolModel;
import model.Playboard_Model;
import model.Spelbord_Model;
import model.Speler_Model;

public interface Bordspel_Interface extends Remote {

	public Spelbord_Model.SpelState getSpelState() throws RemoteException;

	public void setSpelState(Spelbord_Model.SpelState spelState) throws RemoteException;

	public void addObserver(Player_Observer po) throws RemoteException;
	
	public void addSpeler(Speler_Model sm) throws RemoteException;
	
	public ArrayList<Player_Observer> observer_list() throws RemoteException;
	
	public ArrayList<Speler_Model> playerList() throws RemoteException;
	
	public Spelbord_Model spelModel() throws RemoteException;
	
	public void addSpelerReady(Speler_Model sm) throws RemoteException;
	
	public ArrayList<Speler_Model> readyList() throws RemoteException;

	public void veranderBeurt() throws  RemoteException;

	public ArrayList<Speler_Model> getSpelers() throws  RemoteException;
	
	public int maxSpelers() throws RemoteException;
	
	public ArrayList<MolModel> molOnField() throws RemoteException;
	
	public void addMolField(MolModel mol) throws RemoteException;
	
	public Playboard_Model pm() throws RemoteException;
}
