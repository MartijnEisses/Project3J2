package root.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import root.Main;

import java.io.IOException;

public abstract class Controller implements Initializable {

    protected void setScene(String path) {
        Main.setScene(path);
    }

}
