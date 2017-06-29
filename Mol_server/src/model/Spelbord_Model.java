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
	private int beurtIndex =0;
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
	public ArrayList<Player_Observer> observer_list() throws RemoteException {
		// TODO Auto-generated method stub
		return this.bord_observers;
	}

	public ArrayList<Speler_Model> getSpelers() throws  RemoteException {
		return this.players;
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
	public int getMaxMollen() throws RemoteException {
		// TODO Auto-generated method stub
		return this.maxMollen;
	}

	@Override
	public int beurtIndex() throws RemoteException {
		// TODO Auto-generated method stub
		return beurtIndex;
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
	public int getHuidigeNiveauIndex() throws RemoteException {
		// TODO Auto-generated method stub
		return this.huidigeNiveau;
	}

	@Override
	public void setMolCoord(MolModel mol, int[] coord) throws RemoteException {
		System.out.println("test" +mol.getCoord());
		mol.setCoord(coord);
		System.out.println("test" +mol.getCoord());
	}

	public void addMolltoList(int[] coordinaten) throws RemoteException{
		System.out.println("AddmolltoLIst" +coordinaten);
		Speler_Model speler_model = this.players.get(beurtIndex);
		speler_model.getMol_list().add(new MolModel(coordinaten,speler_model.getKleur()));
		System.out.println(this.getClass().toString() +"aantalMollen(amtl): " +this.players.get(beurtIndex).getMol_list().size());
	}


	public void nextObserver() throws RemoteException{
		if (bord_observers.size() > 0) {
			bord_observers.get(beurtIndex).setEnabled(false);
			beurtIndex++;
			if (beurtIndex >= bord_observers.size()) {
				beurtIndex = 0;
			}
			bord_observers.get(beurtIndex).setEnabled(true);
		}
	}

	@Override
	public void veranderBeurt() throws RemoteException {
		System.out.println(this.getClass().toString()+": beurtIndex: "+ beurtIndex);
		if(beurtIndex <(bordMax-1)){
			beurtIndex++;
		} else{
			beurtIndex =0;
		}
		System.out.println(this.getClass().toString()+": beurtIndex: "+ beurtIndex);
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

}
