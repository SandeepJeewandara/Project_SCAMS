package com.example.scams_ood;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PromptBoxController {
    @FXML
    public Button okButton;

    @FXML
    public AnchorPane promptBoxPain;

    @FXML
    public Label promptText;

    public void setPromptText(String  message) {
        promptText.setText(message);
    }

    public void showPromptMessage(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PromptBox.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        PromptBoxController controller = loader.getController();
        controller.setPromptText(message);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void okPress() {
        okButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void okRelease(MouseEvent event) {
        okButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");

        Stage stage = (Stage) ((javafx.scene.control.Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
