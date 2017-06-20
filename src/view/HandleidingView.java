package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.*;
/**
 * Created by Robert on 6/20/2017.
 */
public class HandleidingView {
    private Image pag1 = new Image("");

    public HandleidingView(){
        double button_width = 150.0;
        Stage handleidingStage = new Stage();
        HBox hbox_handleiding = new HBox();

        Button btn_volgende = new Button("Volgende >>");
        Button btn_vorige = new Button("<< Vorige");
        btn_volgende.setMaxWidth(button_width);
        btn_vorige.setMaxWidth(button_width);

        hbox_handleiding.getChildren().addAll(btn_vorige,btn_volgende);
        hbox_handleiding.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hbox_handleiding);

        Scene handleiding_scene = new Scene(borderPane, 250, 85);
        handleidingStage.setScene(handleiding_scene);
        handleidingStage.setTitle("Mollenmania - handleiding");
        handleidingStage.show();
    }
}
