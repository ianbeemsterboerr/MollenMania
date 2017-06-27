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

	/**
	 * Deze method geeft aan dat er op een veld
	 * @param position
	 */
	public void clickAction(int[] position) throws RemoteException{
		System.out.println(this.getClass().toString()+": x: "+position[0]+" y:"+position[1]+" z:"+position[2]);
		if(bs_interface.getBeurtStatus()== BeurtStatus.NEERZETTEN){
			System.out.println("Bordspel_Controller: clickAction NEERZETTEN");

			//Een verzameling aan onnodige check die ik voor de zekerheid wil bewaren.
//			//persoon die nu aan de beurt is
//			Speler_Model huidigeSpeler = bs_interface.playerList().get(bs_interface.beurtIndex());
//
//			//check of die persoon nog mollen op het bord mag zetten
//			if(huidigeSpeler.getMol_list().size()<bs_interface.getMaxMollen()){
//
//				//kijken of de plek geen molshoop is: eerst huidig niveau index
//				int huidigNiveau = bs_interface.getHuidigeNiveauIndex();
//
//				//daarna het niveau echt pakken
//				Niveau_Model niveau=bs_interface.pm().getHuidigNiveau(huidigNiveau);
//
//				//daarna de molshopen pakken
//				ArrayList<Molshoop_Veld> molshopen = niveau.getMolshoop();
//
//				//Itereer door de molshopen er een is met dezelfde positie als de mol die de speler wilt plaatsen.
//				for (Molshoop_Veld molshoop:molshopen) {
//					if(Arrays.equals(molshoop.getPositie(),position)){
//						System.out.println();
//					}
//				}
//			}
//			bs_interface.setBeurtStatus(BeurtStatus.FICHEDRAAIEN);
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
	
	public void loadBoard(VeldKnop[] buttonArray, ArrayList<MolModel> mol, Playboard_Model pm, int niveau){
		loadSpelerMols(buttonArray, mol);
		loadMolsHoop(buttonArray, pm, niveau);
		loadSpecial(buttonArray, pm, niveau);
		
	}
	
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
						buttonArray[x].setDisable(true);
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		} else if(niveau == 2){
			for(Molshoop_Veld m : molshoop_niveau2){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(true);
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		} else if(niveau == 3){
			for(Molshoop_Veld m : molshoop_niveau3){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(true);
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		} else if(niveau == 4){
			for(Molshoop_Veld m : molshoop_niveau4){
				for(int x = 0; x < buttonArray.length; x++){
					if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
						buttonArray[x].setDisable(true);
						buttonArray[x].setStyle("-fx-background-color: blue;");
					}
				}
			}
		}
		
	}

	public boolean checkZetValid(MolModel molcheck, int[] positie){
		int [] mollist = molcheck.getCoord();

		if(Arrays.equals(mollist, positie)){
			return true;
		} else {
			return false;
		}

	}
	/**
	 * Deze method geeft aan dat er op een veld
	 */
	/*
	 * mol dinges. here we get the mol that is not yet in play and we give it coords.
	 * once coords have been set we up the index and go until all mols have been registered.
	 * 
	 * index max = the amount of mols u have in your list. so when the index is max
	 * it is time to switch turn
	 * 
	 */
	public void setMolCoords(Speler_Model sm, MolModel mol_placeholder, VeldKnop buttonBox, int mol_max, int mol_index){
		//starts at 0 -> how many mols am I going to have? to avoid list exceptions.
		/*
		 * mol dinges. here we get the mol that is not yet in play and we give it coords.
		 * once coords have been set we up the index and go until all mols have been registered.
		 * 
		 * index max = the amount of mols u have in your list. so when the index is max
		 * it is time to switch turn
		 * 
		 */
		if(sm.getMol_list().size() == mol_max){
			System.out.println("max");
		} else {
			//sm.getMol_list().add(new MolModel(buttonBox.getCoordinaten()));
			mol_placeholder = sm.getMol_list().get(mol_index);
			mol_placeholder.setCoord(buttonBox.getCoordinaten()); //mol 0 now has coords, index++

			System.out.println("Current mol index: " + sm.getMol_list().size());
		}
		
		/*
		 * here we send the mol to the board.
		 * model has new mol + it's coords -> register it for all to see.
		 */
		try {
			this.bs_interface.addMolField(mol_placeholder);
			System.out.println("Mols on field: " + bs_interface.molOnField().size());
			bs_interface.nextObserver();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;
	}

	public void refresh() throws RemoteException{
		spelbordView.playerDataTest(bs_interface.playerList());
	}
	
	public void assignMolCoord(Speler_Model sm, int[] coord){
		ArrayList<MolModel> mols = new ArrayList<MolModel>();
		mols = sm.getMol_list();
		mols.get(0).setCoord(coord);
		System.out.println(mols.get(0).getCoord());
	}

	public void showWinner(){
		System.out.println(this.getClass().toString()+": show winner");
		WinView winView = new WinView(instellingenPanelController.createInstInGameView(this));
	}

	public void opslaan(){
		System.out.println(this.getClass().toString()+": opslaan");
	}

	public void spelVerlaten(){
		System.out.println(this.getClass().toString()+": spelVerlaten");
	}
}
