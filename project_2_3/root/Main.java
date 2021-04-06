package root;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import root.model.TicTacToe;
import javax.swing.*;
import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;

    @FXML
    public static Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/main.fxml"));
        root = loader.load();

        primaryStage.setTitle("project 2.3");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(String path) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(path));
        try {
            root = loader.load();
            primaryStage.setScene(new Scene(root, 1280, 720));
            primaryStage.show();
            primaryStage.getScene().setOnKeyPressed(e -> goToMain(e.getCode()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void goToMain(KeyCode code) {
        if (code.equals(KeyCode.ESCAPE)) {
            int dialogButton = 0;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to exit this game?", "Warning",
                    dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                setScene("view/main.fxml");
            }
        }
    }
}
