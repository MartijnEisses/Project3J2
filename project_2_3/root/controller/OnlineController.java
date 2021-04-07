package root.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import root.Main;
import root.model.Game;
import root.model.Online;
import root.model.Reversi;
import java.net.URL;
import java.util.ResourceBundle;


public class OnlineController extends Online implements Initializable {


    @FXML



    protected void setScene(String path) {
        Main.setScene(path);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    protected void handleMainMenuButton(ActionEvent event){
        setScene("view/mains.fxml");
    }


}
