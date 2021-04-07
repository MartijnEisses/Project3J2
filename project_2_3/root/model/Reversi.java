package root.model;


import java.util.HashMap;
import java.util.ArrayList;
public class Reversi extends Board {

    final double CORNERPLACE = 10;
    final double EDGEPLACE = 5;
    final double PLACENEXTCORNER = -10;
    double stonePosition[][] = new double[8][8];


/*
 * Reversi Board Cheat Sheet
 *
 * 0,0|1,0|2,0|3,0|4,0|5,0|6,0|7,0
 * -------------------------------
 * 0,1|1,1|2,1|3,1|4,1|5,1|6,1|7,1
 * -------------------------------
 * 0,2|1,2|2,2|3,2|4,2|5,2|6,2|7,2
 * -------------------------------
 * 0,3|1,3|2,3|3,3|4,3|5,3|6,3|7,3
 * -------------------------------
 * 0,4|1,4|2,4|3,4|4,4|5,4|6,4|7,4
 * -------------------------------
 * 0,5|1,5|2,5|3,5|4,5|5,5|6,5|7,5
 * -------------------------------
 * 0,6|1,6|2,6|3,6|4,6|5,6|6,6|7,6
 * -------------------------------
 * 0,7|1,7|2,7|3,7|4,7|5,7|6,7|7,7
 *
 */


    public Reversi() {
        super(8, 8);

    }

    /*


     */
    private enum Color {
        BLACK, WHITE
    }

    /*


     */
    private void bordPositions(){
        //Posities in de hoeken.
        stonePosition[0][0] = CORNERPLACE;
        stonePosition[7][7] = CORNERPLACE;
        stonePosition[0][7] = CORNERPLACE;
        stonePosition[7][0] = CORNERPLACE;
        //Posities naast de hoeken waar geen steen op moet komen.
        stonePosition[1][0] = EDGEPLACE;
        stonePosition[1][1] = EDGEPLACE;
        stonePosition[0][1] = EDGEPLACE;

        stonePosition[6][1] = EDGEPLACE;
        stonePosition[7][1] = EDGEPLACE;
        stonePosition[6][0] = EDGEPLACE;

        stonePosition[6][6] = EDGEPLACE;
        stonePosition[6][7] = EDGEPLACE;
        stonePosition[7][6] = EDGEPLACE;

        stonePosition[1][6] = EDGEPLACE;
        stonePosition[0][6] = EDGEPLACE;
        stonePosition[1][7] = EDGEPLACE;
    }

    /*
    private enum Direction {
        NORTH, NORTHWEST, NORTHEAST, SOUTH, SOUTHEAST, SOUTHWEST, WEST, EAST
    }
    */







    /*
        Minimax algorithm
     */
    private static int miniMax(){

        return 0;
    }
}
