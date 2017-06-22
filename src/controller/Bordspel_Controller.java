package controller;

import java.rmi.RemoteException;

import model.Speler_Model;
import view.SpelbordView;

public class Bordspel_Controller {
	
	Bordspel_Interface bs_interface;
	
	public Bordspel_Controller(Bordspel_Interface bs) throws RemoteException{
		this.bs_interface = bs;
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
}
