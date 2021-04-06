package root.model;

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

    public void Move(int position, int turn) {
        Color color = Color.EMPTY; // only for initialization
        if (turn % 2 == 0) {
            color = Color.BLACK;
        } else if (turn % 2 == 1) {
            color = Color.WHITE;
        }

        switch (position) {
        case 0:
            if (ValidMove(0, 0)) {
                board[0][0] = color.ordinal();
                setStone(0, 0, turn);
            }

            break;
        case 1:
            if (ValidMove(0, 1)) {
                board[0][1] = color.ordinal();
                setStone(0, 1, turn);
            }

            break;
        case 2:
            if (ValidMove(0, 2)) {
                board[0][2] = color.ordinal();
                setStone(0, 2, turn);
            }

            break;
        case 3:
            if (ValidMove(1, 0)) {
                board[1][0] = color.ordinal();
                setStone(1, 0, turn);
            }

            break;
        case 4:
            if (ValidMove(1, 1)) {
                board[1][1] = color.ordinal();
                setStone(1, 1, turn);
            }

            break;
        case 5:
            if (ValidMove(1, 2)) {
                board[1][2] = color.ordinal();
                setStone(1, 2, turn);
            }

            break;
        case 6:
            if (ValidMove(2, 0)) {
                board[2][0] = color.ordinal();
                setStone(2, 0, turn);
            }

            break;
        case 7:
            if (ValidMove(2, 1)) {
                board[2][1] = color.ordinal();
                setStone(2, 1, turn);
            }

            break;
        case 8:
            if (ValidMove(2, 2)) {
                board[2][2] = color.ordinal();
                setStone(2, 2, turn);
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
        // TODO: Call win function in the main class?!?!
    }
}