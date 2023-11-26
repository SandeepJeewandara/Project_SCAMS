package com.example.scams_ood;

import DB_Operations.ClubAttendanceTrackingDB;
import DB_Operations.EventAssignClubsDB;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceTrackingController implements Initializable {

    @FXML
    private TableView<Student> attendanceTableView;

    private String eventId;

    private boolean isInitialized = false;

    public void backToClubDashBoard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("attendance-event.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public String setStudentDataInTable(Label eventIdLabel) {

        this.eventId = eventIdLabel.getText();
        checksTheInitialization();
        System.out.println(eventId);
        return eventId;

    }

    public void checksTheInitialization() {
        if (isInitialized && eventId != null) {

            TableColumn studentNameColumn = new TableColumn("Student Name");
            TableColumn studentIdColumn = new TableColumn("Student ID");
            TableColumn studentGmailColumn = new TableColumn("Student Gmail");
            TableColumn studentAttendanceColumn = new TableColumn("Student Attendance");

            attendanceTableView.getColumns().addAll(studentNameColumn, studentIdColumn,studentGmailColumn,studentAttendanceColumn);
            System.out.println(eventId);

            List<Student> retrievedStudentList = ClubAttendanceTrackingDB.retrieveStudentDataFromDatabase(eventId);

            if (!retrievedStudentList.isEmpty()) {
                studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
                studentIdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
                studentGmailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentGmail"));
                studentAttendanceColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("attendance"));

            }

            if (attendanceTableView != null) {
                ObservableList<Student> students = FXCollections.observableArrayList(retrievedStudentList);
                this.attendanceTableView.setItems(students);

            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isInitialized =true;
        checksTheInitialization();
    }



}








