package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import controller.moluser_interface;

public class Spelbord_Model{
	
	private Player_Model player;
	private ArrayList<Player_Model> players;
	
//	public Spelbord_Model(){
//		button = new button []{
//				add new button ( dsdsd ),
//				add new
//		}
//		for <
//	}
	
	public Spelbord_Model(Player_Model pl){
		this.player = pl;
	}
	
	public void addSpeler(){
		this.players.add(player);
	}
	
	public ArrayList<Player_Model> speler_list(Player_Model player){
		return players;
	}	
}
