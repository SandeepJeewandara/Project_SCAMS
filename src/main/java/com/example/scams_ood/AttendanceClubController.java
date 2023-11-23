package com.example.scams_ood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceClubController  {

    @FXML
    private Button advisorSubmitButton;
    @FXML
    private  TextField advisorIdTextField;

    @FXML
    private GridPane clubGrid;

    @FXML
    private ScrollPane clubScroll;

    @FXML
    private Button backButton;

    @FXML
    private Scene scene;

    //private List<Club> clubs = new ArrayList<>();

    public List<Club> retrieveDataFromDatabase() {
         List<Club> clubsList = new ArrayList<>();
         String advisorId = advisorIdTextField.getText();

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db","root","")){


            String query = "SELECT Club.ClubID, Club.Club_name,Club.Club_logo_path FROM Club_Advisor JOIN Club  ON Club_Advisor.AdvisorID = Club.AdvisorID WHERE Club_Advisor.AdvisorID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);
                //SELECT Club_name, Club_logo_path FROM club
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Club club = new Club();
                        club.setClubId(resultSet.getString("ClubID"));
                        club.setClubName(resultSet.getString("Club_name"));
                        //club.setImageLogoPath(resultSet.getString("Club_logo_path"));
                        clubsList.add(club);
                        System.out.println(club.getClubName());
                        System.out.println(club.getImageLogoPath());
                    }
                }
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return clubsList;
    }
    public void displayAdvisorClub(ActionEvent event) {
        List <Club> retrievedClubs = retrieveDataFromDatabase();

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < retrievedClubs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/scams_ood/club.fxml"));
                //main/resources/com/example/scams_ood
                AnchorPane anchorPane = fxmlLoader.load();


                ClubController clubController = fxmlLoader.getController();
                clubController.clubIconSetData(retrievedClubs.get(i));

                if(column == 3){
                    column =0;
                    row ++;
                }

                clubGrid.add(anchorPane,column++, row);
                //Set width
                clubGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                clubGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                clubGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set height
                clubGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                clubGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                clubGrid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    public void backToMain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Dash Board");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }

     */




    }








