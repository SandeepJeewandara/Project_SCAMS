package com.example.scams_ood;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddEventController {

    @FXML
    private Button CreateButton;

    @FXML
    private ComboBox<?> advisorIdInput;

    @FXML
    private ComboBox<?> clubIdInput;

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

    @FXML
    private ChoiceBox<?> eventTypeInput;

}
