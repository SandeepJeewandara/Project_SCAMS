package Features;
//import packages and classes

import Database.DataAccess;
import com.example.scams_ood.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceEventController { //Controller Class for handling attendance events

    //FXML elements for interactions with controller class and FXML files
    @FXML
    private TextField clubIdTextField;

    @FXML
    private GridPane eventGrid;


    private boolean isValidClubId(String clubId) { //Method to check the validation of the entered clubId
        if (clubId.length() != 4) {
            return false;
        }
        if (!clubId.substring(0, 1).equals("C") || !clubId.substring(1).matches(("\\d{3}"))) {
            return false;
        }
        return true;
    }


    public void displayClubEvent(ActionEvent event) throws IOException { //Method to display events related with clubs



        String clubId = clubIdTextField.getText(); //Get the club id from the text field

        if (isValidClubId(clubId)) { //Check if the club id is valid

            //Get a list of all events from the database
            List<Event> retrievedClubs = DataAccess.getEvents();
            List<Event> eventsAssignToClubs = new ArrayList<>();

            for (Event events : retrievedClubs) { //Iteration to find the events that associated to specific club
                if (events.getClubID().getClubId().equals(clubId)) {
                    eventsAssignToClubs.add(events);
                }
            }

            int column = 0;
            int row = 1;

            try {
                for (int i = 0; i < eventsAssignToClubs.size(); i++) { //Loop through the events and load their FXML
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/example/scams_ood/event.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    //Set the data for event using the EventController
                    EventController eventController = fxmlLoader.getController();
                    eventController.eventIconSetData(eventsAssignToClubs.get(i));

                    //Adjust row and column for layout
                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    eventGrid.add(anchorPane, column++, row);
                    //Set width
                    eventGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    eventGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    eventGrid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set height
                    eventGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    eventGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    eventGrid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));

                    //retrievedClubs.clear();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else { // Display a prompt message if the club id is invalid
        }
    }


}








