package root.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import root.model.ReversiAi;
import root.model.Reversi;

import javafx.event.ActionEvent;
import java.net.URL;
import root.Main;
import java.util.ResourceBundle;

public class ReversiBoardController extends Reversi implements Initializable {

    @FXML
    GridPane gridBoard;
    private ReversiAi ai;
    public static int turn = 1;
    int[][] oldBoard = getBoard();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGridBoard(getBoard(), 75, 75);
        setStoneOnBoardStart(3, 4, 1);
        setStoneOnBoardStart(4, 3, 1);
        setStoneOnBoardStart(4, 4, 2);
        setStoneOnBoardStart(3, 3, 2);
    }

    public void createGridBoard(int[][] b, int i1, int i2) {
        for (int i = 0; i < b[0].length; i++) {
            for (int j = 0; j < b[1].length; j++) {
                Pane p = new Pane();
                p.setMinSize(i1, i2);
                p.setLayoutX(i);
                p.setLayoutY(j);
                final int x = i;
                final int y = j;

                setStone(x, y, 0);
                p.setOnMouseClicked(e -> setStoneOnBoard(x, y, turn));
                gridBoard.add(p, i, j);
            }
        }
    }
    public void setStoneOnBoardStart(int x, int y, int turn) {
            setStone(x, y, turn);
            switch (turn) {
            case 1:
                Circle stone_1 = new Circle();
                stone_1.setCenterX(100.0f);
                stone_1.setCenterY(100.0f);
                stone_1.setRadius(30.0f);
                gridBoard.add(stone_1, x, y);
                this.turn = 2;
                break;
            case 2:
                Circle stone_2 = new Circle();
                stone_2.setCenterX(100.0f);
                stone_2.setCenterY(100.0f);
                stone_2.setRadius(30.0f);
                stone_2.setFill(Color.WHITE);
                gridBoard.add(stone_2, x, y);
                this.turn = 1;
                break;
            }
    }
    public void setStoneOnBoard(int x, int y, int turn) {
            boardChange(doMove(getBoard(), turn, y, x));
            updateBoard();
        try {
            aiSet();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void updateBoard(){
            int[][] newBoard = getBoard();
            switch (turn) {
                case 1:
                    for (int i = 0; i < newBoard.length; i++) {
                        for (int j = 0; j < newBoard[1].length; j++) {

                            if (newBoard[i][j] != oldBoard[i][j]) {
                                Circle stone_1 = new Circle();
                                stone_1.setCenterX(100.0f);
                                stone_1.setCenterY(100.0f);
                                stone_1.setRadius(30.0f);
                                gridBoard.add(stone_1, j, i);
                                this.turn = 2;
                            }
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < newBoard.length; i++) {
                        for (int j = 0; j < newBoard[1].length; j++) {

                            if (newBoard[i][j] != oldBoard[i][j]) {
                                Circle stone_2 = new Circle();
                                stone_2.setCenterX(100.0f);
                                stone_2.setCenterY(100.0f);
                                stone_2.setRadius(30.0f);
                                stone_2.setFill(Color.WHITE);
                                gridBoard.add(stone_2, j, i);
                                this.turn = 1;
                            }
                        }
                    }
                    break;
            }
            oldBoard = getBoard();

    }
    public void aiSet() throws InterruptedException {
        aiMove();
        updateBoard();

    }
    @FXML
    public void onlineLobby(ActionEvent actionEvent) { Main.setScene("view/online.fxml"); }
    @FXML
    public void goMain(ActionEvent actionEvent) { Main.setScene("view/main.fxml"); }


}
