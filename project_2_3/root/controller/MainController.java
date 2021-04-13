package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import root.Main;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private PlayerType playerType;
    private GameType gameType;


    @FXML
    ComboBox<String> selectGame;

    public static GameType selectedGame;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectGame.getItems().addAll("TicTacToe", "Reversi");
        selectGame.getSelectionModel().select(String.valueOf(gameType.TicTacToe));
    }

    protected void setScene(String path) {
        Main.setScene(path);
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        selectedGame = getGameType();
        setPlayerType(playerType.LOCAL);
        switch(getGameType()) {
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
        setPlayerType(playerType.REMOTE);
        setScene("view/config.fxml");

    }



    public GameType getGameType(){
        GameType game  = GameType.valueOf(selectGame.getSelectionModel().getSelectedItem());
        return game;
    }



    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
