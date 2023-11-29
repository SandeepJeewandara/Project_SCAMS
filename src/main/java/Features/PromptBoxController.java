package Features;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class PromptBoxController {

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane promptBoxPain;

    @FXML
    private Label promptText;


    //Method for close Prompt box and get working window again
    public void onOkButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    // Set the text of the prompt message
    public void setPromptText(String  message) {
        promptText.setText(message);
    }


    //Prompt box to show Error Messages
    public void showPromptMessage(String message) throws IOException {
        // Load the FXML file for the prompt box
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/PromptBox.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        stage.initStyle(StageStyle.UNDECORATED); // Set the stage style to undecorated
        stage.setResizable(false);// Disable resizing of the prompt box


        PromptBoxController controller = loader.getController();
        controller.setPromptText(message);
        addPopUpAnimation(stage); // Add a pop-up animation to the stage
        stage.show();
    }


    // Method to show a success prompt with a green border
    public void showSuccessPrompt(String message) throws IOException {
        // Load the FXML file for the Success prompt box
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/PromptBox.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        stage.initStyle(StageStyle.UNDECORATED); // Set the stage style to undecorated
        stage.setResizable(false); // Disable resizing of the prompt box

        PromptBoxController controller = loader.getController();
        controller.setPromptText(message);

        // Access the promptBoxPain and set the border color to green
        AnchorPane promptBoxPane = (AnchorPane) scene.lookup("#promptBoxPain");
        if (promptBoxPane != null) {
            promptBoxPane.setStyle("-fx-border-color: #27ae60;");
        }

        addPopUpAnimation(stage);// Add a pop-up animation to the stage
        stage.show();
    }


    // Add a pop-up animation to the stage
    private void addPopUpAnimation(Stage stage) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), stage.getScene().getRoot());
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }
}
