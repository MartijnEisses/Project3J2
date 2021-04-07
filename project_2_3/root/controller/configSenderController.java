package root.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import root.Main;
import root.server.configSender;
import root.server.Observers;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.stage.Window;
/*
    Method to put in the ip and port and name of player to connect to the server.
    @Author Jelmer de Haan.
 */
public class configSenderController extends configSender implements Initializable {



    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    protected void setScene(String path) {
        Main.setScene(path);
    }
    @FXML
    private TextField ignField;

    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    @FXML
    private Button submitButton;



    @FXML
    protected void handleSubmitButton(ActionEvent event) throws IOException {
        Window ErrorMessage = submitButton.getScene().getWindow();
        //Check if the textfields are empty
        if(ignField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid ign");
            return;
        }
        if(ipField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid IP");
            return;
        }
        if(!portField.getText().matches("^[0-9]*$")){
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid port");
            return;
        }
       // AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, ErrorMessage, "Correct registration!", "Goodluck and may you WIN! " + ignField.getText());
        System.out.println("Connected to: " + ipField.getText() + " on port : " + portField.getText());

        if(Main.newConnection.connect(ipField.getText(), Integer.parseInt(portField.getText()))){
           // Main.newConnection.addObserver();
            Main.newConnection.login(ignField.getText());
        }
         else if(Main.newConnection.connect(ipField.getText(), Integer.parseInt(portField.getText())))
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage,"Connection Failure", "Ip: " + ipField.getText() + " port: " + "is refused please check the IP and port again!");
        return;
    }

    public void loginReply(String responseMessage){

        if(responseMessage.contains("ok ")){


        }
    }




}

class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}