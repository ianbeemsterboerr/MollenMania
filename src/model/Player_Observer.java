package model;
import java.rmi.Remote;
import java.rmi.RemoteException;

import controller.Bordspel_Interface;
import controller.Bordspel_Interface;

/**
 * De observer interface uit het observer pattern. Alles wat een observer is, dus het Spelbord view en de LobbyView, dienen deze te implementeren. Zorgt ervoor dat het model kan worden geupdate, en aan de hand hiervan de relevante views worden geupdate.
 */
public interface Player_Observer extends Remote {
	/**
	 *
	 * @param
	 *            playable Het model dat meegegeven wordt, zodat de individuele clients de informatie er zelf uit kunnen halen.
	 * @throws RemoteException Wanneer de connectie tussen de client en de server verbroken is.
	 */
	public void modelChanged(Bordspel_Interface playable) throws RemoteException;

	/**
	 *
	 * @return Of het object waarin deze functie wordt gecalled is in staat is om veranderingen aan het model te maken.
	 * @throws RemoteException Wanneer de connectie tussen de client en de server verbroken is.
	 */
	public boolean isEnabled() throws RemoteException;
	

	/**
	 *
	 * 
	 * @param enabled Wanneer deze parameter true is, zal het object waarvan deze wordt aangepast in staat zijn veranderingen aan het model toe te passen.
	 * @throws RemoteException Wanneer de connectie tussen de client en de server verbroken is.
	 */
	public void setEnabled(boolean enabled) throws RemoteException;

}
