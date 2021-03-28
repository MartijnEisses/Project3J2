package root;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import root.controller.MainController;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        primaryStage.setTitle("Project 2.3");
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();


        ComboBox cb = (ComboBox) primaryStage.getScene().lookup("#combobox_select");
        cb.getSelectionModel().select(0);

        MainController cm = new MainController();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
