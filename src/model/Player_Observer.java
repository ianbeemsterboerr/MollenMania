package model;
import java.rmi.Remote;
import java.rmi.RemoteException;

import controller.moluser_interface;


public interface Player_Observer extends Remote {
	/**
	 * @since 0.1
	 * @param t
	 *            the model that is changed
	 * @throws RemoteException
	 *             when the connection between RMI client and server is
	 *             compromised
	 */
	public void modelChanged(moluser_interface playable) throws RemoteException;

	/**
	 * @since 0.2
	 * @return whether this object is enabled or disabled to send a message to the CounterController
	 * @throws RemoteException
	 *             when the connection between RMI client and server is
	 *             compromised
	 */
	public boolean isEnabled() throws RemoteException;
	

	/**
	 * since 0.2
	 * 
	 * @param enabled enabling or disabling this object to send a message to the CounterController
	 * @throws RemoteException
	 *             when the connection between RMI client and server is
	 *             compromised
	 */
	public void setEnabled(boolean enabled) throws RemoteException;

}
