package view;

import controller.Bordspel_Controller;
import controller.Bordspel_Interface;
import controller.Player_Observer;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Speler_Model;
import model.Velden.VeldKnop;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class spelbord_view extends UnicastRemoteObject implements Player_Observer {
	protected spelbord_view() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	int[] intArray1;
	int[] intArray2;
	GridPane player_1;
	GridPane player_2;
	GridPane player_3;
	GridPane player_4;
	private static final long serialVersionUID = 1L;
	private ArrayList<Speler_Model> players;
	
	public Stage spelbord_show(Bordspel_Controller bs_controller, Bordspel_Interface bs_interface) throws RemoteException {

		try {
			bs_interface.addObserver(this);
		}catch(Exception e){
			e.printStackTrace();
		}

		players = bs_interface.playerList();
		Stage primaryStage = new Stage();
		GridPane root = new GridPane();

		VBox left = new VBox();
		left.setPadding(new Insets(20, 20, 20, 20));
		left.setSpacing(300.0);
		VBox right = new VBox();
		right.setPadding(new Insets(20, 20, 20, 20));
		right.setSpacing(300.0);

		if(players.size() == 2){
			this.player_1 = this.createUserPanel(players.get(0));
			this.player_2 = this.createUserPanel(players.get(1));
			left.getChildren().addAll(player_1);
			right.getChildren().addAll(player_2);

			this.player_1.setAlignment(Pos.TOP_LEFT);
			this.player_2.setAlignment(Pos.TOP_RIGHT);
		} else if(players.size() == 3){
			this.player_1 = this.createUserPanel(players.get(0));
			this.player_2 = this.createUserPanel(players.get(1));
			this.player_3 = this.createUserPanel(players.get(2));

			left.getChildren().addAll(player_1, player_3);
			right.getChildren().addAll(player_2);

			this.player_1.setAlignment(Pos.TOP_LEFT);
			this.player_2.setAlignment(Pos.TOP_RIGHT);
			this.player_3.setAlignment(Pos.BOTTOM_LEFT);
		} else if(players.size() == 4){
			this.player_1 = this.createUserPanel(players.get(0));
			this.player_2 = this.createUserPanel(players.get(1));
			this.player_3 = this.createUserPanel(players.get(2));
			this.player_4 = this.createUserPanel(players.get(3));

			left.getChildren().addAll(player_1, player_3);
			right.getChildren().addAll(player_2, player_4);

			this.player_1.setAlignment(Pos.TOP_LEFT);
			this.player_2.setAlignment(Pos.TOP_RIGHT);
			this.player_3.setAlignment(Pos.BOTTOM_LEFT);
			this.player_4.setAlignment(Pos.BOTTOM_RIGHT);
		} else {
			System.out.println("nope");
		}

//		GridPane player_1 = this.createUserPanel(players.get(0));
//		GridPane player_2 = this.createUserPanel(players.get(1));
//		GridPane player_3 = this.createUserPanel(players.get(2));
//		GridPane player_4 = this.createUserPanel(players.get(3));
//
//		this.player_1.setAlignment(Pos.TOP_LEFT);
//		this.player_2.setAlignment(Pos.TOP_RIGHT);
//		this.player_3.setAlignment(Pos.BOTTOM_LEFT);
//		this.player_4.setAlignment(Pos.BOTTOM_RIGHT);


		BorderPane moap = new BorderPane();
		moap.setLeft(left);
		moap.setRight(right);


		int numRows = 12;
		int numCols = 41;
		int ficheRowStart = 12;
		int ficheRowEnd = 14;
		Label speler1 = new Label();
		Label speler2 = new Label();
		Label speler3 = new Label();
		Label speler4 = new Label();
		Button fiches1 = new Button();
		Button fiches2 = new Button();
		Button fiches3 = new Button();
		Button fiches4 = new Button();
		Button molSpeler1 = new Button();
		Button molSpeler2 = new Button();
		Button molSpeler3 = new Button();
		Button molSpeler4 = new Button();
		Label naamSpeler1 = new Label(); 
		Label naamSpeler2 = new Label(); 
		Label naamSpeler3 = new Label(); 
		Label naamSpeler4 = new Label(); 

		Button btn_close = new Button("exit");
		Button btn_minimize = new Button("-");
		VeldKnop[] buttonArray = new VeldKnop[61];

		for (int i = 0; i < numRows; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setPercentHeight(100.0 / numRows);
			rc.setValignment(VPos.BOTTOM);
			root.getRowConstraints().add(rc);
		}
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHalignment(HPos.CENTER);
			cc.setPercentWidth(100 / numCols);
			root.getColumnConstraints().add(cc);
		}

		// loop voor buttons 1 t/m 5
		for (int column = 19; column < 29; column = column + 2) {
			VeldKnop veld = new VeldKnop((14 - ((column + 1) / 2)) , ((column + 1) / 2 - 10), -4);
			root.add(veld, column, 1);
			buttonArray[(column + 1) / 2 - 10] = veld;
			
		}
		// loop voor buttons 6 t/m 11
		for (int column = 18; column < 30; column = column + 2) {
			VeldKnop veld = new VeldKnop((13 - (column / 2)) , (column / 2 - 10), -3);
			root.add(veld, column, 2);
			buttonArray[column / 2 - 4] = veld;
		}
		// loop voor buttons 12 t/m 18
		for (int column = 17; column < 31; column = column + 2) {
			VeldKnop veld = new VeldKnop((13 - ((column + 1) / 2)) , ((column + 1) / 2 - 11), -2);
			root.add(veld, column, 3);
			buttonArray[(column + 1) / 2 + 2] = veld;
		}
		// loop voor buttons 19 t/m 26
		for (int column = 16; column < 31; column = column + 2) {
			VeldKnop veld = new VeldKnop((12 - (column / 2)) , (column / 2 - 11), -1);
			root.add(veld, column, 4);
			buttonArray[column / 2 + 10] = veld;
		}
		// loop voor buttons 27 t/m 35
		for (int column = 15; column < 32; column = column + 2) {
			VeldKnop veld = new VeldKnop((12 - ((column + 1) / 2)) , ((column + 1) / 2 - 12), 0);
			root.add(veld, column, 5);
			buttonArray[(column + 1) / 2 + 18] = veld;
		}
		// loop voor buttons 36 t/m 43
		for (int column = 16; column < 31; column = column + 2) {
			VeldKnop veld = new VeldKnop((11 - (column / 2)) , (column / 2 - 12), 1);
			root.add(veld, column, 6);
			buttonArray[column / 2 + 27] = veld;
		}
		// buttons 44 t/m 50
		for (int column = 17; column < 31; column = column + 2) {
			VeldKnop veld = new VeldKnop((11 - ((column + 1) / 2)) , ((column + 1) / 2 - 13), 2);
			root.add(veld, column, 7);
			buttonArray[(column + 1) / 2 + 34] = veld;
		}
		// buttons 51 t/m 56
		for (int column = 18; column < 30; column = column + 2) {
			VeldKnop veld = new VeldKnop(10 - (column / 2) , (column / 2 - 13), 3);
			root.add(veld, column, 8);
			buttonArray[column / 2 + 41] = veld;
		}
		// buttons 57 t/m 61
		for (int column = 19; column < 29; column = column + 2) {
			VeldKnop veld = new VeldKnop((10 - (column + 1) / 2) , ((column + 1) / 2 - 14), 4);
			root.add(veld, column, 9);
			buttonArray[(column + 1) / 2 + 46] = veld;
		}
		
		root.add(btn_close, 41, 0);
		root.add(btn_minimize, 40, 0);
		
		buttonArray[0 ].setOnAction(e -> {System.out.println(buttonArray[0 ].getX()+" "+buttonArray[0 ].getY()+" "+buttonArray[0 ].getZ());});
		buttonArray[1 ].setOnAction(e -> {System.out.println(buttonArray[1 ].getX()+" "+buttonArray[1 ].getY()+" "+buttonArray[1 ].getZ());});
		buttonArray[2 ].setOnAction(e -> {System.out.println(buttonArray[2 ].getX()+" "+buttonArray[2 ].getY()+" "+buttonArray[2 ].getZ());});
		buttonArray[3 ].setOnAction(e -> {System.out.println(buttonArray[3 ].getX()+" "+buttonArray[3 ].getY()+" "+buttonArray[3 ].getZ());});
		buttonArray[4 ].setOnAction(e -> {System.out.println(buttonArray[4 ].getX()+" "+buttonArray[4 ].getY()+" "+buttonArray[4 ].getZ());});
		buttonArray[5 ].setOnAction(e -> {System.out.println(buttonArray[5 ].getX()+" "+buttonArray[5 ].getY()+" "+buttonArray[5 ].getZ());});
		buttonArray[6 ].setOnAction(e -> {System.out.println(buttonArray[6 ].getX()+" "+buttonArray[6 ].getY()+" "+buttonArray[6 ].getZ());});
		buttonArray[7 ].setOnAction(e -> {System.out.println(buttonArray[7 ].getX()+" "+buttonArray[7 ].getY()+" "+buttonArray[7 ].getZ());});
		buttonArray[8 ].setOnAction(e -> {System.out.println(buttonArray[8 ].getX()+" "+buttonArray[8 ].getY()+" "+buttonArray[8 ].getZ());});
		buttonArray[9 ].setOnAction(e -> {System.out.println(buttonArray[9 ].getX()+" "+buttonArray[9 ].getY()+" "+buttonArray[9 ].getZ());});
		buttonArray[10].setOnAction(e -> {System.out.println(buttonArray[10].getX()+" "+buttonArray[10].getY()+" "+buttonArray[10].getZ());});
		buttonArray[11].setOnAction(e -> {System.out.println(buttonArray[11].getX()+" "+buttonArray[11].getY()+" "+buttonArray[11].getZ());});
		buttonArray[12].setOnAction(e -> {System.out.println(buttonArray[12].getX()+" "+buttonArray[12].getY()+" "+buttonArray[12].getZ());});
		buttonArray[13].setOnAction(e -> {System.out.println(buttonArray[13].getX()+" "+buttonArray[13].getY()+" "+buttonArray[13].getZ());});
		buttonArray[14].setOnAction(e -> {System.out.println(buttonArray[14].getX()+" "+buttonArray[14].getY()+" "+buttonArray[14].getZ());});
		buttonArray[15].setOnAction(e -> {System.out.println(buttonArray[15].getX()+" "+buttonArray[15].getY()+" "+buttonArray[15].getZ());});
		buttonArray[16].setOnAction(e -> {System.out.println(buttonArray[16].getX()+" "+buttonArray[16].getY()+" "+buttonArray[16].getZ());});
		buttonArray[17].setOnAction(e -> {System.out.println(buttonArray[17].getX()+" "+buttonArray[17].getY()+" "+buttonArray[17].getZ());});
		buttonArray[18].setOnAction(e -> {System.out.println(buttonArray[18].getX()+" "+buttonArray[18].getY()+" "+buttonArray[18].getZ());});
		buttonArray[19].setOnAction(e -> {System.out.println(buttonArray[19].getX()+" "+buttonArray[19].getY()+" "+buttonArray[19].getZ());});
		buttonArray[20].setOnAction(e -> {System.out.println(buttonArray[20].getX()+" "+buttonArray[20].getY()+" "+buttonArray[20].getZ());});
		buttonArray[21].setOnAction(e -> {System.out.println(buttonArray[21].getX()+" "+buttonArray[21].getY()+" "+buttonArray[21].getZ());});
		buttonArray[22].setOnAction(e -> {System.out.println(buttonArray[22].getX()+" "+buttonArray[22].getY()+" "+buttonArray[22].getZ());});
		buttonArray[23].setOnAction(e -> {System.out.println(buttonArray[23].getX()+" "+buttonArray[23].getY()+" "+buttonArray[23].getZ());});
		buttonArray[24].setOnAction(e -> {System.out.println(buttonArray[24].getX()+" "+buttonArray[24].getY()+" "+buttonArray[24].getZ());});
		buttonArray[25].setOnAction(e -> {System.out.println(buttonArray[25].getX()+" "+buttonArray[25].getY()+" "+buttonArray[25].getZ());});
		buttonArray[26].setOnAction(e -> {System.out.println(buttonArray[26].getX()+" "+buttonArray[26].getY()+" "+buttonArray[26].getZ());});
		buttonArray[27].setOnAction(e -> {System.out.println(buttonArray[27].getX()+" "+buttonArray[27].getY()+" "+buttonArray[27].getZ());});
		buttonArray[28].setOnAction(e -> {System.out.println(buttonArray[28].getX()+" "+buttonArray[28].getY()+" "+buttonArray[28].getZ());});
		buttonArray[29].setOnAction(e -> {System.out.println(buttonArray[29].getX()+" "+buttonArray[29].getY()+" "+buttonArray[29].getZ());});
		buttonArray[30].setOnAction(e -> {System.out.println(buttonArray[30].getX()+" "+buttonArray[30].getY()+" "+buttonArray[30].getZ());});
		buttonArray[31].setOnAction(e -> {System.out.println(buttonArray[31].getX()+" "+buttonArray[31].getY()+" "+buttonArray[31].getZ());});
		buttonArray[32].setOnAction(e -> {System.out.println(buttonArray[32].getX()+" "+buttonArray[32].getY()+" "+buttonArray[32].getZ());});
		buttonArray[33].setOnAction(e -> {System.out.println(buttonArray[33].getX()+" "+buttonArray[33].getY()+" "+buttonArray[33].getZ());});
		buttonArray[34].setOnAction(e -> {System.out.println(buttonArray[34].getX()+" "+buttonArray[34].getY()+" "+buttonArray[34].getZ());});
		buttonArray[35].setOnAction(e -> {System.out.println(buttonArray[35].getX()+" "+buttonArray[35].getY()+" "+buttonArray[35].getZ());});
		buttonArray[36].setOnAction(e -> {System.out.println(buttonArray[36].getX()+" "+buttonArray[36].getY()+" "+buttonArray[36].getZ());});
		buttonArray[37].setOnAction(e -> {System.out.println(buttonArray[37].getX()+" "+buttonArray[37].getY()+" "+buttonArray[37].getZ());});
		buttonArray[38].setOnAction(e -> {System.out.println(buttonArray[38].getX()+" "+buttonArray[38].getY()+" "+buttonArray[38].getZ());});
		buttonArray[39].setOnAction(e -> {System.out.println(buttonArray[39].getX()+" "+buttonArray[39].getY()+" "+buttonArray[39].getZ());});
		buttonArray[40].setOnAction(e -> {System.out.println(buttonArray[40].getX()+" "+buttonArray[40].getY()+" "+buttonArray[40].getZ());});
		buttonArray[41].setOnAction(e -> {System.out.println(buttonArray[41].getX()+" "+buttonArray[41].getY()+" "+buttonArray[41].getZ());});
		buttonArray[42].setOnAction(e -> {System.out.println(buttonArray[42].getX()+" "+buttonArray[42].getY()+" "+buttonArray[42].getZ());});
		buttonArray[43].setOnAction(e -> {System.out.println(buttonArray[43].getX()+" "+buttonArray[43].getY()+" "+buttonArray[43].getZ());});
		buttonArray[44].setOnAction(e -> {System.out.println(buttonArray[44].getX()+" "+buttonArray[44].getY()+" "+buttonArray[44].getZ());});
		buttonArray[45].setOnAction(e -> {System.out.println(buttonArray[45].getX()+" "+buttonArray[45].getY()+" "+buttonArray[45].getZ());});
		buttonArray[46].setOnAction(e -> {System.out.println(buttonArray[46].getX()+" "+buttonArray[46].getY()+" "+buttonArray[46].getZ());});
		buttonArray[47].setOnAction(e -> {System.out.println(buttonArray[47].getX()+" "+buttonArray[47].getY()+" "+buttonArray[47].getZ());});
		buttonArray[48].setOnAction(e -> {System.out.println(buttonArray[48].getX()+" "+buttonArray[48].getY()+" "+buttonArray[48].getZ());});
		buttonArray[49].setOnAction(e -> {System.out.println(buttonArray[49].getX()+" "+buttonArray[49].getY()+" "+buttonArray[49].getZ());});
		buttonArray[50].setOnAction(e -> {System.out.println(buttonArray[50].getX()+" "+buttonArray[50].getY()+" "+buttonArray[50].getZ());});
		buttonArray[51].setOnAction(e -> {System.out.println(buttonArray[51].getX()+" "+buttonArray[51].getY()+" "+buttonArray[51].getZ());});
		buttonArray[52].setOnAction(e -> {System.out.println(buttonArray[52].getX()+" "+buttonArray[52].getY()+" "+buttonArray[52].getZ());});
		buttonArray[53].setOnAction(e -> {System.out.println(buttonArray[53].getX()+" "+buttonArray[53].getY()+" "+buttonArray[53].getZ());});
		buttonArray[54].setOnAction(e -> {System.out.println(buttonArray[54].getX()+" "+buttonArray[54].getY()+" "+buttonArray[54].getZ());});
		buttonArray[55].setOnAction(e -> {System.out.println(buttonArray[55].getX()+" "+buttonArray[55].getY()+" "+buttonArray[55].getZ());});
		buttonArray[56].setOnAction(e -> {System.out.println(buttonArray[56].getX()+" "+buttonArray[56].getY()+" "+buttonArray[56].getZ());});
		buttonArray[57].setOnAction(e -> {System.out.println(buttonArray[57].getX()+" "+buttonArray[57].getY()+" "+buttonArray[57].getZ());});
		buttonArray[58].setOnAction(e -> {System.out.println(buttonArray[58].getX()+" "+buttonArray[58].getY()+" "+buttonArray[58].getZ());});
		buttonArray[59].setOnAction(e -> {System.out.println(buttonArray[59].getX()+" "+buttonArray[59].getY()+" "+buttonArray[59].getZ());});
		buttonArray[60].setOnAction(e -> {System.out.println(buttonArray[60].getX()+" "+buttonArray[60].getY()+" "+buttonArray[60].getZ());});

		btn_close.setOnAction(e -> {
			try {
				System.exit(0);
			} catch (Exception b) {
				b.printStackTrace();
			}
		});

		btn_minimize.setOnAction(e -> {
			try {
				primaryStage.setIconified(true);
			} catch (Exception b) {
				b.printStackTrace();
			}
		});

		// SET ID's for CSS
		Scene scene = new Scene(root, 1000, 600);
		root.setId("root");

		scene.getStylesheets().addAll(this.getClass().getResource("SpelbordStyle.css").toExternalForm());
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		/**for (int i = 0; i < buttonArray.length; i++) {
			System.out.print(buttonArray[i]);
		}**/
		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("MollenMania - Spelbord");
		primaryStage.setScene(scene);

		return primaryStage;
	}

	public GridPane createUserPanel(Speler_Model sm){
		/*
		 * maybe each panel should be owned by a user?! watcha think dog.
		 */

		String speler_naam = sm.getUsername();
		String mol_count = Integer.toString(sm.getMol_list().size());
		// int fiche_count = sm.getFicheList().size();

		GridPane grid = new GridPane();

		Label username_lbl = new Label(speler_naam);
		Label aantal_fiche_lbl = new Label("5");
		Label aantal_mol_lbl = new Label(mol_count);

		Button fiche_btn = new Button("Fiche");
		Button mol_btn = new Button("Mol");
		Button klaar_btn = new Button("Klaar");

		grid.add(username_lbl, 0, 0);
		grid.add(mol_btn, 0, 1);
		grid.add(aantal_mol_lbl, 1, 1);
		grid.add(fiche_btn, 0, 2);
		grid.add(aantal_fiche_lbl, 1, 2);
		grid.add(klaar_btn, 0, 3);
		grid.setHgap(10.0);
		grid.setVgap(10.0);

		return grid;
	}

	// TODO Auto-generated method stub

	@Override
	public void modelChanged(Bordspel_Interface playable) throws RemoteException {

	}

	@Override
	public boolean isEnabled() throws RemoteException {
		return false;
	}

	@Override
	public void setEnabled(boolean enabled) throws RemoteException {

	}

	public int[] getIntArray1() {
		return intArray1;
	}


	public void setIntArray1(int[] intArray1) {
		this.intArray1 = intArray1;
	}


	public int[] getIntArray2() {
		return intArray2;
	}


	public void setIntArray2(int[] intArray2) {
		this.intArray2 = intArray2;
	}
}
