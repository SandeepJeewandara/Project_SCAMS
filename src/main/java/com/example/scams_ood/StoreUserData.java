package com.example.scams_ood;

import Database.DatabaseConnectionTest;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreUserData {
    public void setUserType(String name, String id, String userName, TextField field1, TextField field2,
                            TextField field3, RadioButton radio4, TextField field5,
                            DatePicker picker6, TextField field7, TextField field8) {
        try {
            Connection connection = DatabaseConnectionTest.getConnection();
            if (connection == null) {
                System.err.println("Database connection is null.");
                return;
            }

            String sql = "INSERT INTO " + name + "("+ id +", First_name, Last_name, Gender, Email, DOB, "+ userName +", Password)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, field1.getText());
                    preparedStatement.setString(2, field2.getText());
                    preparedStatement.setString(3, field3.getText());
                    preparedStatement.setString(4, radio4.getText());
                    preparedStatement.setString(5, field5.getText());
                    preparedStatement.setDate(6, java.sql.Date.valueOf(picker6.getValue()));
                    preparedStatement.setString(7, field7.getText());
                    preparedStatement.setString(8, field8.getText());

                    preparedStatement.executeUpdate();

                    System.out.println("User details inserted into the database successfully!");
                }
            } catch (SQLException e) {
                System.err.println("Error inserting user details into the database: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
