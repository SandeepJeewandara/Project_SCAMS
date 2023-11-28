package Features;

import com.example.scams_ood.PromptBoxController;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class PromptController {


    @FXML
    private Label promptText;


    public void onOkButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setPromptText(String message) {
        promptText.setText(message);
    }

    public void showPromptMessage(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/PromptBox.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setResizable(false);


        PromptController controller = loader.getController();
        controller.setPromptText(message);
        addPopUpAnimation(stage);
        stage.setResizable(false);
        stage.show();
    }


    private void addPopUpAnimation(Stage stage) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), stage.getScene().getRoot());
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }
}


