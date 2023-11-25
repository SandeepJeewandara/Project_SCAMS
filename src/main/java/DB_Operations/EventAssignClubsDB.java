package DB_Operations;

import com.example.scams_ood.Event;
import com.example.scams_ood.ClubController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EventAssignClubsDB {

    static List<Event> eventsList = new ArrayList<>();

    public static List<Event> retrieveEventDataFromDatabase(String clubId) {

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "")) {

            String query = "SELECT Event.* FROM Event JOIN Club ON Event.ClubID = Club.ClubID WHERE Club.ClubID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, clubId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Event event = new Event();
                        event.setEventName(resultSet.getString("Event_Name"));
                        event.setEventId(resultSet.getString("EventID"));
                        //Event.setStudentGmail(resultSet.getString("Student_Email"));
                        //System.out.println(student.getStudentId());
                        //System.out.println(student.getStudentName());
                        //System.out.println(student.getStudentGmail());
                        eventsList.add(event);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return eventsList;

    }
}
