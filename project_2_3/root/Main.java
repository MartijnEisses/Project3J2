package root;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        primaryStage.setTitle("Project 2.3");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();


        ComboBox cb = (ComboBox) primaryStage.getScene().lookup("#combobox_select");
        cb.getSelectionModel().select(0);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
