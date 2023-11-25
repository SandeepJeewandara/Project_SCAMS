package com.example.scams_ood;

import DB_Operations.ClubAttendanceTrackingDB;
import DB_Operations.EventAssignClubsDB;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static DB_Operations.ClubAttendanceTrackingDB.retrieveStudentDataFromDatabase;

public class AttendanceTrackingController implements Initializable {

    @FXML
    private TableView<Student> attendanceTableView;

    @FXML
    private TableView<Event> eventTable ;
    @FXML
    private TextField eventIdTextField;
    @FXML
    private TableColumn<Event, String> eventIdColumn;





    @FXML
    private TableColumn<Event,String> eventNameColumn;


    @FXML
    private Button submitButton;

    @FXML
    private TableColumn<Student, Boolean> attendanceColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Student, String> studentGmailColumn;

    @FXML
    private TableColumn<Student, Integer> studentIdColumn;

    @FXML
    private TableColumn<Student, String> studentNameColumn;

    private String clubId;


    public void submit(ActionEvent event) {
        String eventId = eventIdTextField.getText();
        List<Student> retrieveStudentsList = retrieveStudentDataFromDatabase(eventId);

        if (!retrieveStudentsList.isEmpty()) {
            studentNameColumn.setCellValueFactory(new PropertyValueFactory("studentName"));
            System.out.println(studentNameColumn.getText());
            studentIdColumn.setCellValueFactory(new PropertyValueFactory("studentId"));
            System.out.println(studentIdColumn);
            studentGmailColumn.setCellValueFactory(new PropertyValueFactory("studentGmail"));
            //attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attendance"));
            //attendanceColumn.setCellFactory(CheckBoxTableCell.forTableColumn(attendanceColumn));

            if (attendanceTableView != null) {
                ObservableList<Student> students = FXCollections.observableArrayList(retrieveStudentsList);
                attendanceTableView.setItems(students);
                //attendanceColumn.setEditable(true);
            }
        }
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
        System.out.println(clubId);

        setEventDataInTable();
    }

    public void backToClubDashBoard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("attendance-club.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void setEventDataInTable()  {

        //this.eventTable.getSelectionModel().clearSelection();
        //eventTable.getColumns().clear();
        //this.eventTable.refresh();

        //eventTable =new TableView<>() ;




        /*
        TableColumn eventName = new TableColumn("Event Name");
        TableColumn eventId = new TableColumn("Event ID");
        eventTable.getColumns().addAll(eventName, eventId);
        System.out.println(clubId);

        List<Event> retrievedEventsList = EventAssignClubsDB.retrieveEventDataFromDatabase(clubId);

        eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        eventId.setCellValueFactory(new PropertyValueFactory<Event, String>("eventId"));

         */
        List<Event> retrievedEventsList = EventAssignClubsDB.retrieveEventDataFromDatabase(clubId);

        if(!retrievedEventsList.isEmpty()){
            this.eventNameColumn.setCellValueFactory(new PropertyValueFactory("eventName"));
            this.eventIdColumn.setCellValueFactory(new PropertyValueFactory("eventId"));
        }

        if (eventTable != null) {
            ObservableList<Event> events = FXCollections.observableArrayList(retrievedEventsList);
            this.eventTable.setItems(events);

        }
        //eventTable.getItems().clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}





        //List<Student> retrievedStudents = retrieveStudentDataFromDatabase(eventIdTextField);

        //System.out.println(retrievedStudents);



