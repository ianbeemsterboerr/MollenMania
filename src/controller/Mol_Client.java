package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Speler_Model;
import view.InstellingenView;
import view.Lobby_View;

public class Mol_Client {

	private InstellingenPanelController instellingenPanelController;
	private String bijnaam;
	private Bordspel_Controller bs_controller;

	public Mol_Client(String ip, String username, InstellingenPanelController instellingenPanelController){
		this.instellingenPanelController=instellingenPanelController;
		this.bijnaam=username;
		//this.instellingenView=instellingenView;
		try {
			// get access to the RMI registry on the remote server
			// if server on another machine: provide that machine's IP address. Default port  1099
			Registry registry = LocateRegistry.getRegistry(ip);

            //CALL OBJECT FROM INTERFACE IMPLEMENTATIONNN
			// get remote interface object from registry
			Bordspel_Interface userStub = (Bordspel_Interface) registry.lookup("Spelbord_Model");
			this.bs_controller = new Bordspel_Controller(userStub, username);

			int spid = userStub.playerList().size()+1;
			//System.out.println(userStub.getClientHost());
			Speler_Model sm = new Speler_Model(username, spid);

			bs_controller.checkPlayerList(4, sm);

			new Lobby_View(userStub, bs_controller, this);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void naarSpelBord() throws RemoteException{
		System.out.println(this.getClass().toString()+": naarSpelBord()");
		bs_controller.showSpelBordView(instellingenPanelController);
	}

	public String getBijnaam(){
		return this.bijnaam;
	}
}
