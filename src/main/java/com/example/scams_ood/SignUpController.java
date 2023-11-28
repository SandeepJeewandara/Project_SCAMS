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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Text passwordMessage;

    @FXML
    private Text idMessage;

    @FXML
    private ToggleGroup genderGroup;

    //Limit input characters for text fields and set listeners to user input fields
    @FXML
    private void initialize() {
        addTextLimiter(idFill, 4);
        addTextLimiter(dateOfBirthPicker.getEditor(), 10);
        addTextLimiter(firstNameFill, 100);
        addTextLimiter(LastNameFill, 100);
        addTextLimiter(emailFill, 150);
        addTextLimiter(usernameFill, 50);
        addTextLimiter(passwordFill,50);

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

    //Enable sign up button after all fields are filled
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

    //When user select one of the toggle buttons all the text fields are enabled
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

    //Method to clear all text fields
    private void clearAll() {
        firstNameFill.clear();
        LastNameFill.clear();
        genderGroup.selectToggle(null);
        usernameFill.clear();
        idFill.clear();
        dateOfBirthPicker.getEditor().clear();
        emailFill.clear();
        passwordFill.clear();
        reEnterPasswordFill.clear();
    }

    //Advisor button mouse event for mouse press
    @FXML
    public void advisorPress() {
        advisorToggle.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
        studentToggle.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
        idText.setText("Staff ID:");
        typeMessage.setVisible(false);
        idFill.setPromptText("Ex: A123");
        clearAll();
    }

    //Student button mouse event for mouse press
    @FXML
    public void studentPress() {
        studentToggle.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
        advisorToggle.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
        idText.setText("Student ID:");
        typeMessage.setVisible(false);
        idFill.setPromptText("Ex: S123");
        clearAll();
    }

    //Validate User ID field to only take A or S and numbers
    @FXML
    public void validTyped() {
        if (advisorToggle.isSelected()) {
            validInput(idFill, "[A][0-9]*");
        }
        else if (studentToggle.isSelected()) {
            validInput(idFill, "[S][0-9]*");
        }
    }

    //Redirect to Sign In scene if user already registered
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

    //Styles for cancel button when mouse pressed
    @FXML
    public void cancelPress() {
        cancelButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }


    //Styles for cancel button when mouse released and redirect to Sign In scene
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

    //Validation for emails
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Method to check the values are available in database
    private boolean isAvailable(String check, String table, String column) {
        try {
            Connection connection = DatabaseConnectionTest.getConnection();
            if (connection == null) {
                System.err.println("Database connection is null.");
            }

            String query = "SELECT * FROM " + table +" WHERE "+ column +" = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, check);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Call isAvailable method to check in database and show message if ID is available
    @FXML
    public void idExit() {
        String id = idFill.getText();

        if (idFill.getText().isEmpty()) {
            idMessage.setVisible(false);
        }
        else if (isAvailable(id,"club_advisor", "AdvisorID")) {
            idMessage.setVisible(true);
        }
        else if (isAvailable(id,"student", "StudentID")) {
            idMessage.setVisible(true);
        }
    }

    //Call isAvailable method to check in database and show message if ID is available
    @FXML
    public void emailExit() {
        String email = emailFill.getText();

        if (email.isEmpty()) {
            emailMessage.setVisible(false);
        }
        else if (!(isValidEmail(email))) {
            emailMessage.setText("*Email not valid");
            emailMessage.setVisible(true);
        }
        else if (isAvailable(email,"club_advisor", "Email")) {
            emailMessage.setVisible(true);
        }
        else if (isAvailable(email,"student", "Email")) {
            emailMessage.setVisible(true);
        }
    }

    //If fields are empty visible messages are set to false
    @FXML
    public void hiddenError() {
        if (idFill.getText().isEmpty()) {
            idMessage.setVisible(false);
        }
        if (reEnterPasswordFill.getText().isEmpty()) {
            passwordMessage.setVisible(false);
        }
        if (emailFill.getText().isEmpty()) {
            emailMessage.setVisible(false);
        }
    }

    //Check for both password fields to verify password is same in both
    @FXML
    public void passwordExit() {
        if (reEnterPasswordFill.getText().isEmpty()) {
            passwordMessage.setVisible(false);
        }
        else passwordMessage.setVisible(!(Objects.equals(passwordFill.getText(), reEnterPasswordFill.getText())));
    }

    //If messages are visible Sign Up button is set to disable
    private void disableSignUp() {
        boolean idMessageVisible = idMessage.isVisible();
        boolean emailMessageVisible = emailMessage.isVisible();
        boolean passwordMessageVisible = passwordMessage.isVisible();

        signUpButton.setDisable(idMessageVisible || emailMessageVisible || passwordMessageVisible);
    }

    //Disable sign up method is called when mouse entered to sign up button
    @FXML
    public void signUpEnter() {
        disableSignUp();
    }

    //Styles for Sign Up button when mouse pressed
    @FXML
    public void signUpPress() {
        signUpButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    //User details are inserted into respective tables and prompt box will appear when mouse released
    @FXML
    public void signUpRelease() throws IOException {
        Toggle selectedToggle = genderGroup.getSelectedToggle();
        RadioButton selectedRadioButton = (RadioButton) selectedToggle;

        PromptBoxController promptBoxController = new PromptBoxController();
        promptBoxController.showPromptMessage("Successfully Signed Up!");

        StoreUserData storeUserData = new StoreUserData();

        signUpButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");


        if (advisorToggle.isSelected()) {
            storeUserData.setUserType("club_advisor", "AdvisorID", "Username", idFill, firstNameFill, LastNameFill, selectedRadioButton,
                    emailFill, dateOfBirthPicker, usernameFill, passwordFill);
        }
        else if (studentToggle.isSelected()) {
            storeUserData.setUserType("student", "StudentID", "User_name", idFill, firstNameFill, LastNameFill, selectedRadioButton,
                    emailFill, dateOfBirthPicker, usernameFill, passwordFill);
        }
        clearAll();
    }
}
