package root.model;

public class Board {

    int x;
    int y;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
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
