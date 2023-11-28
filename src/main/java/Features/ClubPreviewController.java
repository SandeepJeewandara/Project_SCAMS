package Features;

import com.example.scams_ood.Club;
import com.example.scams_ood.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class ClubPreviewController {
    public ImageView clubImagePreview;


    @FXML
    private Button selectButton;

    @FXML
    private Label clubName;

    private Club club;
    private ViewClubsController viewClubsController;

    public void clubIconSetData(Club club){
        this.club = club;
        clubName.setText(club.getClubName());

        if (club.getClubLogoPath() != null && !club.getClubLogoPath().isEmpty()) {
            String imagePath = "/Images/ClubLogos/" + club.getClubLogoPath();
            try {
                clubImagePreview.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
            } catch (Exception e) {
                clubImagePreview.setImage(null);
            }


        }
    }

    public void setViewClubsController(ViewClubsController viewClubsController) {
        this.viewClubsController = viewClubsController;
    }

    public void onSelectButtonClick(ActionEvent event) {
        if (viewClubsController != null && club != null) {
            viewClubsController.showSelectedClubDetails(club);
        }
    }
}
