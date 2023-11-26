package com.example.scams_ood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button addActivityButton;

    @FXML
    private Button addEventButton;

    @FXML
    private Button addMeetingButton;


    public void setAddEventButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
