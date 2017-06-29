package controller;

import model.*;
import model.Velden.Molshoop_Veld;
import model.Velden.SpeciaalVeld_Veld;
import model.Velden.VeldKnop;
import view.SpelbordView;
import view.WinView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Bestuurt alles wat er op het bordspel gebeurt. laat het de BordSpelView en kan data uit het servermodel halen.
 */
public class Bordspel_Controller {
	
	Bordspel_Interface bs_interface;
	SpelbordView spelbordView;

	private String bijnaam;
	MolController molController;
	Fiche_Controller fiche_controller;
	private InstellingenPanelController  instellingenPanelController;

	private int kerenGeweest;
	private int[] geselecteerdeMolPos;
	private int gedraaideFicheWaarde;

	public Bordspel_Controller(){}
	public Bordspel_Controller(Bordspel_Interface bs, String bijnaam) throws RemoteException{
		this.bijnaam=bijnaam;
		this.bs_interface = bs;
	}

	public void showSpelBordView(InstellingenPanelController instellingenPanelController) throws RemoteException{
		this.instellingenPanelController=instellingenPanelController;
		this.fiche_controller = new Fiche_Controller(); // krijgt bs_interface ?
		this.molController = new MolController(); //krijgt bs_interface ?
		this.spelbordView=new SpelbordView(this, bs_interface, this.bijnaam, instellingenPanelController.createInstInGameView(this));
	}
	
	public void checkPlayerList(int max, Speler_Model sm) throws RemoteException{
		int listSize = this.bs_interface.playerList().size();
		if(listSize < max){
			this.bs_interface.addSpeler(sm);
		} else {
			System.out.println("Player list is full.");
		}
	}

	/**
	 * @since 0.2
	 * 
	 * Disables the current CounterObserver in the ArrayList observers and
	 * enables the next CounterObserver in the list. This way the turn of one
	 * CounterObserver ends and the other one starts
	 * 
	 * @throws RemoteException
	 *             RemoteException when the connection between RMI client and
	 *             server is compromised
	 * 
	 */
	private void nextCounterObserver() throws RemoteException {
		int beurt_index = bs_interface.beurtIndex();
		if (bs_interface.observer_list().size() > 0) {
			bs_interface.observer_list().get(bs_interface.beurtIndex()).setEnabled(false);
			beurt_index++;
			if (bs_interface.beurtIndex() >= bs_interface.observer_list().size()) {
				beurt_index = 0;
			}
			bs_interface.observer_list().get(bs_interface.beurtIndex()).setEnabled(true);
		}
	}
	
//	public void spelerReady(ArrayList<Speler_Model> rlist) throws RemoteException{
//		int players_ready = rlist.size();
//		int max = this.bs_interface.maxSpelers();
//
//		if(players_ready == max){
//			new SpelbordView(this, bs_interface, this.bijnaam);
//		} else{
//			System.out.println(players_ready);
//			System.out.println("Waiting for players");
//		}
//	}

	public String getBijnaam() {
		return bijnaam;
	}

	/**
	 * Deze method geeft aan dat er op een veld
	 * @param position
	 * @throws RemoteException 
	 * Handelt een klik op een mol af. kijkt in welke fase het spel is en handelt de klik zodanig af.
	 *
	 * @param position De positie van de knop waarop werd geklikt.
	 */
	
	public void changeNiveau(ArrayList<MolModel> player_mols, VeldKnop[] buttonArray, int current_level) throws RemoteException{
		Playboard_Model pm = new Playboard_Model();
		
		ArrayList<Molshoop_Veld> niveau1 = pm.getNiveau1().getMolshoop();
		ArrayList<Molshoop_Veld> niveau2 = pm.getNiveau2().getMolshoop();
		ArrayList<Molshoop_Veld> niveau3 = pm.getNiveau3().getMolshoop();
		
		switch(current_level){
		case 1:
			for(MolModel mollen : player_mols){
				for(Molshoop_Veld molshopen : niveau1){
					if(mollen.getCoord() == molshopen.getPositie()){
						player_mols.remove(mollen);
						System.out.println("Mols on field: " + player_mols.size());
						this.loadBoard(buttonArray, player_mols, new Playboard_Model(), bs_interface.getHuidigeNiveauIndex());
					} else{
						System.out.println("Mol zit in molshoop");
					}
				}
			}	
		case 2:
			for(MolModel mollen : player_mols){
				for(Molshoop_Veld molshopen : niveau2){
					if(mollen.getCoord() == molshopen.getPositie()){
						player_mols.remove(mollen);
						System.out.println("Mols on field: " + player_mols.size());
						this.loadBoard(buttonArray, player_mols, new Playboard_Model(), bs_interface.getHuidigeNiveauIndex());
					} else{
						System.out.println("Mol zit in molshoop");
					}
				}
			}
		case 3: 
			for(MolModel mollen : player_mols){
				for(Molshoop_Veld molshopen : niveau3){
					if(mollen.getCoord() != molshopen.getPositie()){
						player_mols.remove(mollen);
						System.out.println("Mols on field: " + player_mols.size());
						this.loadBoard(buttonArray, player_mols, new Playboard_Model(), bs_interface.getHuidigeNiveauIndex());
					} else{
						System.out.println("Mol zit in molshoop");
					}
				}
			}
		}
		System.out.println("Mols on field: " + player_mols.size());
	}
	
