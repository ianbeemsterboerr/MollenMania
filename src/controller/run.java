package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Playboard_Model;
import view.main_menu;

public class run extends Application {
		
		main_menu main_stage = new main_menu();
		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub

			//MAGIC BABY
		    primaryStage = main_stage.main_menu_show();
		    primaryStage.show();
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			launch(args);

			Playboard_Model playboardModel = new Playboard_Model();

		}
}
