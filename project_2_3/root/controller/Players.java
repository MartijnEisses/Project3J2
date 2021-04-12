package root.controller;

import javafx.beans.property.SimpleStringProperty;

public class Players {
    private final SimpleStringProperty name;


    //Voor fxml
    public Players(){
        this("");

    }

    public Players(String name){
        this.name = new SimpleStringProperty(name);
    }

    public String getNameValue() {
        return name.getValue();
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }



}
