package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import model.Player_Model;
import view.connect_host_view;

public class molserver {
	
	public molserver(){
		try {
			Player_Model moluser = new Player_Model();
			moluser_interface userSkeleton = (moluser_interface) UnicastRemoteObject.exportObject(moluser, 0); // cast to remote object
			System.out.println("User skeleton created");
			
			Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
			System.out.println("RMI Registry starter");
			
			registry.rebind("moluser_interface", userSkeleton); // bind userinterface to RMI registry
	        System.out.println("User skeleton bound");
	        System.out.println("Server running...");
			// if you'd like to run rmiregistry from the command line
			//	run it from the project's bin directory, so rmiregistry can find the necessary classes
		} catch (RemoteException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
	   }
	}
}

