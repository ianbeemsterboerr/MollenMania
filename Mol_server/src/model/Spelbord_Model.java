package model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import controller.Bordspel_Interface;
import controller.Player_Observer;
import model.Velden.Molshoop_Veld;

/**
 * Spelbord_Model is de container voor alle data in het hele spel. Deze is door elke client muteerbaar, zodat elke client het spel kan spelen.
 */
public class Spelbord_Model implements Bordspel_Interface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Player_Observer> bord_observers = new ArrayList<>();
	private ArrayList<Speler_Model> players = new ArrayList<>();
	private ArrayList<MolModel> mol_onbord = new ArrayList<>();
	private ArrayList<Speler_Model> hervat_spelers = new ArrayList<>();
	private int beurtIndex =0;
	private int huidigeNiveau = 1;
	private int bordMax;
	private boolean hervatStatus;
	private int maxMollen;

	private BeurtStatus beurtStatus;
	
	public Spelbord_Model(){
		
	}

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
		if(readyCount==bordMax){
			this.beurtStatus=BeurtStatus.BORDSTARTEN;
		}
		notifyObservers();
		if(readyCount==bordMax){
			return true;
		}
		return false;
	}

	@Override
	public void setSpelerInGame(String bijnaam)throws RemoteException{
		int inGameCount=0;
		for (Speler_Model speler:players) {
			if(speler.getUsername().trim().equals(bijnaam.trim())){
				speler.setInGame(true);
			}
			if(speler.isInGame()){
				inGameCount++;
			}
		}
		if(inGameCount==bordMax){
			beurtStatus=BeurtStatus.NEERZETTEN;
		}
		notifyObservers();
	}

	@Override
	public ArrayList<Speler_Model> playerList() throws RemoteException {
		// TODO Auto-generated method stub
		return this.players;
	}

	public void notifyObservers() throws RemoteException {
		for (Player_Observer co : bord_observers) {
			System.out.println(this.getClass().toString()+": notifyObservers "+co.getBijnaam());
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
		this.huidigeNiveau = this.huidigeNiveau + 1;
	}

	@Override
	public int getHuidigeNiveauIndex() throws RemoteException {
		// TODO Auto-generated method stub
		return this.huidigeNiveau;
	}

	@Override
	public void setMolCoord(Speler_Model speler, int[] coord, int molIndex) throws RemoteException {
		System.out.println("voor verplaatsen" +this.players.get(beurtIndex).getMol_list().get(molIndex).printCoord());
		this.players.get(beurtIndex).getMol_list().get(molIndex).setCoord(coord);
		System.out.println("na verplaatsen" +this.players.get(beurtIndex).getMol_list().get(molIndex).printCoord());
	}

	@Override
	public Spelbord_Model getSpelbordModel() {
		return this;
	}

	public void addMolltoList(int[] coordinaten)throws RemoteException{
		System.out.println("AddmolltoLIst" +coordinaten);
		this.players.get(beurtIndex).getMol_list().add(new MolModel(coordinaten, players.get(beurtIndex).getKleur()));
		System.out.println(this.getClass().toString() +"aantalMollen(amtl): " +this.players.get(beurtIndex).getMol_list().size());
	}

	@Override
	public void deleteMollfromList()throws RemoteException {
		Playboard_Model playboardModel = new Playboard_Model();
		Niveau_Model niveauModel = playboardModel.getHuidigNiveau(this.getHuidigeNiveauIndex());
		ArrayList<MolModel> molToRemove = new ArrayList<>();
		int counter = 0;
		for ( Speler_Model speler : this.players){
			for (MolModel mol : speler.getMol_list()){
				for (Molshoop_Veld molshoopVeld : niveauModel.getMolshoop()) {
					if (!Arrays.equals(mol.getCoord(),molshoopVeld.getPositie())){
						counter++;
						System.out.println(counter);
						if (counter == niveauModel.getMolshoop().size())
							molToRemove.add(mol);
					}
				}
				counter = 0;
			}
			System.out.println("Size voor del" +this.players.get(this.players.indexOf(speler)).getMol_list().size());
			System.out.println("size van RemoveMolList" +molToRemove.size());
			this.players.get(this.players.indexOf(speler)).getMol_list().removeAll(molToRemove);
			molToRemove.clear();
			System.out.println("size na DEL" +this.players.get(this.players.indexOf(speler)).getMol_list().size());
			System.out.println("size van RemoveMolList" +molToRemove.size());
		}

	}

	@Override
	public void veranderBeurt() throws RemoteException {
		System.out.println(this.getClass().toString()+": aanDeBeurt: "+beurtIndex);
		beurtIndex++;
		if (beurtIndex >= bord_observers.size()) {
			beurtIndex = 0;
		}
		System.out.println(this.getClass().toString()+": aanDeBeurt: "+beurtIndex);
	}

	@Override
	public void addObserver(Player_Observer po, String bijnaam) throws RemoteException {
		// TODO Auto-generated method stub

		bord_observers.add(po);

		boolean exists=false;
		for (Player_Observer observer: bord_observers){
			if(observer.getBijnaam().trim().equals(bijnaam.trim())){
				int i = bord_observers.lastIndexOf(observer);
				bord_observers.set(i,po);
				exists=true;
			}
		}
		if(!exists){
			bord_observers.add(po);
		}

		try {
			notifyObservers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void testMe() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("test");
	}

	public ArrayList<MolModel> getMollen() throws RemoteException {
		return mol_onbord;
	}

	public int getBordMax(){
		return this.bordMax;
	}

	public void setPlayers(ArrayList<Speler_Model> players){
		this.players = players;
	}

	public void setBeurtIndex(int beurtIndex){
		this.beurtIndex = beurtIndex;
	}

	public void setHuidigeNiveau(int huidigeNiveau){
		this.huidigeNiveau = huidigeNiveau;
	}

	public void setMaxMollen(int maxMollen){
		this.maxMollen = maxMollen;
	}

	@Override
	public boolean getHervatStatus() throws RemoteException {
		// TODO Auto-generated method stub
		return this.hervatStatus;
	}

	@Override
	public void setHervatStatus(boolean status) throws RemoteException {
		// TODO Auto-generated method stub
		this.hervatStatus = status;
	}

	@Override
	public void addHervatSpelerKlaar(Speler_Model speler) throws RemoteException {
		// TODO Auto-generated method stub
		hervat_spelers.add(speler);
	}

	@Override
	public ArrayList<Speler_Model> getHervatSpelersList() throws RemoteException {
		// TODO Auto-generated method stub
		return this.hervat_spelers;
	}
}
