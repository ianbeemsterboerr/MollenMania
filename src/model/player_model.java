package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import controller.moluser_interface;
import javafx.beans.property.SimpleStringProperty;

public class player_model implements moluser_interface{

	private int player_id;
	private final SimpleStringProperty player_name;
	private ArrayList<player_observer> molplayer_observers = new ArrayList<player_observer>();
	//private int observer_index;
	
	public player_model(int player_id, String player_name){
		this.player_id = player_id;
		this.player_name = new SimpleStringProperty(player_name);
	}
	
	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getPlayer_name() {
		return player_name.get();
	}

	public void setPlayer_name(String player_name) {
		this.player_name.set(player_name);
	}
	

	private void notifyPlayers() throws RemoteException {
		// TODO Auto-generated method stub
		for(player_observer mop_o : molplayer_observers){
			mop_o.modelChanged(this);
		}
	}
	
	/**
	private void beurtObserver() throws RemoteException {
		
		if (molplayer_observer.size() > 0) {
			molplayer_observer.get(observer_index).setEnabled(false);
			observer_index++;
			if (observer_index >= molplayer_observer.size()) {
				observer_index = 0;
			}
			molplayer_observer.get(observer_index).setEnabled(true);
		}
		
	}
	**/
	
	@Override
	public String getPlayer() throws RemoteException {
		// TODO Auto-generated method stub
		return this.getPlayer_name();
	}

	@Override
	public void addObserver(player_observer po) throws RemoteException {
		// TODO Auto-generated method stub
		molplayer_observers.add(po);
		try {
			notifyPlayers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int observerSize() throws RemoteException {
		// TODO Auto-generated method stub
		return molplayer_observers.size();
	}

	@Override
	public player_model player() throws RemoteException {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void max_observers() throws RemoteException {
		// TODO Auto-generated method stub
		if(this.observerSize() >= 4){
			System.out.println("Max aantal spelers bereikt");
		} else{
			
		}
	}
	
}
