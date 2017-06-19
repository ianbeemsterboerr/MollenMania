package JavaFXImageShit;

import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class spelbord_view {
	int[] intArray1;
	int[] intArray2;
	
	
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


	public Stage spelbord_show() {
		Stage primaryStage = new Stage();
		GridPane root = new GridPane();

		VBox spelerData1 = new VBox();
		VBox spelerData2 = new VBox();
		VBox spelerData3 = new VBox();
		VBox spelerData4 = new VBox();
		
		int numRows = 12;
		int numCols = 41;
		int ficheRowStart = 12;
		int ficheRowEnd = 14;
		spelerData1.getChildren().addAll(new Label("Fiches"), new Label("Speler"));
		spelerData2.getChildren().addAll(new Label("Fiches"), new Label("Speler"));
		spelerData3.getChildren().addAll(new Label("Fiches"), new Label("Speler"));
		spelerData4.getChildren().addAll(new Label("Fiches"), new Label("Speler"));
	
		Button btn_close = new Button("exit");
		Button btn_minimize = new Button("-");
		Button[] buttonArray = new Button[61];
		Label[] ficheLabelArray = new Label[24];

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
			Button veld = new Button("" + ((column + 1) / 2 - 9));
			root.add(veld, column, 1);
			buttonArray[(column + 1) / 2 - 10] = veld;
			
		}
		// loop voor buttons 6 t/m 11
		for (int column = 18; column < 30; column = column + 2) {
			Button veld = new Button("" + (column / 2 - 3));
			root.add(veld, column, 2);
			buttonArray[column / 2 - 4] = veld;
		}
		// loop voor buttons 12 t/m 18
		for (int column = 17; column < 31; column = column + 2) {
			Button veld = new Button("" + ((column + 1) / 2 + 3));
			root.add(veld, column, 3);
			buttonArray[(column + 1) / 2 + 2] = veld;
		}
		// loop voor buttons 19 t/m 26
		for (int column = 16; column < 31; column = column + 2) {
			Button veld = new Button("" + (column / 2 + 11));
			root.add(veld, column, 4);
			buttonArray[column / 2 + 10] = veld;
		}
		// loop voor buttons 27 t/m 35
		for (int column = 15; column < 32; column = column + 2) {
			Button veld = new Button("" + ((column + 1) / 2 + 19));
			root.add(veld, column, 5);
			buttonArray[(column + 1) / 2 + 18] = veld;
		}
		// loop voor buttons 36 t/m 43
		for (int column = 16; column < 31; column = column + 2) {
			Button veld = new Button("" + ((column / 2) + 28));
			root.add(veld, column, 6);
			buttonArray[column / 2 + 27] = veld;
		}
		// buttons 44 t/m 50
		for (int column = 17; column < 31; column = column + 2) {
			Button veld = new Button("" + ((column + 1) / 2 + 35));
			root.add(veld, column, 7);
			buttonArray[(column + 1) / 2 + 34] = veld;
		}
		// buttons 51 t/m 56
		for (int column = 18; column < 30; column = column + 2) {
			Button veld = new Button("" + (column / 2 + 42));
			root.add(veld, column, 8);
			buttonArray[column / 2 + 41] = veld;
		}
		// buttons 57 t/m 61
		for (int column = 19; column < 29; column = column + 2) {
			Button veld = new Button("" + ((column + 1) / 2 + 47));
			root.add(veld, column, 9);
			buttonArray[(column + 1) / 2 + 46] = veld;
		}
		
		int column;
		for (int section = 0; section < 21; section = section + 20) {
			for (int row = (2 + section / 2); row < (5 + section / 2); row++) {
				for (column = (ficheRowStart + section); column < (ficheRowEnd + section); column++)
				{
					Label ficheVeld1 = new Label();
					root.add(ficheVeld1, column, row);
				}
				ficheRowStart = (ficheRowStart + section) - 3;
				ficheRowEnd = (ficheRowEnd + section) - 3;
				column = ficheRowStart;
			}
		}
		root.add(btn_close, 41, 0);
		root.add(btn_minimize, 40, 0);
		
		buttonArray[60].setOnAction(e -> {System.out.println("1");});
		buttonArray[1].setOnAction(e -> {System.out.println("2");});
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
		Scene scene = new Scene(root, 2000, 2000);
		root.setId("root");

		scene.getStylesheets().addAll(this.getClass().getResource("style/SpelbordStyle.css").toExternalForm());
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
	// TODO Auto-generated method stub
}
