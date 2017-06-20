package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Player_Model;
import model.Player_Observer;

public class moluser_controller {
	moluser_interface p;
	private int observer_index;
	Player_Model player;
	private ArrayList<Player_Observer> molplayer_observers;
	public int maxAantalSpelers;
	
	public moluser_controller(moluser_interface playable, Player_Model pm) throws RemoteException{
		this.p = playable;
		this.player = pm;
	}
	
	void beurtObserver() throws RemoteException {
		if (molplayer_observers.size() > 0) {
			molplayer_observers.get(observer_index).setEnabled(false);
			observer_index++;
			if (observer_index >= molplayer_observers.size()) {
				observer_index = 0;
			}
			molplayer_observers.get(observer_index).setEnabled(true);
		}
		
	}
	
	public void playersMaximum(int observerSize, int max){
		if(observerSize < max){
			System.out.println("Allowed to add observer.");
		} else {
			System.out.println("No more users allowed!");
		}
	}
}
