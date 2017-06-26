package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Speler_Model;
import view.InstellingenView;
import view.Lobby_View;

public class Mol_Client {

	private InstellingenView instellingenView;
	private String bijnaam;

	public Mol_Client(String ip, String username){
		this.bijnaam=username;
		//this.instellingenView=instellingenView;
		try {
			// get access to the RMI registry on the remote server
			// if server on another machine: provide that machine's IP address. Default port  1099
			Registry registry = LocateRegistry.getRegistry(ip);

            //CALL OBJECT FROM INTERFACE IMPLEMENTATIONNN
			// get remote interface object from registry
			Bordspel_Interface userStub = (Bordspel_Interface) registry.lookup("Spelbord_Model");
			Bordspel_Controller bs_controller = new Bordspel_Controller(userStub, username);

			int spid = userStub.playerList().size()+1;
			//System.out.println(userStub.getClientHost());
			Speler_Model sm = new Speler_Model(username, spid);

			bs_controller.checkPlayerList(4, sm);

			new Lobby_View(userStub, bs_controller, this);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getBijnaam(){
		return this.bijnaam;
	}
}
