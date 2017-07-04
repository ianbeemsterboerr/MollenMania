package controller;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import model.Spelbord_Model;
/**
 * Server opstarten en bepalen als het een hervatten spel is of een nieuwe.
 */
public class Mol_Server extends UnicastRemoteObject{


	private static final long serialVersionUID = 1L;

	/**
	 * Maak een nieuwe een server aan
	 * @param max aantal spelers die in mogen
	 * @throws RemoteException
	 */
	public Mol_Server(int max) throws RemoteException{
		try {
			//object to work in
			Spelbord_Model bordmodel = new Spelbord_Model(max);
			// cast to remote object
			Bordspel_Interface userSkeleton = (Bordspel_Interface) UnicastRemoteObject.exportObject(bordmodel, 1099);

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

	/**
	 * Server laden met een hervatten spel model
	 * @param model model van spel
	 * @throws RemoteException
	 */
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
			System.out.println(userSkeleton.playerList().size());
			System.out.println(userSkeleton.observer_list());

			System.out.println("Server running...");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

