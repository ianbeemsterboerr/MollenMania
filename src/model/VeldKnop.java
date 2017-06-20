package model;

import javafx.scene.control.Button;

public class VeldKnop extends Button {
	
	private int[] coordinaten;
	
	public VeldKnop(int x, int y, int z){
		this.coordinaten = new int[]{x, y, z};
	}

	public int[] getCoordinaten() {
		return coordinaten;
	}

	public void setCoordinaten(int x, int y, int z) {
		this.coordinaten = new int[]{x, y, z};
	}
	
}
