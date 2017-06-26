package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import model.BeurtStatus;
import model.MolModel;
import model.Niveau_Model;
import model.Playboard_Model;
import model.Spelbord_Model;
import model.Speler_Model;
import model.Velden.Molshoop_Veld;
import model.Velden.VeldKnop;
import view.SpelbordView;

public class Bordspel_Controller {
	
	Bordspel_Interface bs_interface;
	SpelbordView spelbordView;
	private String bijnaam;
	MolController molController;
	Fiche_Controller fiche_controller;

	private int kerenGeweest;
	private int[] geselecteerdeMolPos;
	private int gedraaideFicheWaarde;

	
	public Bordspel_Controller(Bordspel_Interface bs, String bijnaam) throws RemoteException{
		this.bijnaam=bijnaam;
		this.bs_interface = bs;
	}

	public void showSpelBordView() throws RemoteException{
		this.fiche_controller = new Fiche_Controller(); // krijgt bs_interface ?
		this.molController = new MolController(); //krijgt bs_interface ?

		this.spelbordView=new SpelbordView(this, bs_interface, this.bijnaam);
	}
	
	public void checkPlayerList(int max, Speler_Model sm) throws RemoteException{
		int listSize = this.bs_interface.playerList().size();
		if(listSize < max){
			this.bs_interface.addSpeler(sm);
		} else {
			System.out.println("Player list is full.");
		}
	}
	
	public void spelerReady(ArrayList<Speler_Model> rlist) throws RemoteException{
		int players_ready = rlist.size();
		int max = this.bs_interface.maxSpelers();
		if(players_ready == max){
			new SpelbordView(this, bs_interface, this.bijnaam);
		} else{
			System.out.println(players_ready);
			System.out.println("Waiting for players");
		}
	}


	/**
	 * Deze method geeft aan dat er op een veld
	 * @param position
	 */
	public void clickAction(int[] position) throws RemoteException{
		System.out.println(this.getClass().toString()+": x: "+position[0]+" y:"+position[1]+" z:"+position[2]);
//		if(bs_interface.getBeurtStatus()== BeurtStatus.NEERZETTEN){
//			System.out.println("Bordspel_Controller: clickAction NEERZETTEN");
//			ArrayList<Speler_Model>  spelers = bs_interface.getSpelers();
//			Speler_Model spelerIk = new Speler_Model();
//
//			for (Speler_Model speler: spelers) {
//				if(speler.getUsername().trim().equals(this.bijnaam.trim())){
//					spelerIk=speler;
//				}
//			}
//
//			if(bs_interface.maxSpelers()!=spelerIk.getMol_list().size()){
//				//Hier kijk je of het veld een molshoop is of niet
//			}
//			bs_interface.setBeurtStatus(BeurtStatus.FICHEDRAAIEN);
//		} else if(bs_interface.getBeurtStatus()== BeurtStatus.FICHEDRAAIEN){
//			System.out.println("Bordspel_Controller: clickAction FICHEDRAAIEN");
//
//
//		} else if (bs_interface.getBeurtStatus()== BeurtStatus.SELECTEREN){
//			System.out.println("Bordspel_Controller: clickAction SELECTERE");
//
//
//		} else if (bs_interface.getBeurtStatus()== BeurtStatus.VERPLAATSEN){
//			System.out.println("Bordspel_Controller: clickAction VERPLAATSEN");
//
//
//		} else {
//			System.out.println("Bordspel_Controller: clickAction niet afgehandeld");
//		}
	}
	public void loadBoard(VeldKnop[] buttonArray, ArrayList<MolModel> mol, Playboard_Model pm){
		loadSpelerMols(buttonArray, mol);
		loadMolsHoop(buttonArray, pm);
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
	
	public void loadMolsHoop(VeldKnop[] buttonArray, Playboard_Model pm){
		for(Molshoop_Veld m : pm.getNiveau1().getMolshoop()){
			for(int x = 0; x < buttonArray.length; x++){
				if(Arrays.equals(m.getPositie(), buttonArray[x].getCoordinaten())){
					buttonArray[x].setDisable(true);
					buttonArray[x].setStyle("-fx-background-color: blue;");
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
	 * @param position
	 */

	private void checkZetValid(int[] positie){

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


}
