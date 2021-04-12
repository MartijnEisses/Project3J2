package root.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import root.Main;
import root.model.Online;

import java.net.URL;
import java.util.*;

import root.server.Connection;
import root.server.Interpreter;


public class OnlineController extends Online implements Initializable {

    private Online onlinemodel;
    private static Timer playerTimer;
    private List<Players> playerList;

    @FXML
    public TableView<Players> availablePlayersView;

    //@FXML
    //public TableView<Players, String> name;

    @FXML
    protected void setScene(String path) {
        Main.setScene(path);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection.getPlayerlist();

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
    }

    /*
    Method to display all online players.
     */
    public void challengersView(){


    }


    public void availablePlayersView(List<String> list){

        Players players;
        Set<Integer> indexes = new HashSet<>();

        for(String player : list){

            players = new Players();
            int indexPlayer = playerList.indexOf(players);
            if(!player.equals("AiGroep7")){
                if(indexPlayer == -1){
                    System.out.println(player);
                    playerList.add(new Players(player));

                } else{
                    indexes.add(indexPlayer);
                }
            }

        }
    }



    public void logoutMenuButton(){
        Connection.logout();
        setScene("view/main.fxml");
    }

    @FXML
    private void acceptGameChallenge(){
       // try{
        //    Connection.acceptGameChallenge(Hier moet de gameID nog in van de table);
       // }
    }




}
