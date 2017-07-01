package model;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class Speler_Model implements Serializable, Comparable<Speler_Model>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private int player_id;
	private ArrayList<MolModel> mol_list = new ArrayList<MolModel>();
	private int Handgrootte;
	private String kleur;
	private Fiche_Model fiche_list = new Fiche_Model();
	private int fichesSize;
	private int molSize;
	private Speler_Model myself;
	private boolean isReady=false;
	private boolean inGame=false;

	public Speler_Model(){

	}
	
	public Speler_Model(String user, int id){
		this.username = user;
		this.player_id = id;
		this.myself = this;
		this.molSize = this.mol_list.size();
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

	public String getKleur() {
		return kleur;
	}

	public void setKleur(String kleur) {
		this.kleur = kleur;
	}

	/**
	 * toegevoegd zodat de Comparable interface geimplementeerd kan worden, wat belangrijk is voor het bepalen van de volgorde van de spelers.
	 * @param sm
	 * @return
	 */
	@Override
	public int compareTo(Speler_Model sm) {
		int val  = ((Speler_Model) sm).getHandgrootte();
		return val-this.getHandgrootte();
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean ready) {
		isReady = ready;
	}


	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
}
