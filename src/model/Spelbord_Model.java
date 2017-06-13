package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import controller.moluser_interface;

public class Spelbord_Model{
	
	private Player_Model player;
	private ArrayList<Player_Model> players;
	
	public Spelbord_Model(){
		
	}
	
	public void Spelbord_Model(Player_Model player){
		this.players.add(player);
	}
	
	public ArrayList playerList(){
		return this.players;
	}
	

}
