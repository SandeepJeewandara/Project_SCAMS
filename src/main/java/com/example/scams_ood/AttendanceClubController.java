package com.example.scams_ood;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceClubController {

    @FXML
    private GridPane clubGrid;

    @FXML
    private ScrollPane clubScroll;

    private List<Club> retrieveDataFromDatabase() throws SQLException {
        List<Club> clubs = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db","root","");
            Statement statement = connection.createStatement()) {


            String query = "SELECT Club_name, Club_logo_path FROM club";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Club club = new Club();
                club.setClubName(resultSet.getString("Club_name"));
                club.setImageLogoPath(resultSet.getString("Club_logo_path"));
                System.out.println(club.getClubName());
                System.out.println(club.getImageLogoPath());
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return clubs;

    }


    }







