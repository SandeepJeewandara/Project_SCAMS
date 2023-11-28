package Features;


import Database.DataAccess;
import com.example.scams_ood.Event;
import com.example.scams_ood.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceTrackingController implements Initializable {

    @FXML
    private TableView<Student> attendanceTableView;

    private String eventId;
    @FXML
    private Label countLabel;

    private boolean isInitialized = false;


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

            attendanceTableView.getColumns().addAll(studentNameColumn, studentIdColumn, studentGmailColumn, studentAttendanceColumn);
            attendanceTableView.setEditable(true);
            System.out.println(eventId);

            List<Student> retrievedStudentList = DataAccess.getStudents();
            List<Student> studentOfEvents = new ArrayList<>();

            for (Student student : retrievedStudentList) {
                List<Event> joinedEvents = student.getEventsjoined();
                if (joinedEvents != null) {
                    for (Event event : joinedEvents) {
                        if (event.getEventId().equals(eventId)) {
                            studentOfEvents.add(student);
                            break;
                        }
                    }


                }
            }
            int count = Integer.parseInt(String.valueOf(studentOfEvents.size()));
            countLabel.setText(String.valueOf(count));

            if (!studentOfEvents.isEmpty()) {
                studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
                studentIdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
                studentGmailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("Gmail"));
                studentAttendanceColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("attendance"));
                studentAttendanceColumn.setCellFactory(CheckBoxTableCell.forTableColumn(studentAttendanceColumn));
                studentAttendanceColumn.setEditable(true);
            }

            if (attendanceTableView != null) {
                ObservableList<Student> students = FXCollections.observableArrayList(studentOfEvents);
                this.attendanceTableView.setItems(students);

            }
        }
    }

    public void saveToDatabase(ActionEvent event) {
        List<Student> retrieveStudentsList = new ArrayList<>(attendanceTableView.getItems());
        int studentCount = attendanceTableView.getItems().size();
        System.out.println("Count of Students" + studentCount);


        for (Student student : retrieveStudentsList) {
            boolean value;
            if (student.getAttendance() != null && student.getAttendance().isSelected()) {
                value = true;
            } else {
                value = false;
            }


            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "")) {
                //for (Student students : retrieveStudentsList) {
                String sql = "INSERT INTO Event_Registration (StudentID, EventID, Attendance) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE Attendance = VALUES(Attendance)";
//                    for (Student students : retrieveStudentsList) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    for (Student students : retrieveStudentsList) {
                        preparedStatement.setString(1, students.getStudentId());
                        preparedStatement.setString(2, eventId);
                        preparedStatement.setBoolean(3, value);

                        preparedStatement.executeUpdate();
                    }


                }

            } catch (SQLException ev) {
                ev.printStackTrace();
            }


        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isInitialized = true;
        checksTheInitialization();
    }


}








