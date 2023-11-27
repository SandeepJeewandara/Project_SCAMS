package com.example.scams_ood;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddActivityController {

    @FXML
    private Button CreateButton;

    @FXML
    private ComboBox<?> advisorIdInput;

    @FXML
    private ComboBox<?> clubIdInput;

    @FXML
    private Text codeTitle;

    @FXML
    private DatePicker eventDateInput;

    @FXML
    private TextArea eventDescriptionInput;

    @FXML
    private TextField eventIdInput;

    @FXML
    private TextField eventNameInput;

    @FXML
    private TextField eventTimeInput;

}
