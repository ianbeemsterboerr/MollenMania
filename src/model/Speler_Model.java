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
	private Fiche_Model fiche_list = new Fiche_Model();
	private ArrayList<Integer> fiches;
	
	

	public Speler_Model(){
		
	}
	
	public Speler_Model(String user, int id){
		this.username = user;
		this.player_id = id;
		fillPawnList(6);
		this.setFiches(fiche_list.getGeslotenFiche());
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
}
