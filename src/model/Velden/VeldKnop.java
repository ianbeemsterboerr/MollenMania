package model.Velden;
import javafx.scene.control.Button;

public class VeldKnop extends Button {
	
	private int[] coordinaten;
<<<<<<< HEAD
	private boolean isBezet;
	private boolean isSpeciaal;
	
	public VeldKnop(int x, int y, int z){
=======

	public VeldKnop(int x, int y, int z, boolean isBezet, String kleur){
>>>>>>> 2936c729f97a55815ae28f33e2fd31e0b45ba567
		this.coordinaten = new int[]{x, y, z};
	}


	public int[] getCoordinaten() {
		return coordinaten;
	}
	
	public int getX() {
		return coordinaten[0];
	}

	public int getY() {
		return coordinaten[1];
	}

	public int getZ() {
		return coordinaten[2];
	}

	public void setCoordinaten(int x, int y, int z) {
		this.coordinaten = new int[]{x, y, z};
	}

	public boolean isBezet() {
		return isBezet;
	}

	public void setBezet(boolean isBezet) {
		this.isBezet = isBezet;
	}

	public boolean isSpeciaal() {
		return isSpeciaal;
	}

	public void setSpeciaal(boolean isSpeciaal) {
		this.isSpeciaal = isSpeciaal;
	}
	
}
