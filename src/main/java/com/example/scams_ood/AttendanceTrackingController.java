package com.example.scams_ood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceTrackingController implements Initializable {

    @FXML
    private TextField eventIdTextField;
    @FXML
    private Button submitButton;

    @FXML
    private TableColumn<Event,CheckBox> attendance;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Student,String> studentGmail;

    @FXML
    private TableColumn<Student, Integer> studentId;

    @FXML
    private TableColumn<Student,String> studentName;


    //private List<Student> retrieveStudentDataFromDatabase(TextField textField) {
    public void submit(ActionEvent event){
        List<Student> students = new ArrayList<>();
        String eventId = eventIdTextField.getText();
        //System.out.println(eventId);
        //String eventId = "E001";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "")){
             /*Statement statement = connection.createStatement())*/

            String query = "SELECT  S.StudentID AS StudentID,S.First_name AS Student_First_name,S.Email AS Student_Email, ER.Attendance FROM Event_Registration ER JOIN Student S ON ER.StudentID = S.StudentID JOIN Event E ON ER.EventID = E.EventID WHERE  E.EventID = ?";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1,eventId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentName(resultSet.getString("Student_First_name"));
                    student.setStudentId(resultSet.getString("StudentID"));
                    student.setStudentGmail(resultSet.getString("Student_Email"));
                    System.out.println(student.getStudentId());
                    System.out.println(student.getStudentName());
                    students.add(student);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //List<Student> retrievedStudents = retrieveStudentDataFromDatabase(eventIdTextField);

        //System.out.println(retrievedStudents);

    }
}
