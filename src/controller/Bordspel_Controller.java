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

	public String getBijnaam() {
		return bijnaam;
	}


	/**
	 * Handelt een klik op een mol af. kijkt in welke fase het spel is en handelt de klik zodanig af.
	 *
	 * @param position De positie van de knop waarop werd geklikt.
	 */
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

	/**
	 * Zet de coordinaten van de beklikte knop over naar de mol.
	 * @param sm De SpelerModel die eigenaar is van de mol.
	 * @param mol_placeholder Een placeholder voor een mol.
	 * @param buttonBox Het veld waarop de mol wordt neergezet.
	 * @param mol_max Maximaal aantal mollen. gebaseerd op het aantal spelers die meedoen aan het spel.
	 * @param mol_index index van de actieve mol.
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
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	}

	public void spelVerlaten(){
		System.out.println(this.getClass().toString()+": spelVerlaten");
	}



}
