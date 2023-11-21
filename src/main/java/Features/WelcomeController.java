package Features;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    public void OnContinueButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader createClubLoader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/CreateClub.fxml"));
            Parent createClubRoot = createClubLoader.load();
            CreateClubController createClubController = createClubLoader.getController();

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(createClubRoot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
