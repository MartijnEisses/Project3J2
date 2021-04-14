package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import root.Main;
import root.model.Reversi;
import root.model.Board;
import root.model.ReversiAi;
import root.server.Interpreter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;

public class OnlineReversiBoardController extends Reversi implements Initializable {

    @FXML
    GridPane gridBoard;
    public ReversiAi ai = new ReversiAi();
    public static int turn = 0;
    int[][] oldBoard = getBoard();
    private int aiColor;
    private boolean againstAi = true;
    private Interpreter interpreter;


    public OnlineReversiBoardController(){

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGridBoard(getBoard(), 75, 75);
        setStoneOnBoardStart(3,3,1);
        setStoneOnBoardStart(3,4,2);
        setStoneOnBoardStart(4,3,2);
        setStoneOnBoardStart(4,4,1);
        drawBoard();
    }
    public void setAiColor(int ai){
        System.out.println("Ai is player " + ai);
        aiColor = ai;
    }
    //@FXML
    //protected void setScene(String path) {
    //    Main.setScene(path);
    //}

    //@FXML
    //protected void handleConfigAction(ActionEvent event){
    //    setScene("view/online.fxml");
    //}

    public int[] convertToBoardPosition(int p){
        int[]position = new int[2];
        int counter  = 0;
        for(int x = 0; x < board[0].length; x++){
            for(int y = 0; y< board[1].length; y++){
                if(p == counter){
                    position[0] = x;
                    position[1] = y;
                }
                //System.out.println(counter);
                counter++;
            }
        }
        return position;
    }


    public void createGridBoard(int[][] b, int i1, int i2){
        for (int i = 0; i < b[0].length; i++){
            for (int j = 0; j < b[1].length; j++){
                Pane p = new Pane();
                p.setMinSize(i1, i2);
                p.setLayoutX(i);
                p.setLayoutY(j);
                setStone(i,j,turn);
                //setStoneOnBoard(x,y, turn);
                gridBoard.add(p, i, j);
            }
        }
    }

    public void placeStone(){
        System.out.println("hij doet iets1");
        Circle stone_1 = new Circle();
        stone_1.setCenterX(100.0f);
        stone_1.setCenterY(100.0f);
        stone_1.setRadius(30.0f);
        gridBoard.add(stone_1, 3, 3);
        System.out.println("hij doet iets");
    }
    public void refreshBoard(int[][] board, int turn) {
        switch (turn) {
            case 1:
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[1].length; j++) {

                        if (board[i][j] != oldBoard[i][j]) {
                            Circle stone_1 = new Circle();
                            stone_1.setCenterX(100.0f);
                            stone_1.setCenterY(100.0f);
                            stone_1.setRadius(30.0f);
                            stone_1.setFill(Color.BLACK);
                            gridBoard.add(stone_1, j, i);
                            this.turn = 2;
                            drawBoard();
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[1].length; j++) {

                        if (board[i][j] != oldBoard[i][j]) {
                            Circle stone_2 = new Circle();
                            stone_2.setCenterX(100.0f);
                            stone_2.setCenterY(100.0f);
                            stone_2.setRadius(30.0f);
                            stone_2.setFill(Color.WHITE);
                            gridBoard.add(stone_2, j, i);
                            this.turn = 1;
                            System.out.println("Set stone on board");
                            drawBoard();
                        }
                    }
                }
                break;
        }
        oldBoard = board;
    }
    public void setStoneOnBoardStart(int x, int y, int turn) {
        //if(isEmpty(x, y)){
        //setStone(x, y, turn);
        boardChange(doMove(getBoard(), turn, x, y));
        //drawBoard();
        switch (turn) {
            case 1:
                Circle stone_1 = new Circle();
                stone_1.setCenterX(100.0f);
                stone_1.setCenterY(100.0f);
                stone_1.setRadius(30.0f);
                gridBoard.add(stone_1, x, y);

                this.turn = 2;
                break;
            case 2:
                Circle stone_2 = new Circle();
                stone_2.setCenterX(100.0f);
                stone_2.setCenterY(100.0f);
                stone_2.setRadius(30.0f);
                stone_2.setFill(Color.WHITE);
                gridBoard.add(stone_2, x, y);
                this.turn = 1;
                break;
        }
    }

    public void aiMove(int[][] board,int player){
        System.out.println("aiMove() is creating a move!");
        //boardChange(ai.calculateRandomMove(legalMoves(getBoard(), this.turn), getBoard(), this.turn));

        boardChange(ai.calculateRandomMove(legalMoves(board,player), board, player));
        if(getBoard() == oldBoard){
            if(this.turn == 1) { this.turn++;}
            else{this.turn--;}
        }
        refreshBoard(getBoard(), player);

    }
    public void setStoneOnBoard(int x, int y, int turn) {
        if(x!=100){
            if(isEmpty(x, y)) {
                //setStone(x, y, turn);
                System.out.println("Setting stone on position y: " + y + " x: " + x +  " for player " + turn);
                boardChange(doMove(getBoard(), turn, y, x));
                drawBoard();
            }
        }
        int[][] newBoard = getBoard();
        //System.out.println(x+"x-y"+y);
        switch (turn) {
            case 1:
                for (int i = 0; i < newBoard.length; i++) {
                    for (int j = 0; j < newBoard[1].length; j++) {

                        if (newBoard[i][j] != oldBoard[i][j]) {
                            Circle stone_1 = new Circle();
                            stone_1.setCenterX(100.0f);
                            stone_1.setCenterY(100.0f);
                            stone_1.setRadius(30.0f);
                            stone_1.setFill(Color.BLACK);
                            gridBoard.add(stone_1, j, i);
                            this.turn = 2;
                            drawBoard();
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < newBoard.length; i++) {
                    for (int j = 0; j < newBoard[1].length; j++) {

                        if (newBoard[i][j] != oldBoard[i][j]) {
                            Circle stone_2 = new Circle();
                            stone_2.setCenterX(100.0f);
                            stone_2.setCenterY(100.0f);
                            stone_2.setRadius(30.0f);
                            stone_2.setFill(Color.WHITE);
                            gridBoard.add(stone_2, j, i);
                            this.turn = 1;
                            System.out.println("Set stone on board");
                            drawBoard();
                        }
                    }
                }
                break;
        }
        //if(fullBoard() == true){
         //   if(winner() == true){System.out.println("black has won");}
         //   else{ System.out.println("white has won");}
       // }
        oldBoard = getBoard();
        drawBoard();

        //if (againstAi == true && aiColor == this.turn) {
        //boardChange(ai.calculateRandomMove(legalMoves(getBoard(), this.turn), getBoard(), this.turn));
        //if(getBoard() == oldBoard){
        //   if(this.turn == 1) { this.turn++;}
        //  else{this.turn--;}
        // }
        //setStoneOnBoard(100,100,this.turn);
        //}
    }



    public void onlineLobby(ActionEvent actionEvent) { Main.setScene("view/online.fxml"); }

    public void goMain(ActionEvent actionEvent) { Main.setScene("view/main.fxml"); }







}
