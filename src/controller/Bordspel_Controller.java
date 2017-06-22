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
	
	public Bordspel_Controller(Bordspel_Interface bs, String bijnaam) throws RemoteException{
		this.bijnaam=bijnaam;
		this.bs_interface = bs;

	}

	public void showSpelBordView() throws RemoteException{
		this.spelbordView=new SpelbordView(this, bs_interface);
	}
	
	public void checkPlayerList(int max, Speler_Model sm) throws RemoteException{
		int listSize = this.bs_interface.playerList().size();
		if(listSize < max){
			this.bs_interface.addSpeler(sm);
		} else {
			System.out.println("Player list is full.");
		}
	}
	
	public void spelerReady() throws RemoteException{
		int players_ready = this.bs_interface.readyList().size();
		
		if(players_ready == 2){
			new SpelbordView(this, this.bs_interface);
		} else{
			System.out.println("Waiting for players");
		}
	}

	public void checkZetValid(int[] positie){

	}

	public void refresh() throws RemoteException{
		spelbordView.modelChanged(bs_interface.spelModel());
	}
}
