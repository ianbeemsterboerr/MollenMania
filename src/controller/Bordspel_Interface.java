package controller;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.BeurtStatus;
import model.MolModel;
import model.Playboard_Model;
import model.Spelbord_Model;
import model.Speler_Model;

public interface Bordspel_Interface extends Remote {
<<<<<<< HEAD
	
=======

	public int getMaxMollen() throws RemoteException;

	public BeurtStatus getBeurtStatus() throws RemoteException;

	public void setBeurtStatus(BeurtStatus beurtStatus) throws RemoteException;

>>>>>>> ac4aaa80ee4a863d4405350f7c4aa1f26a7fa687
	public void addObserver(Player_Observer po) throws RemoteException;
	
	public void addSpeler(Speler_Model sm) throws RemoteException;
	
	public ArrayList<Player_Observer> observer_list() throws RemoteException;
	
	public ArrayList<Speler_Model> playerList() throws RemoteException;
	
	public void addSpelerReady(Speler_Model sm) throws RemoteException;
	
	public ArrayList<Speler_Model> readyList() throws RemoteException;

	public void veranderBeurt() throws  RemoteException;
	
	public int maxSpelers() throws RemoteException;
	
	public ArrayList<MolModel> molOnField() throws RemoteException;
	
	public void addMolField(MolModel mol) throws RemoteException;
	
	public Playboard_Model pm() throws RemoteException;
	
	public int beurtIndex() throws RemoteException;
}
