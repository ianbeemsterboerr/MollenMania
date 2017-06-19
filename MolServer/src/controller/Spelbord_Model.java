package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Speler_Model;

public class Spelbord_Model implements Bordspel_Interface{

	private ArrayList<Player_Observer> bord_observers = new ArrayList<Player_Observer>();
	private ArrayList<Speler_Model> players = new ArrayList<Speler_Model>();
	
	public Spelbord_Model(){
		
	}

	private void notifyPlayers() throws RemoteException {
		for(Player_Observer mop_o : bord_observers){
			mop_o.modelChanged(this);
		}
	}

	@Override
	public void addObserver(Player_Observer po) throws RemoteException {
		// TODO Auto-generated method stub
		bord_observers.add(po);
		try {
			notifyPlayers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Speler_Model> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Speler_Model> players) {
		this.players = players;
	}

	@Override
	public void addSpeler(Speler_Model sm) throws RemoteException {
		// TODO Auto-generated method stub
		this.players.add(sm);
	}


	@Override
	public ArrayList<Speler_Model> playerList() throws RemoteException {
		// TODO Auto-generated method stub
		return this.players;
	}

	@Override
	public int getSpelerListSize() throws RemoteException {
		// TODO Auto-generated method stub
		return this.players.size();
	}

	@Override
	public void getSpelerName() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
