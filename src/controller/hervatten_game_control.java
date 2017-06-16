package controller;

import javafx.stage.FileChooser;
import model.Spel_Model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class hervatten_game_control {
	public hervatten_game_control(){

	}

	public String kiesFile() {
		return null;
	}
	public Spel_Model spelLaden() {
		Spel_Model spelModel;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("saveFile.dat"));
			spelModel = (Spel_Model) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("De file is niet gevonden.");
			return null;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("De geselecteerde file is niet van het goede type.");
			return null;
		}
		return spelModel;
	}
}
