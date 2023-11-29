package com.example.scams_ood;

import com.example.scams_ood.Club;
import com.example.scams_ood.ClubAdvisor;
import com.example.scams_ood.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class AddEventController {

    @FXML
    private Button CreateButton;

    @FXML
    private ComboBox<ClubAdvisor> advisorIdInput;

    @FXML
    private ComboBox<Club> clubIdInput;

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
    private void initialize() {
        ClubAdvisor clubAdvisor = new ClubAdvisor();
        advisorIdInput.getItems().add(0, );
    }

    public void addToDatabase(ActionEvent event){

        String eventName = eventNameInput.getText();
        String eventId = eventIdInput.getText();
        LocalDate eventDate = Date.valueOf(eventDateInput.getValue()).toLocalDate();
        String eventTime = eventTimeInput.getText();
        String eventDescription = eventDescriptionInput.getText();
        Club clubId = clubIdInput.getValue();
        ClubAdvisor advisorId = advisorIdInput.getValue();

        Event newEvent = new Event();
        newEvent.setEventId(eventId);
        newEvent.setEventName(eventName);
        newEvent.setEventTime(Time.valueOf(eventTime));
        newEvent.setEventDate(Date.from(eventDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        newEvent.setEventDescription(eventDescription);
        newEvent.setClub(clubId);
        newEvent.setClubAdvisor(advisorId);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "");

            String query = "INSERT INTO Event (EventID, Event_name, Event_date,Event_time, Event_description,ClubID,AdvisorID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newEvent.getEventId());
                preparedStatement.setString(2, newEvent.getEventName());
                preparedStatement.setDate(3, new Date(newEvent.getEventDate().getTime()));
                preparedStatement.setTime(4, newEvent.getEventTime());
                preparedStatement.setString(5, newEvent.getEventDescription());
                preparedStatement.setString(7, newEvent.getClub().getClubId());
                preparedStatement.setString(8, newEvent.getClubAdvisor().getAdvisorId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            connection.close();
            System.out.println("Event added to the database.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error adding event to the database.");
        }

    }


}




