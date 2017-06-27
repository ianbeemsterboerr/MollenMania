package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Joshua
 *
 * De run-klasse zorgt ervoor dat het spel wordt opgestart.
 */
public class run extends Application {

		//MainMenuView main_stage = new MainMenuView();
		private MainMenuController mainMenuC;
		private static InstellingenPanelController instellingenController;

	/**
	 * Zorgt dat de controller van het main menu opgestart wordt.
	 * @param primaryStage De stage waarin gewerkt wordt.
	 * @throws Exception
	 */
		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			instellingenController = new InstellingenPanelController();
			mainMenuC = new MainMenuController(instellingenController);

			//MAGIC BABY
		    //primaryStage = main_stage.main_menu_show();
		    //primaryStage.show();
 		}

	/**
	 * Start de visuele weergave van het spel.
	 * @param args
	 */
	public static void main(String[] args) {
			// TODO Auto-generated method stub
			launch(args);
		}
}
