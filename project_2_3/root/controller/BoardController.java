package root.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import root.controller.Controller;
import root.model.Board;
import root.model.TicTacToe;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController extends Controller {

    @FXML
    GridPane gridBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeGridBoard();
    }

    public void initializeGridBoard(){
        switch((Games)MainController.selectedGame) {
            case TicTacToe:
                createGridBoard(new Board(3,3), 175, 175);
                break;

            case Reversi:
                createGridBoard(new Board(8,8), 75, 75);
                break;
        }
    }

    public void createGridBoard(Board b, int i1, int i2){
        int[] x = b.getBoard()[0];
        int[] y = b.getBoard()[1];

        Pane p = null;

        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < y.length; j++){
                p = new Pane();
                p.setMinSize(i1, i2);
                gridBoard.add(p, i, j);
            }
        }
    }

}