
package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import controller.moluser_interface;
import javafx.beans.property.SimpleStringProperty;

public class Player_Model implements moluser_interface{

	private int player_id;
	private String player_name = "";
	private ArrayList<Player_Observer> molplayer_observers = new ArrayList<Player_Observer>();
	
	public Player_Model(){
		super();
	}
	
	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	private void notifyPlayers() throws RemoteException {
		for(Player_Observer mop_o : molplayer_observers){
			mop_o.modelChanged(this);
		}
	}

	@Override
	public void addObserver(Player_Observer po) throws RemoteException {
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
	public Player_Model player() throws RemoteException {
		// TODO Auto-generated method stub
		return this;
	}
	
	public ArrayList<Player_Observer> listObservers(){
		return this.molplayer_observers;
	}

	@Override
	public String getPlayer() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
