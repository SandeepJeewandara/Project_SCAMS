package Database;

import Database.DatabaseConnectionTest;
import com.example.scams_ood.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    private static List<Club> clubs;
    private static List<ClubAdvisor> clubAdvisors;

    static {
        clubs = new ArrayList<>();
        clubAdvisors = new ArrayList<>();

        try {
            Connection connection = DatabaseConnectionTest.getConnection();
            fetchClubAdvisors(connection);
            fetchClubs(connection);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fetchClubAdvisors(Connection connection) throws SQLException {
        String query = "SELECT * FROM Club_Advisor";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClubAdvisor clubAdvisor = new ClubAdvisor(
                        resultSet.getString("AdvisorID"),
                        resultSet.getString("First_name"),
                        resultSet.getString("Gender"),
                        resultSet.getString("Email"),
                        resultSet.getDate("DOB"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password")
                );
                clubAdvisors.add(clubAdvisor);
            }
        }
    }

    private static void fetchClubs(Connection connection) throws SQLException {
        String query = "SELECT * FROM Club";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Club club = new Club(
                        resultSet.getString("ClubID"),
                        resultSet.getString("Club_name"),
                        resultSet.getString("Club_type"),
                        resultSet.getDate("Started_date").toLocalDate(),
                        resultSet.getString("Club_description"),
                        resultSet.getString("Club_logo_path"),
                        null
                );
                clubs.add(club);

                // Connect Club and ClubAdvisor
                String advisorId = resultSet.getString("AdvisorID");
                connectClubAndAdvisor(club, advisorId);
            }
        }
    }

    private static void connectClubAndAdvisor(Club club, String advisorId) {
        for (ClubAdvisor advisor : clubAdvisors) {
            if (advisor.getAdvisorId().equals(advisorId)) {
                club.setClubAdvisor(advisor);
                break;
            }
        }
    }

    public static List<Club> getClubs() {
        return clubs;
    }

    public static List<ClubAdvisor> getClubAdvisors() {
        return clubAdvisors;
    }
}
