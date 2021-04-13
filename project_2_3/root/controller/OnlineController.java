package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import root.Main;
import root.model.Online;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import root.server.Connection;
import root.server.Interpreter;


public class OnlineController extends Online implements Initializable {

    private Online onlinemodel;
    private static Timer playerTimer;
    private List<String> playerList;
    private OnlineReversiBoardController ReversiGame;
    private ConfigSenderController configSenderController;


    @FXML
    private TextField opponent;

    @FXML
    private Button challengeButton;

    @FXML
    protected void setScene(String path) {
        Main.setScene(path);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection.getPlayerlist();
        playerList = Interpreter.getPlayerList();
        playerTimer = new Timer();
        playerTimer.scheduleAtFixedRate(new TimerTask() {
             public void run() {
                Connection.getPlayerlist();
            }
        }, 5000, 5000);
    }
    @FXML
    protected void handleMainMenuButton(ActionEvent event){
        setScene("view/main.fxml");
    }

    @FXML
    protected void subscribeMenuButton(ActionEvent event){
        //System.out.println("Subscribe to Reversi is pressed!");
        Connection.subscribe("Reversi");
    }

    @FXML
    protected void acceptChallengeButton(ActionEvent event){
        System.out.println("accept is pressed!");
        Connection.acceptGameChallenge(Interpreter.getGameID());
        if(Interpreter.getGameChallenge()!=null) {
            gameStart();
        }
    }

    @FXML
    protected void handleTictactoeGameButton(){

    }



    @FXML
    protected void handleReversiGameButton(){

    }


    @FXML
    protected void handleChallengeButton(ActionEvent event) throws IOException {
        Window ErrorMessage = challengeButton.getScene().getWindow();

        if(opponent.getText().isEmpty()){
            AlertHelp.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid ign");
            return;
        }
        if(opponent.getText().equals(playerList)){
            AlertHelp.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Player is not online");
            return;
        }

        System.out.println("Sending challenge to: " + opponent.getText());
        Connection.challengePlayer(opponent.getText() , " Reversi");

        gameStart();

    }

    /*
    Game begint en jij bent niet als eerste aan de beurt.
    Dus de kleur is wit.
     */
    public void gameStart(){
        String[] temp = Interpreter.getGameChallenge();
        String whosTurn = temp[4];
        if(whosTurn.equals(opponent) ) {
            System.out.println("Gamestart whosTUrn opponent");
            ReversiGame = new OnlineReversiBoardController(PlayerType.REMOTE, PlayerType.AI,1,2);
            setScene("view/OnlineReversi.fxml");
        } else if(whosTurn.equals(configSenderController.getIgnField())) {
            System.out.println("Gamestart whosTUrn ai");
            ReversiGame = new OnlineReversiBoardController(PlayerType.REMOTE, PlayerType.AI, 2,1);
            setScene("view/OnlineReversi.fxml");
        }

    }
    /*
        Wanneer de server zegt dat het jou beurt is als de game begint.
        De kleur van de ai is dan zwart
     */


   // public void yourGameTurn(){
   //     ReversiGame = new OnlineReversiBoardController(PlayerType.AI, PlayerType.REMOTE, opponent.getText(), 1);
   // }

    public void logoutMenuButton(){
        Connection.logout();
        setScene("view/main.fxml");
    }
/*
    public void availablePlayersView(ActionEvent event){
        System.out.println("Start van availablePlayersView");

        playerList = Interpreter.getPlayerList();
        //Players players;
        Set<Integer> indexes = new HashSet<>();

        for(String player : playerList){
            //players = new Players();
            //int indexPlayer = playerList.indexOf(players);
            if(!player.equals("AiGroep7")){
                    System.out.println(player);
                    playerList.add(player);

                }

            }

        }

 */
}

class AlertHelp {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}