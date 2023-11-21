package com.example.scams_ood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField LastNameFill;

    @FXML
    private Button advisorButton;

    @FXML
    private Button cancelButton;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private TextField emailFill;

    @FXML
    private TextField firstNameFill;

    @FXML
    private Text idText;

    @FXML
    private PasswordField passwordFill;

    @FXML
    private PasswordField reEnterPasswordFill;

    @FXML
    private Button signUpButton;

    @FXML
    private Button studentButton;

    @FXML
    private TextField idFill;

    @FXML
    private TextField usernameFill;

    @FXML
    private Text typeMessage;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private void initialize() {
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        genderGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
        });

        firstNameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        LastNameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        usernameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        idFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        dateOfBirthPicker.editorProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        emailFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        passwordFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        reEnterPasswordFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
    }

    private void enableSignUp() {
        boolean firstnameFilled = !firstNameFill.getText().trim().isEmpty();
        boolean lastnameFilled = !LastNameFill.getText().trim().isEmpty();
        boolean usernameFilled = !LastNameFill.getText().trim().isEmpty();
        boolean idFilled = !idFill.getText().trim().isEmpty();
        boolean dobFilled = !dateOfBirthPicker.getEditor().getText().isEmpty();
        boolean emailFilled = !emailFill.getText().trim().isEmpty();
        boolean passwordFilled = !passwordFill.getText().trim().isEmpty();
        boolean reEnterPasswordFilled = !reEnterPasswordFill.getText().trim().isEmpty();
        signUpButton.setDisable(!(firstnameFilled && lastnameFilled && usernameFilled && idFilled &&
                dobFilled && emailFilled && passwordFilled && reEnterPasswordFilled));
    }

    @FXML
    private void typeSelect(ActionEvent event) {
        firstNameFill.setDisable(false);
        LastNameFill.setDisable(false);
        maleRadio.setDisable(false);
        femaleRadio.setDisable(false);
        usernameFill.setDisable(false);
        idFill.setDisable(false);
        dateOfBirthPicker.setDisable(false);
        emailFill.setDisable(false);
        passwordFill.setDisable(false);
        reEnterPasswordFill.setDisable(false);
    }

    @FXML
    public void advisorPress(MouseEvent event) {
        advisorButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
        studentButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
        idText.setText("Staff ID:");
        typeMessage.setVisible(false);
    }

    @FXML
    public void studentPress(MouseEvent event) {
        studentButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
        advisorButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
        idText.setText("Student ID:");
        typeMessage.setVisible(false);
    }

    @FXML
    public void signInRelease(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-in.fxml"));
            Parent root = loader.load();
            Scene anotherScene = new Scene(root);
            Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            stage.setScene(anotherScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelPress(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void cancelRelease(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-in.fxml"));
            Parent root = loader.load();
            Scene anotherScene = new Scene(root);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(anotherScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}