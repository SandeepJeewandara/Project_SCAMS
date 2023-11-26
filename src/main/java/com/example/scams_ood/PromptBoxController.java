package com.example.scams_ood;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class PromptBoxController {
    @FXML
    public Button okButton;

    @FXML
    public void okPress() {
        okButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void okRelease() {
        okButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
    }

}
