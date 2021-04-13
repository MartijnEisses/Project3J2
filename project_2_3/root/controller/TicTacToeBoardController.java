package root.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import root.model.TicTacToe;
import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeBoardController extends TicTacToe implements Initializable {

    @FXML
    GridPane gridBoard;

    public int turn = 1;
    public boolean versusAI = true;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGridBoard(getBoard(), 175, 175);
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

    public void setStoneOnBoard(int x, int y, int turn) {

        if (isEmpty(x, y)) {
            setStone(x, y, turn);
            switch (turn % 2) {
            case 1:
                ImageView img_o = new ImageView(new Image("root/view/O.png"));
                img_o.setFitHeight(140);
                img_o.setFitWidth(140);
                gridBoard.add(img_o, x, y);
                Move(boardPosition[x][y], turn);
                this.turn++;
                int bestmove = BestMove();
                setStoneOnBoard(oBoardPosition[bestmove][0], oBoardPosition[bestmove][1], this.turn);
                break;
            case 0:
                ImageView img_x = new ImageView(new Image("root/view/X.png"));
                img_x.setFitHeight(140);
                img_x.setFitWidth(140);
                gridBoard.add(img_x, x, y);
                Move(boardPosition[x][y], turn);
                this.turn++;
                break;
            }
            // // 1st turn is always the player
            // if (versusAI && turn % 2 == 0) {
            // int bestmove = BestMove();
            // this.turn++;
            // setStoneOnBoard(oBoardPosition[bestmove][0], oBoardPosition[bestmove][1],
            // this.turn);
            // }
            if (CheckForWin() != null) {
                Win(CheckForWin());
            }

        }
    }
}