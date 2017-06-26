package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Robert on 6/23/2017.
 *
 * Deze class is simpelweg bedoeld om de code voor de WInview gescheiden te houden van SpelBordVoew ivm merge errors,
 * Hij hoeft niet per se in een aparte klasse te blijven, I think.
 *
 */
public class WinView {
    private Label message = new Label("A WINNER IS YOU");
    private InstInGameView instInGameView;
    public WinView(InstInGameView instInGameView){
        this.instInGameView=instInGameView;
        message.setId("text_win");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(instInGameView.getView());
        borderPane.setCenter(message);
        borderPane.setId("panel_win");
        Scene winScene = new Scene(borderPane, 1440, 900);
        winScene.setFill(Color.TRANSPARENT);
        winScene.getStylesheets().addAll(this.getClass().getResource("style/SpelbordStyle.css").toExternalForm());
        Stage winStage = new Stage();
        instInGameView.registerStage(winStage);
        winStage.initStyle(StageStyle.TRANSPARENT);
        winStage.setScene(winScene);
        winStage.show();
    }
}
