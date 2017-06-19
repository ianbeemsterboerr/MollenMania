package controller;

import java.rmi.RemoteException;

import model.MolModel;
import model.Speler_Model;

public class Bordspel_Controller {
	
	Bordspel_Interface bs_interface;
	
	public Bordspel_Controller(Bordspel_Interface bs) throws RemoteException{
		this.bs_interface = bs;
	}
	
	public void checkPlayerList(int max, Speler_Model sm) throws RemoteException{
		int listSize = this.bs_interface.getSpelerListSize();
		if(listSize < max){
			this.bs_interface.addSpeler(sm);
		} else {
			System.out.println("Player list is full.");
		}
	}
	
	public void loadSpel(){
		
	}
	
	public void printNaam(Speler_Model sm){
		for(MolModel mol : sm.getMol_list()){
			System.out.println(mol.getPionID());
		}
	}
	
}
