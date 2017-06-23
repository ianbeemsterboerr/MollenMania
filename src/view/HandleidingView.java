package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.awt.*;

/**
 * Created by Robert on 6/20/2017.
 */
public class HandleidingView {
    private Image[] paginas = new Image[4];
    private int huidigePagina=0;
    private ImageView imgPane;
    private Stage handleidingStage;


    public HandleidingView(){
        paginas[0] = new Image(getClass().getResource("img/handleiding_pag1.png").toString());
        paginas[1] = new Image(getClass().getResource("img/handleiding_pag2.png").toString());
        paginas[2] = new Image(getClass().getResource("img/handleiding_pag3.png").toString());
        paginas[3] = new Image(getClass().getResource("img/handleiding_pag4.png").toString());

        double button_width = 150.0;
        handleidingStage = new Stage();
        HBox hbox_handleiding = new HBox();

        Button btn_volgende = new Button("Volgende >>");
        Button btn_vorige = new Button("<< Vorige");
        btn_volgende.setMaxWidth(button_width);
        btn_vorige.setMaxWidth(button_width);

        btn_volgende.setOnAction(e -> {volgende();});
        btn_vorige.setOnAction(e -> {vorige();});

        hbox_handleiding.getChildren().addAll(btn_vorige,btn_volgende);
        hbox_handleiding.setAlignment(Pos.CENTER);

        imgPane = new ImageView(paginas[huidigePagina]);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        double height = screenSize.getHeight();
        imgPane.setFitHeight(height-70);//set heightn of image to hieght of computer -40
        imgPane.setPreserveRatio(true);
        imgPane.setOnMouseClicked( e ->  volgende());

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hbox_handleiding);
        borderPane.setCenter(imgPane);

        Scene handleiding_scene = new Scene(borderPane, 800, height);
        handleidingStage.setScene(handleiding_scene);
        handleidingStage.setTitle("Mollenmania - handleiding");
    }

    public void show(){
        handleidingStage.show();
    }

    private void vorige(){
        if(huidigePagina==0){
            huidigePagina=3;
        } else {
            huidigePagina--;
        }
        imgPane.setImage(paginas[huidigePagina]);
    }

    private void volgende(){
        if(huidigePagina==3){
            huidigePagina=0;
        } else{
            huidigePagina++;
        }
        imgPane.setImage(paginas[huidigePagina]);
    }
}

