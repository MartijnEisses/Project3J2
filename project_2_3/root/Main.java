package root;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import root.model.Reversi;
import root.server.Connection;
import root.controller.OnlineReversiBoardController;
import root.server.Interpreter;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {
public static FXMLLoader loader;
    public static Stage primaryStage;
    public static Connection newConnection;



    @FXML
    public static Parent root;
    public static Reversi controller;
    public static Interpreter interpreter;

    @Override
    public void start(Stage primaryStage) throws Exception {

        interpreter = new Interpreter();

        Main.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/main.fxml"));
        root = loader.load();
        primaryStage.setTitle("project 2.3");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

    }

    public static void main(String[] args) throws IOException {

        newConnection = new Connection();
        launch(args);
    }

    public static void setScene(String path) {
        loader = new FXMLLoader();
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
    public Stage getPrimaryStage() {
        return this.primaryStage;
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
