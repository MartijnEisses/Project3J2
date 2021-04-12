package root.model;

import java.util.Scanner;

public class TicTacToe extends Board {

    Scanner in = new Scanner(System.in);

    private enum Color {
        BLACK, WHITE, EMPTY
    }

    // AI is BLACK player is WHITE
    private int[][] board = { { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() },
            { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() },
            { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() } };
    public int[][] boardPosition = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

    Board b;
    boolean endGame = false;
    int turn = 0;

    public TicTacToe() {
        super(3, 3);
        b = new Board(3, 3);

    }

    public int BestMove() {
        int bestScore = -1;
        int bestMove = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // is there an empty spot
                if (board[i][j] == Color.EMPTY.ordinal()) {
                    board[i][j] = Color.BLACK.ordinal();
                    int score = Minimax(board);
                    board[i][j] = Color.EMPTY.ordinal();
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = boardPosition[i][j];
                    }
                }
            }
        }
        return bestMove;
    }

    private int Minimax(int[][] board2) {
        return 1;
    }

    public void Move(int position, int turn) {
        Color color = Color.EMPTY;
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

        if (turn == 8) {
            System.out.println("TIE");
        }
        return false;
    }

    private void Win(Color winner) {
        System.out.println(winner + " HAS WON!!");
        // TODO: Call win function in the main class?!?!
    }
}