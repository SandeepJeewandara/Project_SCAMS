package com.example.scams_ood;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class addController {

    @FXML
    private Text CodeDescription;

    @FXML
    private Button CreateButton;

    @FXML
    private ComboBox<?> advisorIdInput;

    @FXML
    private ComboBox<?> clubIdInput;

    @FXML
    private Text codeAdvisorId;

    @FXML
    private Text codeClubId;

    @FXML
    private Text codeDate;

    @FXML
    private Text codeId;

    @FXML
    private Text codeName;

    @FXML
    private Text codeTime;

    @FXML
    private Text codeTitle;

    @FXML
    private Text codeType;

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