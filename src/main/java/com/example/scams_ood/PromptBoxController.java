package com.example.scams_ood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class PromptBoxController {
    @FXML
    public Button okButton;
    public Label promptText;

    @FXML
    public void okPress() {
        okButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void okRelease() {
        okButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
    }

    public void onOkButtonClick(ActionEvent event) {
    }
}
