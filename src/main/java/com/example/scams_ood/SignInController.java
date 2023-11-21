package com.example.scams_ood;

import Features.DatabaseConnectionTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignInController {

    @FXML
    private Button exitButton;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private PasswordField passwordFill;

    @FXML
    private Button signInButton;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private TextField usernameFill;

    @FXML
    public void exitPress(MouseEvent event) {
        exitButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void exitRelease(MouseEvent event) {
        System.exit(0);
        exitButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
    }

    @FXML
    public void signInPress(MouseEvent event) {
        signInButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void signInRelease(MouseEvent event) {
        signInButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
    }

    @FXML
    private void initialize() {
        usernameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignIn());
        passwordFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignIn());
    }

    private void enableSignIn() {
        boolean usernameFilled = !usernameFill.getText().trim().isEmpty();
        boolean passwordFilled = !passwordFill.getText().trim().isEmpty();
        signInButton.setDisable(!(usernameFilled && passwordFilled));
    }

    @FXML
    public void signUpRelease(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up.fxml"));
            Parent root = loader.load();
            Scene anotherScene = new Scene(root);
            Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            stage.setScene(anotherScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
