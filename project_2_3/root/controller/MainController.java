package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import root.model.TicTacToe;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller {

    @FXML
    ComboBox<String> selectGame;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectGame.getItems().addAll("TicTacToe", "Reversi");
        selectGame.getSelectionModel().select(String.valueOf(Games.TicTacToe));
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        selectedGame = getSelectedGame();
        setScene("view/board.fxml");

    }

    public Games getSelectedGame(){
        Games r = Games.valueOf(selectGame.getSelectionModel().getSelectedItem());
        return r;
    }

}
