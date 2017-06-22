package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Spelbord_Model;
import model.Speler_Model;
import view.InstellingenView;
import view.Lobby_View;

public class Mol_Client {

	private InstellingenView instellingenView;
	private Speler_Model speler_model;
	private Bordspel_Interface userStub;

	public Mol_Client(String username){
		//this.instellingenView=instellingenView;
		try {
			// get access to the RMI registry on the remote server
			// if server on another machine: provide that machine's IP address. Default port  1099
			Registry registry = LocateRegistry.getRegistry("127.0.0.1");

			//CALL OBJECT FROM INTERFACE IMPLEMENTATIONNN
			// get remote interface object from registry
			this.userStub = (Bordspel_Interface) registry.lookup("Spelbord_Model");
			Bordspel_Controller bs_controller = new Bordspel_Controller(userStub);

			int spid = userStub.playerList().size()+1;
			//System.out.println(userStub.getClientHost());
			this.speler_model = new Speler_Model(username, spid);

			bs_controller.checkPlayerList(4, speler_model);

			new Lobby_View(userStub, bs_controller, this);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void notifyKlaar() throws RemoteException {
		this.userStub.addSpelerReady(this.speler_model);
	}
}
