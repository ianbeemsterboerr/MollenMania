package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import model.MolModel;
import model.Spelbord_Model;
import model.Speler_Model;
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
		if(bs_interface.getSpelState()== Spelbord_Model.SpelState.NEERZETTEN){
			System.out.println("Bordspel_Controller: clickAction NEERZETTEN");
			ArrayList<Speler_Model>  spelers = bs_interface.getSpelers();
			Speler_Model spelerIk = new Speler_Model();

			for (Speler_Model speler: spelers) {
				if(speler.getUsername().trim().equals(this.bijnaam.trim())){
					spelerIk=speler;
				}
			}
			if(bs_interface.maxSpelers()!=spelerIk.getMol_list().size()){
				//Hier kijk je of het veld een molshoop is of niet
			}
			bs_interface.setSpelState(Spelbord_Model.SpelState.FICHEDRAAIEN);
		} else if(bs_interface.getSpelState()==Spelbord_Model.SpelState.FICHEDRAAIEN){
			System.out.println("Bordspel_Controller: clickAction FICHEDRAAIEN");


		} else if (bs_interface.getSpelState()== Spelbord_Model.SpelState.SELECTEREN){
			System.out.println("Bordspel_Controller: clickAction SELECTERE");


		} else if (bs_interface.getSpelState()== Spelbord_Model.SpelState.VERPLAATSEN){
			System.out.println("Bordspel_Controller: clickAction VERPLAATSEN");


		} else {
			System.out.println("Bordspel_Controller: clickAction niet afgehandeld");
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
