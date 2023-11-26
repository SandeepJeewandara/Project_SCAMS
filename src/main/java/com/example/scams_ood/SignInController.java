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
import java.sql.*;

import static Features.DatabaseConnectionTest.getConnection;

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
    public void exitPress() {
        exitButton.setStyle("-fx-background-color: #690260;"+"-fx-background-radius: 40");
    }

    @FXML
    public void exitRelease() {
        System.exit(0);
        exitButton.setStyle("-fx-background-color: #813EB6;"+"-fx-background-radius: 40");
    }

    @FXML
    public void signInPress() {
        signInButton.setStyle("-fx-background-color: #690260;" + "-fx-background-radius: 40");
    }

    @FXML
    public void signInRelease() {
        String username = usernameFill.getText();
        String password = passwordFill.getText();

        signInButton.setStyle("-fx-background-color: #813EB6;" + "-fx-background-radius: 40");

        if (authenticateUser(username, password)) {
            // User authentication successful, proceed with your logic (e.g., open a new window)
            System.out.println("Sign in successful!");
        } else {
            // Authentication failed, show an error message or perform other actions
            System.out.println("Sign in failed!");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Connect to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "")) {

            // Prepare the SQL query
            String query = "SELECT * FROM students WHERE username = ? AND password = ? " +
                    "UNION " +
                    "SELECT * FROM advisors WHERE username = ? AND password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set parameters for the query
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if the result set has any rows
                    return resultSet.next();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or query errors
        }

        return false;
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
