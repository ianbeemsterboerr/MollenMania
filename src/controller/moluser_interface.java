package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Observer;

import model.player_model;
import model.player_observer;

public interface moluser_interface extends Remote {
	
	public String getPlayer() throws RemoteException;
	
	public player_model player () throws RemoteException;
	
	public void max_observers () throws RemoteException;
	
	public void addObserver(player_observer po) throws RemoteException;
	
	public int observerSize() throws RemoteException;
}
