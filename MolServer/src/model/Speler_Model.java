package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Speler_Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private int player_id;
	private ArrayList<MolModel> mol_list = new ArrayList<MolModel>();
	private int Handgrootte;
	private Fiche_Model fiche_list = new Fiche_Model();
	private ArrayList<Integer> fiches;
	private int fichesSize;
	private int molSize;
	private Speler_Model myself;
	private boolean aanDeBeurt=false;
	private int beurtSpeler;

	public Speler_Model(){

	}
	
	public Speler_Model(String user, int id, int beurtSpeler){
		this.username = user;
		this.player_id = id;
		this.beurtSpeler = beurtSpeler;
		this.myself = this;
		fillPawnList(6);
		this.setFiches(fiche_list.getGeslotenFiche());
		
		this.fichesSize = this.fiches.size();
		this.molSize = this.mol_list.size();
	}
	
	public ArrayList<Integer> getFiches() {
		return fiches;
	}

	public void setFiches(ArrayList<Integer> fiches) {
		this.fiches = fiches;
	}
	
	public void fillPawnList(int aantal){
		for(int i = 0; i < aantal; i++){
			MolModel mol = new MolModel(i);
			this.mol_list.add(mol);
		}
	}
	
	public ArrayList<MolModel> getMol_list() {
		return mol_list;
	}

	public void setMol_list(ArrayList<MolModel> mol_list) {
		this.mol_list = mol_list;
	}

    public int getHandgrootte() {   
        return Handgrootte;
    }

    public void setHandgrootte(int handgrootte) {
        Handgrootte = handgrootte;
    }

    public Fiche_Model getFiche_list() {
		return fiche_list;
	}

	public void setFiche_list(Fiche_Model fiche_list) {
		this.fiche_list = fiche_list;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public Speler_Model getMyself() {
		return myself;
	}

	public boolean isAanDeBeurt(){
		return aanDeBeurt;
	}
	public void setAanDeBeurt(boolean isAanDeBeurt){
		this.aanDeBeurt=aanDeBeurt;
	}

	public int getBeurtSpeler() { return beurtSpeler; }
	public void setBeurtSpeler(int beurtSpeler) { this.beurtSpeler = beurtSpeler; }

}
