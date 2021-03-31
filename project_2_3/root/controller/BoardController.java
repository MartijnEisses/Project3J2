package root.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import root.controller.Controller;
import root.model.Board;
import root.model.TicTacToe;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController extends Controller {

    @FXML
    GridPane gridBoard;
    Board board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = new Board(3, 3);
        createGrid();
    }


    public void createGrid(){
        int[] x = board.getBoard()[0];
        int[] y = board.getBoard()[1];

        for (int i = 0; i < x.length; i++){

            for (int j = 0; j < y.length; j++){

                //        gridBoard.add(new Label("0"), 0, 0);
//        gridBoard.add(new Label("0"), 0, 1);
//        gridBoard.add(new Label("0"), 1, 0);

            }
        }
    }

}