package model;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.Bordspel_Interface;
import controller.Player_Observer;
import javafx.fxml.Initializable;

public class Spelbord_Model implements Bordspel_Interface, Initializable{

	private ArrayList<Player_Observer> bord_observers = new ArrayList<Player_Observer>();
	private ArrayList<Speler_Model> players = new ArrayList<Speler_Model>();
	private ArrayList<Speler_Model> ready_list = new ArrayList<Speler_Model>();

	public Spelbord_Model(){
		
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
	
	public void notifyObservers() throws RemoteException {
		for (Player_Observer co : bord_observers) {
			co.modelChanged(this);
		}
	}
	
	@Override
	public void addObserver(Player_Observer po) throws RemoteException {
		// TODO Auto-generated method stub
		bord_observers.add(po);
		try {
			notifyObservers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Spelbord_Model spelModel() throws RemoteException {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ArrayList<Player_Observer> observer_list() throws RemoteException {
		// TODO Auto-generated method stub
		return this.bord_observers;
	}

	@Override
	public void addSpelerReady(Speler_Model sm) throws RemoteException {
		// TODO Auto-generated method stub
		this.ready_list.add(sm);
	}

	@Override
	public ArrayList<Speler_Model> readyList() throws RemoteException {
		// TODO Auto-generated method stub
		return this.ready_list;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
