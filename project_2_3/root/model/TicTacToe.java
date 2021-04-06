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

        while (!endGame) {
            boolean correctInput = false;

            while (!correctInput) {
                int m = in.nextInt();

                if (turn % 2 == 0) {
                    Move(m, Color.WHITE);
                } else if (turn % 2 == 1) {
                    Move(m, Color.BLACK);

                }
            }
        }
    }

    public void Move(int position, Color color) {
        switch (position) {
        case 0:
            if (ValidMove(0, 0)) {
                board[0][0] = color.ordinal();
            }

            break;
        case 1:
            if (ValidMove(0, 1)) {
                board[0][1] = color.ordinal();
            }

            break;
        case 2:
            if (ValidMove(0, 2)) {
                board[0][2] = color.ordinal();
            }

            break;
        case 3:
            if (ValidMove(1, 0)) {
                board[1][0] = color.ordinal();
            }

            break;
        case 4:
            if (ValidMove(1, 1)) {
                board[1][1] = color.ordinal();
            }

            break;
        case 5:
            if (ValidMove(1, 2)) {
                board[1][2] = color.ordinal();
            }

            break;
        case 6:
            if (ValidMove(2, 0)) {
                board[2][0] = color.ordinal();
            }

            break;
        case 7:
            if (ValidMove(2, 1)) {
                board[2][1] = color.ordinal();
            }

            break;
        case 8:
            if (ValidMove(2, 2)) {
                board[2][2] = color.ordinal();
            }
            break;
        default:
            break;
        }
        if (CheckForWin()) {
            endGame = true;
        }

    }

    public Boolean ValidMove(int x, int y) {
        if (board[x][y] == Color.EMPTY.ordinal()) {
            return true;
        } else
            System.out.println("INVALID MOVE");
        return false;
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