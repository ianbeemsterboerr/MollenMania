package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Speler_Model;

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
		int players_ready = 0;
		if(players_ready < 4){
			
		} else {
			System.out.println("Player list is full.");
		}
	}
}
