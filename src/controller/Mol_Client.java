package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Fiche_Model;
import model.Speler_Model;
import view.Lobby_View;

public class Mol_Client {
	
	public Mol_Client(String username){
		try {
			// get access to the RMI registry on the remote server	
			// if server on another machine: provide that machine's IP address. Default port  1099
			Registry registry = LocateRegistry.getRegistry("145.101.91.52");
			
            //CALL OBJECT FROM INTERFACE IMPLEMENTATIONNN
			// get remote interface object from registry
			Bordspel_Interface userStub = (Bordspel_Interface) registry.lookup("Spelbord_Model");
			Bordspel_Controller bs_controller = new Bordspel_Controller(userStub);
			
			int spid = userStub.getSpelerListSize()+1;
			Fiche_Model fm = new Fiche_Model();
			
			Speler_Model sm = new Speler_Model(username, spid);
			
			bs_controller.checkPlayerList(4, sm);
			
			new Lobby_View(userStub, bs_controller);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
