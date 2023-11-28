package Database;

import com.example.scams_ood.ClubAdvisor;
import com.example.scams_ood.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountAccess {

    private static List<ClubAdvisor> advisorAccounts;
    private static List<Student> studentsAccount;

    public static List<ClubAdvisor> getAdvisorAccounts() {
        if (advisorAccounts == null) {
            advisorAccounts = new ArrayList<>();
            try (Connection connection = DatabaseConnectionTest.getConnection()) {
                String query = "SELECT * FROM Club_Advisor";
                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        ClubAdvisor advisor = new ClubAdvisor(
                                resultSet.getString("AdvisorID"),
                                resultSet.getString("First_name"),
                                resultSet.getString("Username"),
                                resultSet.getString("Password")

                        );
                        advisorAccounts.add(advisor);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return advisorAccounts;
    }

    // Populate studentsAccount from the database
    public static List<Student> getStudentsAccount() {
        if (studentsAccount == null) {
            studentsAccount = new ArrayList<>();
            try (Connection connection = DatabaseConnectionTest.getConnection()) {
                String query = "SELECT * FROM Student";
                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        Student student = new Student(
                                resultSet.getString("StudentID"),
                                resultSet.getString("First_name"),
                                resultSet.getString("User_name"),
                                resultSet.getString("Password")
                        );
                        studentsAccount.add(student);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentsAccount;
    }
}
