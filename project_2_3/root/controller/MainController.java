package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import root.Main;
import root.model.Board;
import root.model.Reversi;
import root.model.TicTacToe;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private GameType gameType;

    public enum Games{
        TicTacToe,
        Reversi
    }

    public enum GameType{
        Local,
        Online
    }

    @FXML
    ComboBox<String> selectGame;

    public static Games selectedGame;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectGame.getItems().addAll("TicTacToe", "Reversi");
        selectGame.getSelectionModel().select(String.valueOf(Games.TicTacToe));
    }

    protected void setScene(String path) {
        Main.setScene(path);
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        selectedGame = getSelectedGame();
        setGameType(GameType.Local);
        switch(getSelectedGame()) {
            case TicTacToe:
                setScene("view/TicTacToeBoard.fxml");
                break;
            case Reversi:
                setScene("view/ReversiBoard.fxml");
                break;
        }
    }

    @FXML
    protected void handleOnlineButtonAction(ActionEvent event){
        setGameType(GameType.Online);
        setScene("view/config.fxml");

    }

    public Games getSelectedGame(){
        Games r = Games.valueOf(selectGame.getSelectionModel().getSelectedItem());
        return r;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType type) {
        gameType = type;
    }

}
