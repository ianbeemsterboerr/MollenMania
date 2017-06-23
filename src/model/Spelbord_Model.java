package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import controller.Bordspel_Interface;
import controller.Player_Observer;

public class Spelbord_Model implements Bordspel_Interface{

	private ArrayList<Player_Observer> bord_observers = new ArrayList<Player_Observer>();
	private ArrayList<Speler_Model> players = new ArrayList<Speler_Model>();
	private ArrayList<Speler_Model> ready_list = new ArrayList<Speler_Model>();
	private ArrayList<MolModel> mol_onbord = new ArrayList<MolModel>();
	private Playboard_Model pmo = new Playboard_Model();
	private int[] specialPos;
	private int bordMax;

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

	public ArrayList<Speler_Model> getSpelers() throws  RemoteException {
		return this.players;
	}

//	public void veranderBeurt(){
//		int nextIndex=0;
//		for (Speler_Model speler: players) {
//			if(speler.isAanDeBeurt()){
//				speler.setAanDeBeurt(false);
//				nextIndex=players.indexOf(speler)+1;
//			}
//			break;
//		}
//		players.get(nextIndex).setAanDeBeurt(true);
//	}

	@Override
	public void veranderBeurt() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public void setBordMax(int m){
		this.bordMax = m;
	}
	
	@Override
	public int maxSpelers() throws RemoteException {
		// TODO Auto-generated method stub
		return this.bordMax;
	}

	public void setMol_onbord(ArrayList<MolModel> mol_onbord) {
		this.mol_onbord = mol_onbord;
	}

	public int[] getSpecialPos() {
		return specialPos;
	}

	public void setSpecialPos(int[] specialPos) {
		this.specialPos = specialPos;
	}

	@Override
	public ArrayList<MolModel> molOnField() throws RemoteException {
		// TODO Auto-generated method stub
		return this.mol_onbord;
	}

	@Override
	public void addMolField(MolModel mol) throws RemoteException {
		// TODO Auto-generated method stub
		this.mol_onbord.add(mol);
	}

	@Override
	public Playboard_Model pm() throws RemoteException {
		// TODO Auto-generated method stub
		return this.pmo;
	}
}
