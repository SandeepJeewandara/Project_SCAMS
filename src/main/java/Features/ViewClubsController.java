package Features;

import Database.DataAccess;
import com.example.scams_ood.Club;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.List;

public class ViewClubsController {

    @FXML
    private Label clubAdvisorLabel;
    @FXML
    private Label clubDescriptionLabel;
    @FXML
    private Label clubIDLabel;
    @FXML
    private ImageView clubLogoView;
    @FXML
    private Label clubNameLabel;
    @FXML
    private Label clubTypeLabel;
    @FXML
    private GridPane eventGrid;
    @FXML
    private ScrollPane eventScroll;
    @FXML
    private Label startedDateLabel;


    //Initialize method for Preview Club Details in Separate Panes
    @FXML
    private void initialize(){
        displayClubs();
    }



    // Method to display club previews in the grid
    private void displayClubs(){
        int column = 0;
        int row = 1;

        List<Club> retrievedClubs = DataAccess.getClubs();
        try {
            for (int i = 0; i <retrievedClubs.size(); i++) {
                // Load the ClubPreview.fxml file for each club
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/scams_ood/ClubPreview.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                // Get the Controller for the ClubPreview.fxml File
                ClubPreviewController clubPreviewController = fxmlLoader.getController();
                clubPreviewController.clubIconSetData(retrievedClubs.get(i));
                clubPreviewController.setViewClubsController(this);

                // Place the Club Preview in The Grid
                if(column == 2){
                    column =0;
                    row ++;
                }

                eventGrid.add(anchorPane,column++, row);
                eventGrid.setVgap(1);

                //Set width
                eventGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                eventGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                eventGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set height
                eventGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                eventGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                eventGrid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    // Method to show details of the selected club
    public void showSelectedClubDetails(Club selectedClub) {

        // Update the UI with the selected club details
        clubIDLabel.setText(selectedClub.getClubId());
        clubNameLabel.setText(selectedClub.getClubName());
        clubTypeLabel.setText(selectedClub.getClubType());
        startedDateLabel.setText(selectedClub.getStartedDate().toString());
        clubAdvisorLabel.setText(selectedClub.getClubAdvisor().getName());
        clubDescriptionLabel.setText(selectedClub.getClubDescription());

        // Load the Club Logo if Available
        if (selectedClub.getClubLogoPath() != null && !selectedClub.getClubLogoPath().isEmpty()) {
            String imagePath = "/Images/ClubLogos/" + selectedClub.getClubLogoPath();
            clubLogoView.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        } else {
            // Set a Default Image
            clubLogoView.setImage(new Image(getClass().getResource("Images/Logo/logo_4.png").toExternalForm()));
        }
    }
}


