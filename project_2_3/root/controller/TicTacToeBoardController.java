package root.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import root.model.Game;
import root.model.TicTacToe;
import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeBoardController extends TicTacToe implements Initializable {

    @FXML
    GridPane gridBoard;

    public static int turn = 1;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGridBoard(getBoard(), 175, 175);
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
                    ImageView img_o = new ImageView(new Image("root/view/O.png"));
                    img_o.setFitHeight(140);
                    img_o.setFitWidth(140);
                    gridBoard.add(img_o, x, y);

                    this.turn = 2;
                    break;
                case 2:
                    ImageView img_x = new ImageView(new Image("root/view/X.png"));
                    img_x.setFitHeight(140);
                    img_x.setFitWidth(140);
                    gridBoard.add(img_x, x, y);
                    this.turn = 1;
                    break;
            }
        }
    }
}