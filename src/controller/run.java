package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Joshua
 *
 * Edited by Robert: start niet langer de MainMenuView (main_menu) op.
 * Start enkel de MainMenuController en InstellingenController op.
 */
public class run extends Application {

		//MainMenuView main_stage = new MainMenuView();
		private MainMenuController mainMenuC;
		private static InstellingenPanelController instellingenController;

		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			instellingenController = new InstellingenPanelController();
			mainMenuC = new MainMenuController(instellingenController.getInstellingenView());

			//MAGIC BABY
		    //primaryStage = main_stage.main_menu_show();
		    //primaryStage.show();
 		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			launch(args);
		}
}
