package com.example.scams_ood;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
