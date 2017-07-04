package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * De Speler klasse bevat alle data die een speler moet hebben, zoals mollen, naam, de gekozen kleur etc.
 */
public class Speler_Model implements Serializable, Comparable<Speler_Model>{


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

	/**
	 * Maakt een Speler aan.
	 * @param user username van de speler.
	 * @param id het ID van de speler.
	 */
	public Speler_Model(String user, int id){
		this.username = user;
		this.player_id = id;
		this.myself = this;
		this.molSize = this.mol_list.size();
	}

	/**
	 * Geeft alle mollen van een speler.
	 * @return ArrayList<MolModel>
	 */
	public ArrayList<MolModel> getMol_list() {
		return mol_list;
	}

	/**
	 * Set de mollen van een Player.
	 * @param mol_list De lijst van mollen die geset moet worden.
	 */
	public void setMol_list(ArrayList<MolModel> mol_list) {
		this.mol_list = mol_list;
	}

	/**
	 * Geeft de handgrootte van de Player.
	 * @return int
	 */
    public int getHandgrootte() {
        return Handgrootte;
    }

	/**
	 * set de handgrootte van een Player.
	 * @param handgrootte int
	 */
    public void setHandgrootte(int handgrootte) {
        Handgrootte = handgrootte;
    }

	/**
	 * Geeft de fiches van een Player.
	 * @return ArrayList<Fiche_Model>
	 */
    public Fiche_Model getFiche_list() {
		return fiche_list;
	}

	/**
	 * set de fiches van een Player.
	 * @param fiche_list ArrayList.
	 */
	public void setFiche_list(Fiche_Model fiche_list) {
		this.fiche_list = fiche_list;
	}

	/**
	 * geeft de username van een Player.
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * geeft de player ID terug.
	 * @return int.
	 */
	public int getPlayer_id() {
		return player_id;
	}

	/**
	 * Set de player ID.
	 * @param player_id int.
	 */
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	/**
	 * geeft de gehele Speler klasse.
	 * @return
	 */
	public Speler_Model getMyself() {
		return myself;
	}

	/**
	 * geeft de door de speler gekozen kleur.
	 * @return String.
	 */
	public String getKleur() {
		return kleur;
	}

	/**
	 * Set de kleur van de speler.
	 * @param kleur String
	 */
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

	/**
	 * geeft terug of de speler klaar is om het spel te starten.
	 * @return boolean.
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * Set of de speler klaar is of niet.
	 * @param ready
	 */
	public void setReady(boolean ready) {
		isReady = ready;
	}

	/**
	 * Geeft aan of de speler ingame is of niet.
	 * @return
	 */
	public boolean isInGame() {
		return inGame;
	}

	/**
	 * Set op true als de speler ingame zit.
	 * @param inGame
	 */
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
}
