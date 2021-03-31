package root.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import root.Main;

public abstract class Controller implements Initializable {

    public enum Games{
        TicTacToe,
        Reversi
    }

    public Games selectedGame;

    protected void setScene(String path) {
        Main.setScene(path);
    }

}
