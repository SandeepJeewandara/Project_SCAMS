package com.example.scams_ood;

import Database.UserAccountAccess;
import Features.DashboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SignInController {
    @FXML
    public Text passwordMessage;

    @FXML
    public Text usernameMessage;

    @FXML
    private Button exitButton;

    @FXML
    private PasswordField passwordFill;

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameFill;

    //Retrieve data from database to lists and set listeners to user input fields
    @FXML
    private void initialize() {
        List<ClubAdvisor> advisorAccounts = UserAccountAccess.getAdvisorAccounts();
        List<Student> studentsAccount = UserAccountAccess.getStudentsAccount();

        System.out.println(advisorAccounts);
        System.out.println(studentsAccount);

        passwordMessage.setVisible(false);
        usernameMessage.setVisible(false);

        usernameFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignIn());
        passwordFill.textProperty().addListener((observable, oldValue, newValue) -> enableSignIn());
    }

    //Enable sign in button after all fields are filled
    @FXML
    private void enableSignIn() {
        boolean usernameFilled = !usernameFill.getText().trim().isEmpty();
        boolean passwordFilled = !passwordFill.getText().trim().isEmpty();
        signInButton.setDisable(!(usernameFilled && passwordFilled));
    }

    //Styles for exit button when mouse pressed
    @FXML
    public void exitPress() {
        exitButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    //Styles for exit button when mouse release
    @FXML
    public void exitRelease() {
        System.exit(0);
        exitButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
    }

    //Styles for sign in button when mouse pressed
    @FXML
    public void signInPress() {
        signInButton.setStyle("-fx-background-color: #690260;" + "-fx-background-radius: 40");
    }

    //Redirect to Sign Up scene if user not registered
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

    //
    private void openDashboard(Object user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setUser(user);

            Scene dashboardScene = new Scene(root);
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.setScene(dashboardScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Check for username and password if it's available in the list and set messages to visible if user inputs are incorrect
    @FXML
    public void signInRelease() {
        signInButton.setStyle("-fx-background-color: #813EB6;" + "-fx-background-radius: 40");

        List<ClubAdvisor> advisorAccounts = UserAccountAccess.getAdvisorAccounts();
        List<Student> studentsAccount = UserAccountAccess.getStudentsAccount();

        String username = usernameFill.getText();
        String password = passwordFill.getText();

        passwordMessage.setVisible(false);
        usernameMessage.setVisible(false);

        for (ClubAdvisor advisor : advisorAccounts) {
            if (advisor.getUsername().equals(username)) {
                if (advisor.getPassword().equals(password)) {
                    openDashboard(advisor);
                    return;
                } else {
                    passwordMessage.setVisible(true);
                    return;
                }
            }
        }

        for (Student student : studentsAccount) {
            if (student.getUsername().equals(username)) {
                if (student.getPassword().equals(password)) {
                    openDashboard(student);
                    return;
                } else {
                    passwordMessage.setVisible(true);
                    return;
                }
            }
        }
        usernameMessage.setVisible(true);
    }

    //If user input fields are empty messages are set to not visible
    @FXML
    public void hiddenError() {
        if (usernameFill.getText().isEmpty()) {
            usernameMessage.setVisible(false);
        }
        if (passwordFill.getText().isEmpty()) {
            passwordMessage.setVisible(false);
        }
    }
}
