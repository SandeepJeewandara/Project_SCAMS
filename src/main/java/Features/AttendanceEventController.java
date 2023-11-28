package Features;


import Database.DataAccess;
import com.example.scams_ood.Club;

import com.example.scams_ood.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceEventController {

    @FXML
    private Button backButton;

    @FXML
    private TextField clubIdTextField;

    @FXML
    private Button clubSubmitButton;

    @FXML
    private GridPane eventGrid;

    @FXML
    private ScrollPane eventScroll;

    public void displayClubEvent(ActionEvent event) {
        PromptController promptController = new PromptController();

        String clubId = clubIdTextField.getText();
        if(clubId == null || )
        System.out.println(clubId);

        List <Event> retrievedClubs = DataAccess.getEvents();
        List<Event> eventsAssignToClubs =new ArrayList<>();

        for(Event events : retrievedClubs){
            System.out.println(events.getClubID().getClubId());
            if(events.getClubID().getClubId().equals(clubId)){
                System.out.println(events.getClubID());
                eventsAssignToClubs.add(events);
            }
        }

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < eventsAssignToClubs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/scams_ood/event.fxml"));
                //main/resources/com/example/scams_ood
                AnchorPane anchorPane = fxmlLoader.load();


                EventController eventController = fxmlLoader.getController();
                eventController.eventIconSetData(eventsAssignToClubs.get(i));

                if(column == 3){
                    column =0;
                    row ++;
                }

                eventGrid.add(anchorPane,column++, row);
                //Set width
                eventGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                eventGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                eventGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set height
                eventGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                eventGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                eventGrid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));

                retrievedClubs.clear();
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








