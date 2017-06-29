package controller;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import model.Spelbord_Model;

public class Mol_Server extends UnicastRemoteObject{
	
	/**
	 * Bevat alle functies voor het hosten van een server.
	 */
	private static final long serialVersionUID = 1L;

	public Mol_Server(int max) throws RemoteException{
		try {
			//object to work in
			Spelbord_Model bordmodel = new Spelbord_Model(max);
			// cast to remote object
			Bordspel_Interface userSkeleton = (Bordspel_Interface) UnicastRemoteObject.exportObject(bordmodel, 0);

			// default port 1099 // run RMI registry on local host
			Registry registry = LocateRegistry.createRegistry(1099);

			// if you'd like to run rmiregistry from the command line
			// run it from the project's bin directory, so rmiregistry can find the necessary classes
			// bind userinterface to RMI registry
			registry.rebind("Spelbord_Model", userSkeleton);


			System.out.println("Server running...");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }

	}
	public Mol_Server(Spelbord_Model model) throws RemoteException{
		try {
			//object to work in
			Spelbord_Model bordmodel = model;
			// cast to remote object
			Bordspel_Interface userSkeleton = (Bordspel_Interface) UnicastRemoteObject.exportObject(bordmodel, 0);

			// default port 1099 // run RMI registry on local host
			Registry registry = LocateRegistry.createRegistry(1099);

			// if you'd like to run rmiregistry from the command line
			// run it from the project's bin directory, so rmiregistry can find the necessary classes
			// bind userinterface to RMI registry
			registry.rebind("Spelbord_Model", userSkeleton);


			System.out.println("Server running...");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

