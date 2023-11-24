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
            FXMLLoader dashBoardLoader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/Dashboard.fxml"));
            Parent createClubRoot = dashBoardLoader.load();
            DashboardController dashboardController = dashBoardLoader.getController();

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(createClubRoot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
