package root.server;

import root.server.Connection;
import java.util.Arrays;
import java.util.List;

public class Interpreter {
    private String GAMETYPE;
    private String PLAYERTOMOVE;
    private String OPPONENT;
    private List<String> playerList;
    private List<String> gameList;
    private static int gameID;

    /*
        Note: zie protocol.txt op blackboard om de input te zien van de server
     */
    public Interpreter(){


    }

    public void inputInterpreter(String inputCommand){
         inputCommand = inputCommand.replace("{", "")
                                    .replace("}", "")
                                    .replace(",", "")
                                    .replace("\"", "");

        String[] commands = inputCommand.split(" ");
        System.out.println("Volgende line is van inputInterpreter:");
        System.out.println(Arrays.toString(commands));

        //Switch case voor de mogelijke commands
        switch (commands[0]){
            case "OK":
                System.out.println("Confim message from server, command is accepted!");
                break;
            case "ERR":
                System.out.println("Command is not accept, there is an error!");
                break;
            case "SVR":
                System.out.println("Test SVR");
                switch (commands[1]) {
                    case "GAMELIST":
                        System.out.println("Gamelist of games");
                        break;
                    case "PLAYERLIST":
                        System.out.println("Playerlist of all players");
                        //playerList = Connection.getPlayerlist();
                        //System.out.println(playerList);
                        break;
                    case "HELP":
                        break;

                    case "GAME":
                        System.out.println("Test GAME");
                        switch (commands[2]){
                            case "MATCH":
                                //hier nog wat neerzetten van de match info.
                                GAMETYPE = commands[4];
                                PLAYERTOMOVE = commands[6];
                                OPPONENT = commands[8];
                                break;
                            case "MOVE":
                                //reageer op de string move van de opponent.
                                int zet = Integer.parseInt(commands[6]);
                                if(commands[4].equals(OPPONENT)){
                                    System.out.println("Received move from opponent");
                                    System.out.println("Opponent made move on: " + zet);
                                } else{
                                    System.out.println("Received move from ai");
                                    System.out.println("ai made move on: " + zet);
                                }
                                break;
                            case "YOURTURN":
                                //ai moet weten dat het zijn beurt is.
                                System.out.println("Its youre turn ai make a good move...");

                                break;
                            case "CHALLENGE":

                                //switch statement voor challenge.
                                System.out.println("Test Challenger");
                                switch (commands[3]){
                                    case "CHALLENGER:":
                                        //stuur challenge accept terug.
                                        //System.out.println("Send instant challenge back start");
                                        gameID = Integer.parseInt(commands[6]);
                                        System.out.println("Challenged by " + commands[4] + " for game: " + commands[8] + " gameID :" + commands[6]);
                                        System.out.println("Sending challenge accept back!");
//                                        Connection.acceptGameChallenge(gameID);
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
}
