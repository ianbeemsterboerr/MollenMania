package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Speler_Model;
import view.HervatLobbyView;
import view.InstellingenView;
import view.Lobby_View;

/**
 * Bevat alle functies voor het zijn van een client.
 */
public class Mol_Client {

	private InstellingenPanelController instellingenPanelController;
	private String bijnaam;
	private Bordspel_Controller bs_controller;

	/**
	 * Maakt een nieuw MolClient object aan.
	 * @param ip Het IP adres waarmee verbonden moet worden.
	 * @param username De username waarmee de user in de lobby komt te staan.
	 * @param instellingenPanelController De instellingenpanelcontroller die ervoor zorgt dat er in de game een instellingenpanel staat.
	 */
	public Mol_Client(String ip, String username, InstellingenPanelController instellingenPanelController){
		this.instellingenPanelController=instellingenPanelController;
		this.bijnaam=username;
		//this.instellingenView=instellingenView;
		try {
			// get access to the RMI registry on the remote server
			// if server on another machine: provide that machine's IP address. Default port  1099
			Registry registry = LocateRegistry.getRegistry(ip,1099);

            //CALL OBJECT FROM INTERFACE IMPLEMENTATIONNN
			// get remote interface object from registry
			Bordspel_Interface userStub = (Bordspel_Interface) registry.lookup("Spelbord_Model");
			this.bs_controller = new Bordspel_Controller(userStub, username);

			int spid = userStub.playerList().size()+1;
			//System.out.println(userStub.getClientHost());
			Speler_Model sm = new Speler_Model(username, spid);

			//check if boolean hervat status is true if so, load game via hervat route.
			if(userStub.getHervatStatus() == true){
				for(Speler_Model speler : userStub.playerList()){
						if(speler.getUsername().equals(username)){
							System.out.println("User found");
							new HervatLobbyView(userStub, bs_controller, this);
						} else{
							Alert alert = new Alert(AlertType.WARNING, 
			                        "Current game is a saved game, please enter the username used in that game.");
							alert.show();
						}
					}
				} else {
					System.out.println("New game!");
					bs_controller.checkPlayerList(4, sm);
					new Lobby_View(userStub, bs_controller, this);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Laat het spelbord zien aan alle verbonden clients.
	 * @throws RemoteException Wanneer de connectie tussen client en server wordt onderbroken.
	 */
	public void naarSpelBord() throws RemoteException{
		System.out.println(this.getClass().toString()+": naarSpelBord()");
		bs_controller.showSpelBordView(instellingenPanelController);
	}

	/**
	 *
	 * @return Geeft de bijnaam van de desbetreffende speler.
	 */
	public String getBijnaam(){
		return this.bijnaam;
	}
}
