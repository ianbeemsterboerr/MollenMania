package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class end_game_view {
	public void end_game_show(){
		Stage endStage = new Stage();
		GridPane grid = new GridPane();
		Button btn_test = new Button("Click me");
		
		grid.setId("grid_win");
		
		btn_test.setOnAction(e -> { 
			try{
				grid.setId("grid_lose");
			}catch(Exception b){
				b.printStackTrace();
		}});
		
		grid.add(btn_test, 0, 0);
		
		Scene end_scene = new Scene(grid, 415, 300);
        end_scene.getStylesheets().addAll(this.getClass().getResource("style/end_game_style.css").toExternalForm());

        endStage.setTitle("Hervat een spel");
		endStage.setScene(end_scene);
		endStage.show();
	}
}
