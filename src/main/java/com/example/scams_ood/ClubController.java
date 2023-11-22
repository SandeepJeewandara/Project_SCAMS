package com.example.scams_ood;

import Features.DatabaseConnectionTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


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

    private Club club;

    public void clubIconSetData(Club club){
        this.club = club;
        clubName.setText(club.getClubName());
        //Image image = new Image(getClass().getResourceAsStream(club.getImageLogoPath()));
        //clubImage.setImage(image);

    }



}



