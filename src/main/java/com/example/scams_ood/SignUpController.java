package com.example.scams_ood;

import Database.DatabaseConnectionTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.scams_ood.Validations.addTextLimiter;
import static com.example.scams_ood.Validations.validInput;

public class SignUpController {

    @FXML
    private TextField LastNameFill;

    @FXML
    private ToggleButton advisorToggle;

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
    private ToggleButton studentToggle;

    @FXML
    private TextField idFill;

    @FXML
    private TextField usernameFill;

    @FXML
    private Text typeMessage;

    @FXML
    private Text emailMessage;

    @FXML
    private ToggleGroup userType;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private void initialize() {

        addTextLimiter(idFill, 4);
        addTextLimiter(dateOfBirthPicker.getEditor(), 10);

        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        genderGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        firstNameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        LastNameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        usernameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        idFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        dateOfBirthPicker.editorProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        emailFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        passwordFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
        reEnterPasswordFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignUp());
    }

    @FXML
    void validTyped() {
        validInput(idFill, "[SA0-9]*");
        validInput(emailFill, "[a-z0-9@._%-+]*");
    }

    private void enableSignUp() {
        boolean firstnameFilled = !firstNameFill.getText().trim().isEmpty();
        boolean lastnameFilled = !LastNameFill.getText().trim().isEmpty();
        boolean usernameFilled = !LastNameFill.getText().trim().isEmpty();
        boolean genderSelected = !(genderGroup.getSelectedToggle() == null);
        boolean idFilled = !idFill.getText().trim().isEmpty();
        boolean dobFilled = !dateOfBirthPicker.getEditor().getText().isEmpty();
        boolean emailFilled = !emailFill.getText().trim().isEmpty();
        boolean passwordFilled = !passwordFill.getText().trim().isEmpty();
        boolean reEnterPasswordFilled = !reEnterPasswordFill.getText().trim().isEmpty();

        signUpButton.setDisable(!(firstnameFilled && lastnameFilled && usernameFilled && genderSelected && idFilled &&
                dobFilled && emailFilled && passwordFilled && reEnterPasswordFilled));
    }

    @FXML
    private void typeSelect() {
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
    public void advisorPress() {
        advisorToggle.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
        studentToggle.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
        idText.setText("Staff ID:");
        typeMessage.setVisible(false);
        idFill.setPromptText("Ex: A123");
    }

    @FXML
    public void studentPress() {
        studentToggle.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
        advisorToggle.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
        idText.setText("Student ID:");
        typeMessage.setVisible(false);
        idFill.setPromptText("Ex: S123");
    }

    @FXML
    public void signInRelease(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign_in.fxml"));
            Parent root = loader.load();
            Scene anotherScene = new Scene(root);
            Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            stage.setScene(anotherScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelPress() {
        cancelButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void cancelRelease() {
        cancelButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign_in.fxml"));
            Parent root = loader.load();
            Scene anotherScene = new Scene(root);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(anotherScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUpRelease() throws IOException {


        Toggle selectedToggle = genderGroup.getSelectedToggle();
        RadioButton selectedRadioButton = (RadioButton) selectedToggle;

        PromptBoxController promptBoxController = new PromptBoxController();
        promptBoxController.showPromptMessage("Successfully Signed Up!");

        StoreUserData storeUserData = new StoreUserData();

        if (advisorToggle.isSelected()) {
            storeUserData.setUserType("club_advisor", "AdvisorID", "Username", idFill, firstNameFill, LastNameFill, selectedRadioButton,
                    emailFill, dateOfBirthPicker, usernameFill, passwordFill);
        }
        else if (studentToggle.isSelected()) {
            storeUserData.setUserType("student", "StudentID", "User_name", idFill, firstNameFill, LastNameFill, selectedRadioButton,
                    emailFill, dateOfBirthPicker, usernameFill, passwordFill);
        }
    }
}
