package root.model;

import java.util.ArrayList;
import java.util.Random;
public class ReversiAi extends Reversi{
    //Variables
    private Boolean[][] geldigeZetten;
    private String id;

    final double CORNERPLACE = 10;
    final double EDGEPLACE = 5;
    final double PLACENEXTCORNER = -10;
    double stonePosition[][] = new double[8][8];
    private Random random = new Random();





    public ReversiAi() {
    }


    public void bordPositions(){
        //Posities in de hoeken.
        stonePosition[0][0] = CORNERPLACE;
        stonePosition[7][7] = CORNERPLACE;
        stonePosition[0][7] = CORNERPLACE;
        stonePosition[7][0] = CORNERPLACE;
        //Posities naast de hoeken waar geen steen op moet komen.
        stonePosition[1][0] = EDGEPLACE;
        stonePosition[1][1] = EDGEPLACE;
        stonePosition[0][1] = EDGEPLACE;

        stonePosition[1][6] = EDGEPLACE;
        stonePosition[7][1] = EDGEPLACE;
        stonePosition[6][0] = EDGEPLACE;

        stonePosition[6][6] = EDGEPLACE;
        stonePosition[6][7] = EDGEPLACE;
        stonePosition[7][6] = EDGEPLACE;

        stonePosition[1][6] = EDGEPLACE;
        stonePosition[0][6] = EDGEPLACE;
        stonePosition[1][7] = EDGEPLACE;
    }

    public int[][] calculateRandomMove(ArrayList<String> legalMoves, int[][] board, int cp) {
        if(legalMoves.size() == 0) {
            System.out.println("no move: "+cp);
            return board;
        };
        int randomZet = random.nextInt(legalMoves.size());
        System.out.println(legalMoves.get(randomZet));
        String coordinates = legalMoves.get(randomZet);
        String[] move = coordinates.split("-");
        int x = Integer.parseInt(move[1]);
        int y = Integer.parseInt(move[0]);
        int[][] newBoard =  doMove(board, cp, x, y);
        return newBoard;
    }
}

