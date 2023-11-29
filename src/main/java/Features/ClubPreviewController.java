package Features;

import com.example.scams_ood.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClubPreviewController {
    @FXML
    public ImageView clubImagePreview;
    @FXML
    private Button selectButton;
    @FXML
    private Label clubName;

    //In Class Variables
    private Club club;
    private ViewClubsController viewClubsController;


    //Sets the Data of the Club Icon in the Preview.
    public void clubIconSetData(Club club){
        this.club = club;
        clubName.setText(club.getClubName());

        // Set the club logo image if available
        if (club.getClubLogoPath() != null && !club.getClubLogoPath().isEmpty()) {
            String imagePath = "/Images/ClubLogos/" + club.getClubLogoPath();
            try {
                clubImagePreview.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
            } catch (Exception e) {
                clubImagePreview.setImage(null);
            }

        }
    }

    // Sets the ViewClubsController Associated with This Preview.
    public void setViewClubsController(ViewClubsController viewClubsController) {
        this.viewClubsController = viewClubsController;
    }

   //Notifies the ViewClubsController to Show Details of the Selected Club.
    public void onSelectButtonClick(ActionEvent event) {
        if (viewClubsController != null && club != null) {
            viewClubsController.showSelectedClubDetails(club);
        }
    }
}
