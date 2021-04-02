package root.model;

import java.io.BufferedReader;
import java.util.Scanner;

public class TicTacToe extends Board {

    Scanner in = new Scanner(System.in);

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
        super(3, 3);
        b = new Board(3, 3);

    }

    public void Start() {
        DrawBoard(board);
        System.out.println("write x coord [enter] y coord [enter]");

        System.out.println("The Board");
        System.out.println("  0 1 2");
        System.out.println("0 * * *");
        System.out.println("1 * * *");
        System.out.println("2 * * *");

        while (!endGame) {

            System.out.println("Player 1:");
            int x = in.nextInt();
            int y = in.nextInt();

            Move(x, y, Color.WHITE);

            if (CheckForWin()) {
                endGame = true;
            }

            System.out.println("Player 2:");
            int X = in.nextInt();
            int Y = in.nextInt();

            Move(X, Y, Color.BLACK);

            if (CheckForWin()) {
                endGame = true;
            }

        }
    }

    public void Move(int x, int y, Color color) {
        if (ValidMove(x, y)) {
            board[x][y] = color.ordinal();
            CheckForWin();
        }
        DrawBoard(board);

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
            System.out.println("INVALID MOVE");
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

    private boolean CheckForWin() {
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == Color.BLACK.ordinal()) {
            Win(Color.BLACK);
            return true;
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == Color.WHITE.ordinal()) {
            Win(Color.WHITE);
            return true;
        }

        return false;
    }

    private void Win(Color winner) {
        System.out.println(winner + " HAS WON!!");
    }
}