package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Player_Model;
import view.lobby_view;

public class molclient {
	
	public molclient(String username, String maxSpelers){
		try {
			System.out.println("Getting access to the registry");// get access to the RMI registry on the remote server	
			Registry registry = LocateRegistry.getRegistry("127.0.0.1"); // if server on another machine: provide that machine's IP address. Default port  1099				
			
			System.out.println("Getting the stub from registry");
            
            //CALL OBJECT FROM INTERFACE IMPLEMENTATIONNN
            //COMING SOON
			moluser_interface userStub = (moluser_interface) registry.lookup("moluser_interface");// get remote interface object from registry
            Player_Model player = new Player_Model();
            moluser_controller pc = new moluser_controller(userStub, player);
            player.setPlayer_id(userStub.observerSize()+1);
            player.setPlayer_name(username);
            new lobby_view(pc, userStub, player);
            
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
