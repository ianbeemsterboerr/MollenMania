package model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

import controller.Bordspel_Interface;
import controller.Player_Observer;

/**
 * Spelbord_Model is de container voor alle data in het hele spel. Deze is door elke client muteerbaar, zodat elke client het spel kan spelen.
 */
public class Spelbord_Model implements Bordspel_Interface{
	private ArrayList<Player_Observer> bord_observers = new ArrayList<Player_Observer>();
	private ArrayList<Speler_Model> players = new ArrayList<Speler_Model>();
	private ArrayList<MolModel> mol_onbord = new ArrayList<MolModel>();
	private Playboard_Model pmo = new Playboard_Model();
	private int aanDeBeurt=0;
	private int bordMax;
	private int huidigeNiveau = 1;
	private int maxMollen;
	private int beurtIndex;
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

	public int getMaxMollen() throws RemoteException{
		return this.maxMollen;

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
	public ArrayList<Player_Observer> observer_list() throws RemoteException {
		// TODO Auto-generated method stub
		return this.bord_observers;
	}

	public ArrayList<Speler_Model> getSpelers() throws  RemoteException {
		return this.players;
	}

	@Override
	public void veranderBeurt() throws RemoteException {
		System.out.println(this.getClass().toString()+": aanDeBeurt: "+aanDeBeurt);
		if(aanDeBeurt<(bordMax-1)){
			aanDeBeurt++;
		} else{
			aanDeBeurt=0;
		}
		System.out.println(this.getClass().toString()+": aanDeBeurt: "+aanDeBeurt);
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
		for(Speler_Model sm : players){
			if(sm.getMol_list().size() > 0){
				
			}		
		}
		return this.mol_onbord;
	}

	@Override
	public void addMolField(MolModel mol) throws RemoteException {
		// TODO Auto-generated method stub
		this.mol_onbord.add(mol);
	}

	@Override
	public int beurtIndex() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHuidigeNiveauIndex() throws RemoteException {
		// TODO Auto-generated method stub
		return this.huidigeNiveau;
	}

    @Override
    public void setMolCoord(Speler_Model speler, int[] coord, int molIndex) throws RemoteException {
        System.out.println("voor verplaatsen" +this.players.get(aanDeBeurt).getMol_list().get(molIndex).printCoord());
        this.players.get(aanDeBeurt).getMol_list().get(molIndex).setCoord(coord);
        System.out.println("na verplaatsen" +this.players.get(aanDeBeurt).getMol_list().get(molIndex).printCoord());
    }

	@Override
	public boolean setSpelerReady(Speler_Model sm) throws RemoteException{
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
		int readyCount=0;
		for (Speler_Model speler:players) {
			System.out.println(this.getClass().toString()+" handgrootte: "+speler.getHandgrootte());
			if(speler.isReady()){
				readyCount++;
			}
		}
		notifyObservers();
		if(readyCount==bordMax){
			this.beurtStatus=BeurtStatus.NEERZETTEN;
			return true;
		}
		return false;
	}

	@Override
	public void nextObserver() throws RemoteException {
		if (bord_observers.size() > 0) {
			bord_observers.get(beurtIndex).setEnabled(false);
			beurtIndex++;
			if (beurtIndex >= bord_observers.size()) {
				beurtIndex = 0;
			}
			bord_observers.get(beurtIndex).setEnabled(true);
		}
	}

	public void addMolltoList(int[] coordinaten)throws RemoteException{
		System.out.println("AddmolltoLIst" +coordinaten);
		this.players.get(aanDeBeurt).getMol_list().add(new MolModel(coordinaten));
		System.out.println(this.getClass().toString() +"aantalMollen(amtl): " +this.players.get(aanDeBeurt).getMol_list().size());
	}
	
@Override	
	public void notifyObservers() throws RemoteException {
		// TODO Auto-generated method stub
		for (Player_Observer co : bord_observers) {
			co.modelChanged(this);
		}
	}

	@Override
	public void changeNiveauInt() throws RemoteException {
		// TODO Auto-generated method stub
		for(Speler_Model sm : players){
			System.out.println(sm.getMol_list().size());
		}
//		this.huidigeNiveau = this.huidigeNiveau + 1;
	}

	@Override
	public Spelbord_Model getSpelbordModel() {
		return this;
	}


}
