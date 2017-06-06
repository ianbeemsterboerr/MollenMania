package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.player_model;
import view.connect_host_view;
import view.lobby_view;

public class molclient {
	
	public molclient(){
		//this.player = molplayer;
	}
	
	public void ConnectServer(){
		try {
			System.out.println("Getting access to the registry");// get access to the RMI registry on the remote server	
			Registry registry = LocateRegistry.getRegistry("127.0.0.1"); // if server on another machine: provide that machine's IP address. Default port  1099				
			
			System.out.println("Getting the stub from registry");
            moluser_interface userStub = (moluser_interface) registry.lookup("moluser_interface"); // get remote Calculator object from registry
            
            //CALL OBJECT FROM INTERFACE IMPLEMENTATIONNN
           //COMING SOON
            moluser_controller pc = new moluser_controller(userStub);
            new lobby_view(pc, userStub);
            System.out.println("Users added to observer list");
            userStub.max_observers();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
