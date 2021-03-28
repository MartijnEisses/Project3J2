package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import root.Main;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private ComboBox cb;
    public MainController(){
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        System.out.println(cb.getScene());
    }

    public enum selectedGame{
        TicTacToe,
        Reversi
    }


    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
//        actiontarget.setText("Sign in button pressed");
//        System.out.println(cb.getScene());
    }

    public selectedGame getSelectedGame(){
        return selectedGame.Reversi;
    }



}
