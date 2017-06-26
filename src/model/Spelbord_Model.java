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
	private int aanDeBeurt;
	private int bordMax;

	private int maxMollen;

	//private Niveau_Model niveau1 = new Niveau_Model(); niveau's meoten gemaakt worden.
	private BeurtStatus beurtStatus;

	public Spelbord_Model(int maxSpelers){
		this.beurtStatus = BeurtStatus.LOBBY;
		this.bordMax=maxSpelers;
		switch (maxSpelers){
			case 2:
				this.maxMollen =10;
				break;
			case 3:
				this.maxMollen =8;
				break;
			case 4:
				this.maxMollen =6;
				break;
			case 1:
				System.out.println(this.getClass().toString()+": max spelers te laag. Setup failed.");
				break;
			default:
				System.out.println(this.getClass().toString()+": max spelers te hoog: "+maxSpelers+", mag niet meer zijn dan 4. Setup failed.");
		}
	}

	public Spelbord_Model(String saveNaam){
		System.out.println(this.getClass().toString()+": savenaam is "+saveNaam);
		this.beurtStatus = BeurtStatus.LOBBY;
	}

	public BeurtStatus getBeurtStatus() throws RemoteException {
		return beurtStatus;
	}

	public void setBeurtStatus(BeurtStatus beurtStatus) throws RemoteException {
		this.beurtStatus = beurtStatus;
	}

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

	public int getAanDeBeurt() {
		return aanDeBeurt;
	}

	public void setAanDeBeurt(int aanDeBeurt) {
		this.aanDeBeurt = aanDeBeurt;
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

	@Override
	public void veranderBeurt() throws RemoteException {
		// TODO Auto-generated method stub
		int handGrootteCurrent=players.get(aanDeBeurt).getHandgrootte();
		int lastIterated=0;
		for (int i=0; i<players.size();i++){
			//checken of de speler die je checkt niet dezelfde is die nu aan de beurt is
			if (i!=aanDeBeurt){
				if(players.get(i).getHandgrootte()<handGrootteCurrent){
					if(players.get(i).getHandgrootte()>players.get(lastIterated).getHandgrootte()){
						lastIterated=i;
					}
				}
			}
		}
		this.aanDeBeurt=lastIterated;
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

	@Override
	public int getMaxMollen() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int beurtIndex() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
