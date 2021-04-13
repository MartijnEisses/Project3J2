package root.server;

import root.controller.GameType;
import root.controller.OnlineController;
import root.controller.OnlineReversiBoardController;
import root.model.Reversi;
import root.model.ReversiAi;
import root.server.Connection;
import root.model.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Interpreter {
    private String GAMETYPE;
    private String PLAYERTOMOVE;
    private String OPPONENT;
    private static List<String> playerList;
    private static int gameID;
    private ReversiAi RandomReversiAI;
    private static List<String> legalmovesAI;
    private Board tempBoard;
    private OnlineReversiBoardController online;
    private static String[] gameChallenge;
    /*
        Note: zie protocol.txt op blackboard om de input te zien van de server
     */
    public Interpreter() {
        playerList = new ArrayList<>();
        legalmovesAI = new ArrayList<>();
        online = new OnlineReversiBoardController();
    }

    public void inputInterpreter(String inputCommand) {
        inputCommand = inputCommand.replace("{", "")
                .replace("}", "")
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "");

        String[] commands = inputCommand.split(" ");
        //System.out.println("Volgende line is van inputInterpreter:");
        //System.out.println(Arrays.toString(commands));

        //Switch case voor de mogelijke commands
        switch (commands[0]) {
            case "OK":
                System.out.println("Confim message from server, command is accepted!");
                break;
            case "ERR":
                System.out.println("Command is not accept, there is an error!");
                break;
            case "SVR":
                //
                // System.out.println("Test SVR");
                switch (commands[1]) {
                    case "GAMELIST":
                        System.out.println("Gamelist of games");

                        break;
                    case "PLAYERLIST":
                        System.out.println("Playerlist of all players");

                        for (int i = 2; i < commands.length; i++) {
                            String temp = commands[i];
                            playerList.add(temp);
                            //System.out.println(commands[i]);
                        }
                        break;
                    case "HELP":
                        break;

                    case "GAME":
                        //System.out.println("Test GAME");
                        switch (commands[2]) {
                            case "MATCH":
                                //hier nog wat neerzetten van de match info.
                                GAMETYPE = commands[6];
                                PLAYERTOMOVE = commands[4];
                                OPPONENT = commands[8];
                                //System.out.println(PLAYERTOMOVE + GAMETYPE + OPPONENT);

                                break;


                            case "MOVE":
                                //reageer op de string move van de opponent.
                                int zet = Integer.parseInt(commands[6]);
                                int[] position = new int[2];
                                if (commands[4].equals(OPPONENT)) {
                                    System.out.println("Received move from opponent");
                                    System.out.println("Opponent made move on: " + zet);
                                } else {
                                    System.out.println("Received move from ai");
                                    System.out.println("ai made move on: " + zet);
                                    online.convertToBoardPosition(zet);
                                }
                                break;

                            case "YOURTURN":
                                //ai moet weten dat het zijn beurt is.
                                System.out.println("Its youre turn ai make a good move...");
                                break;
                            case "CHALLENGE":

                                //switch statement voor challenge.
                                //System.out.println("Test Challenger");
                                switch (commands[3]) {
                                    case "CHALLENGER:":
                                        //stuur challenge accept terug.
                                        //System.out.println("Send instant challenge back start");
                                        gameID = Integer.parseInt(commands[6]);
                                        System.out.println("Challenged by " + commands[4] + " for game: " + commands[8] + " gameID :" + commands[6]);
                                       // Connection.acceptGameChallenge(gameID);
                                        break;
                                    case "CANCELLED":
                                        //challenge is door de uitdager gecanceld
                                        System.out.println("Match has been cancelled");

                                        break;
                                }
                                break;
                            case "WIN":
                                //info over win
                                //alle stenen moeten worden gereset.
                                //Display that user has won.Terug naar online screen en display user has won.
                                break;
                            case "LOSS":
                                //info over loss.
                                //alle stenen moeten worden gereset.
                                //Display that user has lost. Terug naar online screen en display user has lost
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public static int getGameID() {
        return gameID;
    }

    public static List<String> getPlayerList() {
        return playerList;
    }

    public static String[] getGameChallenge() {
        return gameChallenge;
    }
}
