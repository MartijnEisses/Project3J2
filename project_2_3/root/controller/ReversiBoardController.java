package root.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import root.model.Game;
import root.model.Reversi;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiBoardController extends Reversi implements Initializable {

    @FXML
    GridPane gridBoard;
    private Reversi reversi;
    public static int turn = 1;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGridBoard(getBoard(), 75, 75);
        setStoneOnBoard(3,3,1);
        setStoneOnBoard(3,4,2);
        setStoneOnBoard(4,3,2);
        setStoneOnBoard(4,4,1);
    }

    public void createGridBoard(int[][] b, int i1, int i2){

        for (int i = 0; i < b[0].length; i++){
            for (int j = 0; j < b[1].length; j++){
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

    public void setStoneOnBoard(int x, int y, int turn) {
        if(isEmpty(x, y)){
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
    }
}