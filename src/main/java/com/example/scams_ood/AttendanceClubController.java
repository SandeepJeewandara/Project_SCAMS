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

public class AttendanceClubController implements Initializable {

    @FXML
    private GridPane clubGrid;

    @FXML
    private ScrollPane clubScroll;

    @FXML
    private Button backButton;

    @FXML
    private Scene scene;

    //private List<Club> clubs = new ArrayList<>();

    private List<Club> retrieveDataFromDatabase() {
         List<Club> clubs = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db","root","");
            Statement statement = connection.createStatement()) {


            String query = "SELECT Club_name FROM club";
            //SELECT Club_name, Club_logo_path FROM club
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Club club = new Club();
                club.setClubName(resultSet.getString("Club_name"));
                //club.setImageLogoPath(resultSet.getString("Club_logo_path"));
                clubs.add(club);
                System.out.println(club.getClubName());
                System.out.println(club.getImageLogoPath());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return clubs;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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








