package root.model;

import java.util.Scanner;

public class TicTacToe {

    private enum Color {
        BLACK, WHITE, EMPTY
    }

    private int[][] board = { { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() },
            { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() },
            { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() } };

    Board b;
    boolean endGame = false;
    int turn = 0;

    public TicTacToe() {
        b = new Board(3, 3);
        Scanner sc = new Scanner(System.in);
    }

    public void Start() {
        DrawBoard(board);
        while (!endGame) {

        }
    }

    public void Move(int x, int y, Color color) {
        if (ValidMove(x, y)) {
            board[x][y] = color.ordinal();
        }

    }

    public void DrawBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                System.out.print(GetValue(board[i][j]));
            }
            System.out.print("\n");
        }
    }

    public Boolean ValidMove(int x, int y) {
        if (board[x][y] == Color.EMPTY.ordinal()) {
            return true;
        } else
            return false;
    }

    private String GetValue(int value) {
        if (value == Color.BLACK.ordinal()) {
            return "X";
        } else if (value == Color.WHITE.ordinal()) {
            return "O";
        } else
            return "*";
    }
}