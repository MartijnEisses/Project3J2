package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import root.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller {

    public enum Games{
        TicTacToe,
        Reversi
    }

    @FXML
    ComboBox<String> selectGame;

    @FXML
    private GridPane content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectGame.getItems().addAll("TicTacToe", "Reversi");
        selectGame.getSelectionModel().select(String.valueOf(Games.TicTacToe));
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        setScene("view/board.fxml");
    }

    public Games getSelectedGame(){
        Games r = Games.valueOf(selectGame.getSelectionModel().getSelectedItem());
        return r;
    }

}
