package com.example.scams_ood;

import Features.DatabaseConnectionTest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Welcome to JavaFX Application!");
        DatabaseConnectionTest.getConnection();
    }


}