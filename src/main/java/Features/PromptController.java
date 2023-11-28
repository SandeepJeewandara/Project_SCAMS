package Features;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.regex.Pattern;

public class PromptController {

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane promptBoxPain;

    @FXML
    private Label promptText;

    private String clubIdValidation(TextField textField){
        String clubId = textField.getText();

        String clubIdRegex = "C\\d{3}";

        
        if (clubId == null || !Pattern.matches(clubIdRegex, clubId)) {
            ("Invalid Club ID", "Club ID must start with 'C' followed by three digits.");

            textField.clear();
            return null; // Or you can throw an exception or handle it according to your requirement.
        }
    }
}