	public void clickAction(int[] position) throws RemoteException{
		System.out.println(this.getClass().toString()+": x: "+position[0]+" y:"+position[1]+" z:"+position[2]);
		if(bs_interface.getBeurtStatus()== BeurtStatus.NEERZETTEN){
			System.out.println("Bordspel_Controller: clickAction NEERZETTEN");

		} else if(bs_interface.getBeurtStatus()== BeurtStatus.FICHEDRAAIEN){
			System.out.println("Bordspel_Controller: clickAction FICHEDRAAIEN");


		} else if (bs_interface.getBeurtStatus()== BeurtStatus.SELECTEREN){
			System.out.println("Bordspel_Controller: clickAction SELECTERE");


		} else if (bs_interface.getBeurtStatus()== BeurtStatus.VERPLAATSEN){
			System.out.println("Bordspel_Controller: clickAction VERPLAATSEN");


		} else {
			System.out.println("Bordspel_Controller: clickAction niet afgehandeld");
		}
	}

	/**
	 * Pakt alle velden die gebruikt gaan worden en plaatst ze op de view.
	 *
	 * @param buttonArray Alle velden waar een pion kan staan in het spel.
	 * @param mol Alle mollen die op een veld staan.
	 * @param pm Model waar alle statische veldinformatie staat opgeslagen zoals molshopen, speciale velden en de gouden schep.
	 * @param niveau Het niveau waarin het spel zich op dit moment bevindt.
	 */
	public void loadBoard(VeldKnop[] buttonArray, ArrayList<MolModel> mol, Playboard_Model pm, int niveau){
		//loadSpelerMols(buttonArray, mol);
		//loadMolsHoop(buttonArray, pm, niveau);
		//loadSpecial(buttonArray, pm, niveau);
	}

	/**
	 * Laadt de 'speciale' velden.
	 *
	 * @param buttonArray Alle velden waar een pion kan staan in het spel.
	 * @param pm Model waar alle statische veldinformatie staat opgeslagen zoals molshopen, speciale velden en de gouden schep.
	 * @param niveau Het niveau waarin het spel zich op dit moment bevindt.
	 */
	public void loadSpecial(VeldKnop[] buttonArray, Playboard_Model pm, int niveau){
		
		ArrayList<SpeciaalVeld_Veld> special_niveau2 = pm.getNiveau2().getSpeciaal();
		ArrayList<SpeciaalVeld_Veld> special_niveau3 = pm.getNiveau3().getSpeciaal();
		ArrayList<SpeciaalVeld_Veld> special_niveau4 = pm.getNiveau4().getSpeciaal();
		
		if(niveau == 2){
			for(SpeciaalVeld_Veld m : special_niveau2){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(true);
						buttonArray[x].setStyle("-fx-background-color: yellow;");
					}
				}
			}
		} else if(niveau == 3){
			for(SpeciaalVeld_Veld m : special_niveau3){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(true);
						buttonArray[x].setStyle("-fx-background-color: yellow;");
					}
				}
			}
		} else if(niveau == 4){
			for(SpeciaalVeld_Veld m : special_niveau4){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(true);
						buttonArray[x].setStyle("-fx-background-color: yellow;");
					}
				}
			}
		}
	}

	public void loadSpelerMols(VeldKnop[] buttonArray, ArrayList<MolModel> mol){
		for(MolModel m : mol){
			for(int x = 0; x < buttonArray.length; x++){
				if(Arrays.equals(m.getCoord(), buttonArray[x].getCoordinaten())){
					buttonArray[x].setDisable(true);
					buttonArray[x].setStyle("-fx-background-color: red;");
				}
			}
		}
	}
	
	public void loadMolsHoop(VeldKnop[] buttonArray, Playboard_Model pm, int niveau){
		ArrayList<Molshoop_Veld> molshoop_niveau1 = pm.getNiveau1().getMolshoop();
		ArrayList<Molshoop_Veld> molshoop_niveau2 = pm.getNiveau2().getMolshoop();
		ArrayList<Molshoop_Veld> molshoop_niveau3 = pm.getNiveau3().getMolshoop();
		ArrayList<Molshoop_Veld> molshoop_niveau4 = pm.getNiveau4().getMolshoop();
		
		if(niveau == 1){
			for(Molshoop_Veld m : molshoop_niveau1){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		} else if(niveau == 2){
			for(Molshoop_Veld m : molshoop_niveau2){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		} else if(niveau == 3){
			for(Molshoop_Veld m : molshoop_niveau3){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		} else if(niveau == 4){
			for(Molshoop_Veld m : molshoop_niveau4){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		}



	}

	/**
	 * Laat wanneer het spel afgelopen is de winnaar zien. Creeert een WinView.
	 */
	public void showWinner(){
		System.out.println(this.getClass().toString()+": show winner");
		WinView winView = new WinView(instellingenPanelController.createInstInGameView(this));
	}

	public void opslaan(){
		System.out.println(this.getClass().toString()+": opslaan");
		new SpelSaveController(bs_interface);
	}

	public void spelVerlaten(){
		System.out.println(this.getClass().toString()+": spelVerlaten");
	}


}
