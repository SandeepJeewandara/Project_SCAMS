package com.example.scams_ood;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WelcomeBanner.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 700);
        stage.setTitle("Welcome Banner");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // Create a timeline to wait for 5 seconds
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(3),
                event -> loadLoginOrSignUpPane(stage)
        ));
        timeline.play();
    }

    private void loadLoginOrSignUpPane(Stage stage) {
        try {
            FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("Sign_in.fxml"));

            Scene loginScene = new Scene(loginLoader.load(), 1150, 700);
            stage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
