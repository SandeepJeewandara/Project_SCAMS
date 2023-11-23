package com.example.scams_ood;

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

public class AttendanceTrackingController {

    @FXML
    private TableView<Student> attendanceTableView;

    @FXML
    private TextField eventIdTextField;
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

    //private List<Student> retrieveStudentDataFromDatabase(TextField textField) {
    public void submit(ActionEvent event) {
        List<Student> studentsList = new ArrayList<>();
        String eventId = eventIdTextField.getText();
        //System.out.println(eventId);
        //String eventId = "E001";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "")) {


            String query = "SELECT  S.StudentID AS StudentID,E.Event_Name AS Event_Name,S.First_name AS Student_First_name,S.Email AS Student_Email, ER.Attendance FROM Event_Registration ER JOIN Student S ON ER.StudentID = S.StudentID JOIN Event E ON ER.EventID = E.EventID WHERE  E.EventID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, eventId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudentName(resultSet.getString("Student_First_name"));
                        student.setStudentId(resultSet.getString("StudentID"));

                        student.setStudentGmail(resultSet.getString("Student_Email"));
                        System.out.println(student.getStudentId());
                        System.out.println(student.getStudentName());
                        System.out.println(student.getStudentGmail());
                        studentsList.add(student);
                        System.out.println(studentsList);

                    }
                    if (!studentsList.isEmpty()) {

                        studentNameColumn.setCellValueFactory(new PropertyValueFactory("studentName"));
                        System.out.println(studentNameColumn.getText());
                        studentIdColumn.setCellValueFactory(new PropertyValueFactory("studentId"));
                        System.out.println(studentIdColumn);
                        studentGmailColumn.setCellValueFactory(new PropertyValueFactory("studentGmail"));
                        //attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attendance"));
                        attendanceColumn.setCellFactory(CheckBoxTableCell.forTableColumn(attendanceColumn));

                        /*
                        attendanceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, Boolean>, ObservableValue<Boolean>>() {
                            @Override
                            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Student, Boolean> param){
                                Student student = param.getValue();
                                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(student.isAttendance());
                                booleanProp.addListener((observable, oldValue, newValue) -> student.setAttendance(newValue));
                                return booleanProp;
                            }
                        });

                         */


                        if (attendanceTableView != null) {
                            ObservableList<Student> students = FXCollections.observableArrayList(studentsList);
                            attendanceTableView.setItems(students);
                            attendanceColumn.setEditable(true);
                        }
                    }
                }


            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void backToClubDashBoard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("attendance-club.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}





        //List<Student> retrievedStudents = retrieveStudentDataFromDatabase(eventIdTextField);

        //System.out.println(retrievedStudents);



