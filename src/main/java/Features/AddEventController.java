package Features;

import Database.DataAccess;
import Database.DatabaseConnectionTest;
import com.example.scams_ood.Club;
import com.example.scams_ood.ClubAdvisor;
import com.example.scams_ood.Event;
import com.example.scams_ood.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddEventController {

    @FXML
    private Button CreateButton;

    @FXML
    public ComboBox<String> advisorIdInput;

    @FXML
    public ComboBox<String> clubIdInput;

    @FXML
    private DatePicker eventDateInput;

    @FXML
    private TextArea eventDescriptionInput;

    @FXML
    private TextField eventIdInput;

    @FXML
    private TextField eventNameInput;

    @FXML
    private TextField eventTimeInput;
    @FXML
    private ComboBox<String> clubTypeInput;

    private Club eventClub;
    private ClubAdvisor loggedAdvisor;


    public void addEventToObject(ActionEvent event) {


        String eventID = eventIdInput.getText();
        String eventName = eventNameInput.getText();
        LocalDate eventDate = eventDateInput.getValue();
        String eventdescription = eventDescriptionInput.getText();
        String eventTime = eventTimeInput.getText();

        Event newEvent = new Event(eventID,eventName,eventdescription);
        inserteventintoDatabase(newEvent);

    }

    private void inserteventintoDatabase(Event event) {
        try {
            Connection connection = DatabaseConnectionTest.getConnection();
            if (connection == null) {
                System.err.println("Error occurred when Connected to the Database. Please Check Your JDBC and Database Server");
                return;
            }

            String sql = "INSERT INTO Event (EventID, Event_name, Event_description, AdvisorID) " + "VALUES (?, ?, ?, ?)";

            try {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, event.getEventId());
                    preparedStatement.setString(2, event.getEventName());
                    //preparedStatement.setDate(4, java.sql.Date.valueOf(event.getEventDate()));
                    //preparedStatement.setString(4, event.getEventTime());
                    preparedStatement.setString(3, event.getEventDescription());
                    //preparedStatement.setString(6,event,getEventClub());
                    preparedStatement.setString(4, loggedAdvisor.getAdvisorId());

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }



    public void setUser(Object user) {
        if (user instanceof ClubAdvisor advisor) {
            setLoggedAdvisor(advisor);
            advisorIdInput.setValue(advisor.getAdvisorId());

        } else if (user instanceof Club club) {
            setEventClub(club);
            setEventClub(club);
        }
    }

    public Club getEventClub() {
        return eventClub;
    }

    public void setEventClub(Club eventClub) {
        this.eventClub = eventClub;
    }

    public ClubAdvisor getLoggedAdvisor() {
        return loggedAdvisor;
    }

    public void setLoggedAdvisor(ClubAdvisor loggedAdvisor) {
        this.loggedAdvisor = loggedAdvisor;
    }


    private boolean isValidClubId(String eventId) {
        if (eventId.length() != 4) {
            return false;
        }

        for (Event existingevent : DataAccess.getEvents()) {
            if (existingevent.getEventId().equals(eventId)) {
                return false;
            }
        }
        return true;
    }

}
