package Features;

//Imports packages and classes

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class AttendanceTrackingController implements Initializable { //Create a AttendanceTrackingController class that implements the initialize interface

    @FXML
    private TableView<Student> attendanceTableView; //TableView for display students Names

    private String eventId; //Variable for store the Event ID
    @FXML
    private Label countLabel; //Variable for count Student Count for each event

    private boolean isInitialized = false; //value for check initialize state
    private Stage stage;
    private Scene scene;


    public String setStudentDataInTable(Label eventIdLabel) { //method to set the event id from the Label

        this.eventId = eventIdLabel.getText();
        checksTheInitialization(); //call the checksTheInitialization method
        return eventId;

    }

    public void checksTheInitialization() { //Method to check the if the controller is initialized and the event id is set
        if (isInitialized && eventId != null) {
            //Create table columns for display student information
            TableColumn studentNameColumn = new TableColumn("Student Name");
            TableColumn studentIdColumn = new TableColumn("Student ID");
            TableColumn studentGmailColumn = new TableColumn("Student Gmail");
            TableColumn studentAttendanceColumn = new TableColumn("Student Attendance");

            attendanceTableView.getColumns().addAll(studentNameColumn, studentIdColumn, studentGmailColumn, studentAttendanceColumn);
            attendanceTableView.setEditable(true); //Enable tableview for editing

            List<Student> retrievedStudentList = DataAccess.getStudents(); //Retrieve all students details from the database
            List<Student> studentOfEvents = new ArrayList<>();

            for (Student student : retrievedStudentList) { //Logic to get students who joined the specific event
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
            int count = Integer.parseInt(String.valueOf(studentOfEvents.size()));//Count the number students for the event and display it
            countLabel.setText(String.valueOf(count));

            if (!studentOfEvents.isEmpty()) { //Add the table view with student data if not empty
                studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
                studentIdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
                studentGmailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("Gmail"));
                studentAttendanceColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("attendance"));
                studentAttendanceColumn.setCellFactory(CheckBoxTableCell.forTableColumn(studentAttendanceColumn));
                studentAttendanceColumn.setEditable(true);
            }

            if (attendanceTableView != null) { //Set the items in the table view
                ObservableList<Student> students = FXCollections.observableArrayList(studentOfEvents);
                this.attendanceTableView.setItems(students);

            }
        }
    }

    public void saveToDatabase(ActionEvent event)throws IOException { //Method to save student data into database
        List<Student> retrieveStudentsList = new ArrayList<>(attendanceTableView.getItems());
        PromptController promptController = new PromptController();

        for (Student student : retrieveStudentsList) { //Iteration to check the students' attendance checkboxes values
            boolean value;
            if (student.getAttendance() != null && student.getAttendance().isSelected()) {
                value = true;
            } else {
                value = false;
            }

            //Create a connection with database and set values in Event_Registration table
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
                promptController.showPromptMessage("Attendance Added Successfully!");

            } catch (SQLException ev) {
                ev.printStackTrace();
                promptController.showPromptMessage("Error Occurred!");
            }
        }
    }

    public void BackToClub(ActionEvent event) throws IOException {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("/com/example/scams_ood/AttendanceClub.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(root);
        this.stage.setScene(this.scene);
        this.stage.show();

    }

    @Override //Initialize method to called when the controller is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isInitialized = true; //Set the initialization to true
        checksTheInitialization(); //calls the initialization check method
    }


}








