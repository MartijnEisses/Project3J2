package root.server;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;
import root.Main;
import root.controller.OnlineController;
import root.controller.ReversiBoardController;
import root.model.Board;
import root.model.Online;
import root.model.ReversiAi;
import root.model.Reversi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Interpreter {
    private String GAMETYPE;
    private String PLAYERTOMOVE;
    private String OPPONENT;
    private static List<String> playerList;
    private static int gameID;
    private ReversiAi RandomReversiAI;
    private static List<String> legalmovesAI;
    private OnlineController onlineController;
    private Reversi online = new Reversi();
    private static String gameChallenge;
    private int playerAI;
    private int playerOpponent;
    private int playerturn;
    /*
        Note: zie protocol.txt op blackboard om de input te zien van de server
     */
    public Interpreter() {
        playerList = new ArrayList<>();
        legalmovesAI = new ArrayList<>();


    }
    public void inputInterpreter(String inputCommand) {
        inputCommand = inputCommand.replaceAll("[^A-Za-z0-9 ]", "");
        String[] commands = inputCommand.split(" ");
        System.out.println("Volgende line is van inputInterpreter:");
        System.out.println(Arrays.toString(commands));
        //Switch case voor de mogelijke commands
        switch (commands[0]) {
            case "OK":
                System.out.println("Confim message from server, command is accepted!");
                break;
            case "ERR":
                System.out.println("Command is not accept, there is an error!");
                break;
            case "SVR":
                switch (commands[1]) {
                    case "GAMELIST":
                        System.out.println("Gamelist of games");

                        break;
                    case "PLAYERLIST":
                        System.out.println("Playerlist of all players");

                        for (int i = 2; i < commands.length; i++) {
                            String temp = commands[i];
                            playerList.add(temp);
                            System.out.println(commands[i]);
                        }
                        break;
                    case "HELP":
                        break;

                    case "GAME":
                        //System.out.println("Test GAME");
                        switch (commands[2]) {
                            case "MATCH":
                                //hier nog wat neerzetten van de match info.


                                //System.out.println(PLAYERTOMOVE + GAMETYPE + OPPONENT);

                            switch (commands[3]) {
                                case "PLAYERTOMOVE":
                                    System.out.println("START OF PLAYERTOMOVE");
                                    PLAYERTOMOVE = commands[4];
                                    GAMETYPE = commands[6];
                                    OPPONENT = commands[8];
                                    //System.out.println("start van PLAYER TO MOVE TEST");
                                    if (PLAYERTOMOVE.equals(OPPONENT)) {
                                        System.out.println("Opponent is color black");
                                        //online.setAiColor(2);
                                        setPlayerAI(2);
                                        setPlayerOpponent(1);
                                        //primaryStage.
                                    } else {
                                        System.out.println("ai has color black");
                                        //online.setAiColor(1);
                                        setPlayerAI(1);
                                        setPlayerOpponent(2);
                                    }
                                    System.out.println("Player to move: " + PLAYERTOMOVE);
                                    break;
                            }
                            break;
                            case "MOVE":
                                //reageer op de string move van de opponent.
                                //String isLegalMove = commands[8];
                               try{
                                    int zet = Integer.parseInt(commands[6]);
                                    int[] position;
                                    System.out.println(commands[6]);
                                    if (commands[4].equals(OPPONENT)) {
                                        System.out.println("Received move from opponent");
                                        System.out.println("Opponent made move on: " + zet);
                                        position = online.convertToBoardPosition(zet);
                                        System.out.println("Position[1] " + position[1] + " Position[2] " + position[0] + " for player: " + getPlayerOpponent());
                                        //online.setStoneOnBoard(position[0], position[1], getPlayerOpponent());
                                        //System.out.println("Draw Board method in MOVE, MOVE is van opponent");
                                        //online.drawBoard();
                                        //online.placeStone();
                                        //online.setStone(position[1], position[0], getPlayerOpponent());
                                        //online.drawBoard();
                                        online.doMove(online.getBoard(), getPlayerOpponent(),position[1],position[0]);
                                        online.drawBoard();
                                        //online.setStoneOnBoardStart(3,2,getPlayerOpponent());
                                        System.out.println(online.legalMoves(online.getBoard(), getPlayerOpponent()));
                                    } else {
                                        System.out.println("Received move from ai");
                                        System.out.println("ai made move on: " + zet);
                                        online.convertToBoardPosition(zet);

                                        position = online.convertToBoardPosition(zet);
                                        System.out.println("Position[1] " + position[1] + " Position[2] " + position[0] + " for player: " + getPlayerAI());
                                        //online.setStoneOnBoard(position[0], position[1], getPlayerAI());
                                        //online.legalMoves(online.getBoard(), getPlayerAI());
                                        //online.setStone(position[1], position[0], getPlayerAI());
                                        //online.drawBoard();
                                        //online.drawBoard();
                                        online.doMove(online.getBoard(), getPlayerOpponent(),position[1],position[0]);
                                        online.drawBoard();
                                       //online.setStoneOnBoardStart(3,2,getPlayerOpponent());
                                        System.out.println(online.legalMoves(online.getBoard(), getPlayerAI()));
                                        //System.out.println("Draw Board method in MOVE, MOVE is van AI");
                                        //online.drawBoard();
                                    }
                                }catch (Exception e){
                                   System.out.println("Move is illegal");
                               }
                            break;

                            case "YOURTURN":
                                //ai moet weten dat het zijn beurt is.
                                System.out.println("Its youre turn ai make a good move...");
                                System.out.println();
                                //online.aiMove();
                                System.out.println( "ai player is: " + getPlayerAI() + " opponenent: " + getPlayerOpponent() );
                                if(getPlayerAI() ==1 ) {
                                    Connection.setMove(37);
                                    //online.aiMove(1);
                                    System.out.println("sending ai for black");
                                }else{
                                    Connection.setMove(20);
                                    //online.aiMove(2);
                                    System.out.println("Sending ai for white");
                                }
                                break;

                            case "CHALLENGE":
                                //switch statement voor challenge.
                                //System.out.println("Test Challenger");
                                switch (commands[3]) {
                                    case "CHALLENGER":
                                        //stuur challenge accept terug.
                                        //System.out.println("Send instant challenge back start");
                                        gameID = Integer.parseInt(commands[6]);
                                        System.out.println("Challenged by " + commands[4] + " for game: " + commands[8] + " gameID :" + commands[6]);
                                        //Connection.acceptGameChallenge(gameID);
                                        gameChallenge = commands[4];
                                        break;
                                    case "CANCELLED":
                                        //challenge is door de uitdager gecanceld
                                        System.out.println("Match has been cancelled");
                                        break;
                                }
                                break;
                            case "WIN":
                                //info over win

                                //online.setScene("view/online.fxml");
                                //alle stenen moeten worden gereset.
                                //Display that user has won.Terug naar online screen en display user has won.
                                break;
                            case "LOSS":
                                //info over loss.
                               // online.setScene("view/online.fxml");
                                //alle stenen moeten worden gereset.

                                //Display that user has lost. Terug naar online screen en display user has lost
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + commands[2]);
                        }
                        break;
                }
                break;
        }
    }
    public static void getReversi(){

    }

    public static int getGameID() {
        return gameID;
    }

    public static List<String> getPlayerList() {
        return playerList;
    }

    public static String getGameChallenge() {
        return gameChallenge;
    }

    public void setGameChallenge(String name){
        gameChallenge = name;
    }

    public int getPlayerAI(){
        return playerAI;
    }

    public int getPlayerOpponent(){
        return playerOpponent;
    }

    public void setPlayerAI(int number){
        this.playerAI = number;
    }

    public void setPlayerOpponent(int playerOpponent) {
        this.playerOpponent = playerOpponent;
    }


}
class AlertHelpers {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}