package root.server;


import java.util.Arrays;

public class Interpreter {
    private String GAMETYPE;
    private String PLAYERTOMOVE;
    private String OPPONENT;
    private Connection connection;
    /*
        Note: zie protocol.txt op blackboard om de input te zien van de server
     */
    public void inputInterpreter(String inputCommand){
        connection = new Connection();
        inputCommand = inputCommand.replaceAll("[A-Za-z0-9,]", "");
        String[] commands = inputCommand.split(" ");
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
                switch (commands[1]) {
                    case "GAMELIST":
                        System.out.println("Gamelist of games");
                        break;
                    case "PLAYERLIST":
                        System.out.println("Playerlist of all players");
                        break;
                    case "HELP":
                        break;

                    case "GAME":
                        switch (commands[2]){
                            case "MATCH":
                                //hier nog wat neerzetten van de match info.
                                GAMETYPE = commands[4];
                                PLAYERTOMOVE = commands[6];
                                OPPONENT = commands[8];
                                break;
                            case "MOVE":
                                //reageer op de string move van de opponent.
                                int zet = Integer.parseInt(commands[8]);
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

                                break;
                            case "CHALLENGE":
                                //switch statement voor challenge.
                                switch (commands[3]){
                                    case "CHALLENGER":
                                        //stuur challenge accept terug.
                                        int gameID = Integer.parseInt(commands[8]);
                                        System.out.println("Challenged by " + commands[4] + " for game: " + commands[6] + " gameID :" + commands[8]);
                                        System.out.println("Sending challenge accept back!");
                                        connection.acceptGameChallenge(gameID);
                                        break;
                                    case "CANCELLED":
                                        //challenge is door de uitdager gecanceld
                                        break;
                                }
                                break;
                            case "WIN":
                                //info over win
                                //alle stenen moeten worden gereset.
                                break;
                            case "LOSS":
                                //info over loss.
                                //alle stenen moeten worden gereset.
                                break;
                        }
                        break;
                }
                break;
        }
    }
}
