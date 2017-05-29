package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class hervat_game_view {
	public void hervat_game_show(){
		double button_width = 100.0;									
		Stage connectStage = new Stage();
		VBox vbox_hervat_options = new VBox();
		GridPane grid = new GridPane();
		TableView game_table = new TableView();
		
		Button btn_hervat = new Button("HERVAT SPEL");
		Button btn_stats = new Button("SPEL STATS");
		Button btn_back = new Button("BACK");
		
		game_table.setEditable(false);
		TableColumn game_id_col = new TableColumn("GAME ID");
		TableColumn game_date_col = new TableColumn("GAME DATE");
		game_id_col.setMinWidth(150.0);
		game_date_col.setMinWidth(150.0);
        game_table.getColumns().addAll(game_id_col, game_date_col);
        
		btn_hervat.setMaxWidth(button_width);
		btn_stats.setMaxWidth(button_width);
		btn_back.setMaxWidth(button_width);
		
		vbox_hervat_options.setSpacing(5.0);
		vbox_hervat_options.getChildren().addAll(btn_hervat, btn_stats, btn_back);
		
		btn_back.setOnAction(e -> { 
			try{
				connectStage.close(); 
			}catch(Exception b){
				b.printStackTrace();
		}});
		
		grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(game_table, 0, 0);
	    grid.add(vbox_hervat_options, 1, 0);
		
		Scene connect_scene = new Scene(grid, 415, 300);
		
		connectStage.setTitle("Hervat een spel");
		connectStage.setScene(connect_scene);
		connectStage.show();
	}
}
