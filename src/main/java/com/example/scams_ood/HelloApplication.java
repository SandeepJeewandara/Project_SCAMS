package com.example.scams_ood;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primarystage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/scams_ood/attendance-club.fxml"));
        primarystage.setTitle("SCAMS - Attendance Tracking");
        //stage.setTitle("SCAMS - Event Scheduling");
        primarystage.setScene(new Scene(root));
        //stage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}