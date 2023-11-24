package DB_Operations;

import com.example.scams_ood.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubAttendanceTrackingDB {

    static List<Student> studentsList = new ArrayList<>();


    public static List<Student> retrieveStudentDataFromDatabase(String eventId) {

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "")) {

            String query = "SELECT  S.StudentID AS StudentID,E.Event_Name AS Event_Name,S.First_name AS Student_First_name,S.Email AS Student_Email, ER.Attendance FROM Event_Registration ER JOIN Student S ON ER.StudentID = S.StudentID JOIN Event E ON ER.EventID = E.EventID WHERE  E.EventID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, eventId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudentName(resultSet.getString("Student_First_name"));
                        student.setStudentId(resultSet.getString("StudentID"));
                        student.setStudentGmail(resultSet.getString("Student_Email"));
                        //System.out.println(student.getStudentId());
                        System.out.println(student.getStudentName());
                        System.out.println(student.getStudentGmail());
                        studentsList.add(student);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return studentsList;

    }
}

