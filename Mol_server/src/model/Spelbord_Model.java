package model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

import controller.Bordspel_Interface;
import controller.Player_Observer;

public class Spelbord_Model implements Bordspel_Interface {

	private ArrayList<Player_Observer> bord_observers = new ArrayList<>();
	private ArrayList<Speler_Model> players = new ArrayList<>();
	private ArrayList<MolModel> mol_onbord = new ArrayList<>();
	private Playboard_Model pmo = new Playboard_Model();
	private int[] specialPos;
	private int aanDeBeurt;
	private int huidigeNiveau = 1;
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

	public ArrayList<Speler_Model> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Speler_Model> players)throws RemoteException {
		this.players = players;
		notifyObservers();
	}

	@Override
	public void addSpeler(Speler_Model sm) throws RemoteException {
		this.players.add(sm);
		notifyObservers();
	}

	/**
	 * Zet speler data, geeft aan dat ie ready is.
	 *
	 * @param sm
	 * @throws RemoteException
	 * @author	Robert
	 */
	public void setSpelerReady(Speler_Model sm) throws RemoteException{
		System.out.println(this.getClass().toString()+" setSpelerReady()");
		int spelerIndex=0;
		for (Speler_Model speler:players) {
			if(speler.getUsername().trim().equals(sm.getUsername().trim())){
				spelerIndex=players.indexOf(speler);
			}
		}
		this.players.set(spelerIndex,sm);
		this.players.get(spelerIndex).setReady(true);

		Collections.sort(players);
		for (Speler_Model speler:players) {
			System.out.println(this.getClass().toString()+" handgrootte: "+speler.getHandgrootte());
		}
		this.beurtStatus=BeurtStatus.NEERZETTEN;
		notifyObservers();
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
			e.printStackTrace();
			System.out.println("Spelbord_Model.addObserver");
		}
	}

	@Override
	public ArrayList<Player_Observer> observer_list() throws RemoteException {
		// TODO Auto-generated method stub
		return this.bord_observers;
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
		return aanDeBeurt;
	}

	@Override
	public int getHuidigeNiveau() throws RemoteException {
		// TODO Auto-generated method stub
		return this.huidigeNiveau;
	}

}
