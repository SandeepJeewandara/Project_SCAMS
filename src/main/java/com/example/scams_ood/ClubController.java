package com.example.scams_ood;

import Features.DatabaseConnectionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClubController {


    @FXML
    private Button clubButton;

    @FXML
    private ImageView clubImage;

    @FXML
    private Label clubName;

    @FXML
    private Scene scene;

    @FXML
    private Label clubId;

    private Club club;

    public void clubIconSetData(Club club){
        this.club = club;
        clubName.setText(club.getClubName());
        clubId.setText((club.getClubId()));

        //Image image = new Image(getClass().getResourceAsStream(club.getImageLogoPath()));
        //clubImage.setImage(image);

    }







    /*

    public void switchToAttendance(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/scams_ood/AttendanceTracking.fxml"));

        AttendanceTrackingController attendanceController = loader.getController();
        attendanceController.setClubId(club.getClubId());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("SCAMS - Attendance Tracking");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     */
    public void switchToAttendance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/AttendanceTracking.fxml"));
        Parent root = loader.load();

        AttendanceTrackingController attendanceController = loader.getController();
        attendanceController.setClubId(club.getClubId());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SCAMS - Attendance Tracking");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}



