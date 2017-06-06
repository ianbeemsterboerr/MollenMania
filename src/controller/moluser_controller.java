package controller;

import java.rmi.RemoteException;

public class moluser_controller {
	moluser_interface p;
	
	public moluser_controller(moluser_interface playable) throws RemoteException{
		this.p = playable;
	}
	
	public String username() throws RemoteException {
		return p.getPlayer();
	}
}
