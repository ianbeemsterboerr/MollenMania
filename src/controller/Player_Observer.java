package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Player_Observer extends Remote {
	/**
	 * @since 0.1
	 * @param playable
	 *            the model that is changed
	 * @throws RemoteException
	 *             when the connection between RMI client and server is
	 *             compromised
	 */
	public void modelChanged(Bordspel_Interface playable) throws RemoteException;

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
