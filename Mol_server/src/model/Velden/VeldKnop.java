package model.Velden;
import javafx.scene.control.Button;

/**
 * Het spelbord bestaat uit 61 VeldKnop objecten. De VeldKnop is een plek waar een Mol kan staan. Er zijn verschillende soorten velden.
 */
public class VeldKnop extends Button {
	private int[] coordinaten;
	private boolean isBezet;
	private boolean isSpeciaal;

	/**
	 * Maakt een VeldKnop aan aan de hand van een x, y en z coordinaat.
	 * @param x Coordinaat
	 * @param y Coordinaat
	 * @param z Coordinaat
	 */
	public VeldKnop(int x, int y, int z){//, boolean isBezet, String kleur moeten teruggezet worden
		this.coordinaten = new int[]{x, y, z};
	}

	/**
	 * Geeft de coordinaten van de VeldKnop.
	 * @return [x, y, z]int
	 */
	public int[] getCoordinaten() {
		return coordinaten;
	}

	/**
	 * geeft het x-coordinaat.
	 * @return
	 */
	public int getX() {
		return coordinaten[0];
	}

	/**
	 * Geeft het y coordinaat.
	 * @return
	 */
	public int getY() {
		return coordinaten[1];
	}

	/**
	 * Geeft het z coordinaat.
	 * @return
	 */
	public int getZ() {
		return coordinaten[2];
	}

	/**
	 * Set de coordinaten van een VeldKnop.
	 * @param x Coordinaat
	 * @param y Coordinaat
	 * @param z Coordinaat
	 */
	public void setCoordinaten(int x, int y, int z) {
		this.coordinaten = new int[]{x, y, z};
	}

	/**
	 * Geeft aan of de VeldKnop bezet is.
	 * @return true of false.
	 */
	public boolean isBezet() {
		return isBezet;
	}

	/**
	 * Set isBezet naar true of false.
	 * @param isBezet de boolean waarde die gezet zal worden.
	 */
	public void setBezet(boolean isBezet) {
		this.isBezet = isBezet;
	}

	/**
	 * Geeft aan of het een SpeciaalVeld is of niet.
	 * @return true of false.
	 */
	public boolean isSpeciaal() {
		return isSpeciaal;
	}

	/**
	 * Zet de waarde van de isSpeciaal boolean.
	 * @param isSpeciaal
	 */
	public void setSpeciaal(boolean isSpeciaal) {
		this.isSpeciaal = isSpeciaal;
	}

}
