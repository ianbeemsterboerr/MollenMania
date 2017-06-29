package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * De observer interface uit het observer pattern. Alles wat een observer is, dus het Spelbord view en de LobbyView, dienen deze te implementeren. Zorgt ervoor dat het model kan worden geupdate, en aan de hand hiervan de relevante views worden geupdate.
 */
public interface Player_Observer extends Remote {
	/**
	 * @since 0.1
	 * @param t
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

	/**Geeft aan bijw elke speler hij hoort.
	 *
	 * @return String bijnaam
	 * auteur: Robert den Blaauwen
	 * Versie: 29-6-2017
	 */
	public String getBijnaam() throws RemoteException;

}
