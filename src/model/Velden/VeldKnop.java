package model.Velden;
import javafx.scene.control.Button;

public class VeldKnop extends Button {
	
	private int[] coordinaten;

	public VeldKnop(int x, int y, int z){ //, boolean isBezet, String kleur moeten teruggezet worden
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
	
}
