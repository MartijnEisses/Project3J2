package root.model;

import javafx.scene.layout.Pane;

public class Board {
    private int[][] board;

    public Board(int x, int y) {
        this.board = new int[x][y];
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setStone(int x, int y, int value) {
        board[x][y] = value;
    }

    public int getStone(int x, int y) {
        return board[x][y];
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y] == 0;
    }

    public void DrawBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }


}
