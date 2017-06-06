package controller;
import view.connect_host_view;

import java.rmi.RemoteException;
import java.util.Observer;

import model.player_model;
import model.player_observer;

public class moluser_controller {
	moluser_interface p;
	
	public moluser_controller(moluser_interface playable) throws RemoteException{
		this.p = playable;
	}
	
	public String username() throws RemoteException {
		return p.getPlayer();
	}
}
