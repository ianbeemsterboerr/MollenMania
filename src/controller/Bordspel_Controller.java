package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.MolModel;
import model.Speler_Model;
import view.SpelbordView;

public class Bordspel_Controller {
	
	Bordspel_Interface bs_interface;
	SpelbordView spelbordView;
	private String bijnaam;
	MolController molController;
	Fiche_Controller fiche_controller;
	
	public Bordspel_Controller(Bordspel_Interface bs, String bijnaam) throws RemoteException{
		this.bijnaam=bijnaam;
		this.bs_interface = bs;
	}

	public void showSpelBordView() throws RemoteException{
		this.fiche_controller = new Fiche_Controller(); // krijgt bs_interface
		this.molController = new MolController(); //krijgt bs_interface

		this.spelbordView=new SpelbordView(this, bs_interface, molController, fiche_controller, this.bijnaam);
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
			new SpelbordView(this, bs_interface, molController, fiche_controller, this.bijnaam);
		} else{
			System.out.println(players_ready);
			System.out.println("Waiting for players");
		}
	}

	/**
	 * Deze method geeft aan dat er op een veld
	 * @param position
	 */
	public void clickAction(int[] position){
	}

	private void checkZetValid(int[] positie){

	}

	public void refresh() throws RemoteException{
		spelbordView.playerDataTest(bs_interface.playerList());
	}

}
