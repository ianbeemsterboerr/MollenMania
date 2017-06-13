package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Player_Model;
import model.Player_Observer;

public interface moluser_interface extends Remote {
	
	public String getPlayer() throws RemoteException;
	
	public Player_Model player () throws RemoteException;
	
	public void addObserver(Player_Observer po) throws RemoteException;
	
	public int observerSize() throws RemoteException;
	
}
