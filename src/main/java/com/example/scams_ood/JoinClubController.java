package com.example.scams_ood;

import Database.DataAccess;
import Database.DatabaseConnectionTest;
import Features.PromptBoxController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JoinClubController {
    public Button joinClubButton;
    @FXML
    private TableView<Club> clubsTable;

    @FXML
    private TableColumn<Club, String> clubNameColumn;


    @FXML
    private Label clubIDLabel;

    @FXML
    private Label clubAdvisorLabel;

    @FXML
    private ImageView clubLogoView;

    @FXML
    private Label clubNameLabel;

    @FXML
    private Label clubTypeLabel;

    @FXML
    private Label startedDateLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private AnchorPane titlePane;

    private ObservableList<Club> observableClubs;

    private Student LoggedStudent;

    private ClubAdvisor LoggedAdvisor;

    private boolean isMember = false;
    private Features.PromptBoxController promptBoxController = new PromptBoxController();

    @FXML
    private void initialize() {
        initializeTable();
        displayClubs();
        setupTableListener();
        updateJoinLeaveButton();
        loadMemberStatus();
    }

    private void loadMemberStatus() {
        Club selectedClub = clubsTable.getSelectionModel().getSelectedItem();
        if (selectedClub != null && getLoggedStudent() != null) {
            isMember = DataAccess.isStudentMemberOfClub(getLoggedStudent().getStudentId(), selectedClub.getClubId());
            updateJoinLeaveButton();
        }
    }

    private void updateJoinLeaveButton() {
        if (isMember) {
            joinClubButton.setText("Leave Club");
        } else {
            joinClubButton.setText("Join Club");
        }
    }


    private void initializeTable() {
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        observableClubs = FXCollections.observableArrayList();

        clubsTable.setItems(observableClubs);
    }

    public Student getLoggedStudent() {
        return LoggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        LoggedStudent = loggedStudent;
    }

    public ClubAdvisor getLoggedAdvisor() {
        return LoggedAdvisor;
    }

    public void setLoggedAdvisor(ClubAdvisor loggedAdvisor) {
        LoggedAdvisor = loggedAdvisor;
    }

    private void displayClubs() {
        List<Club> retrievedClubs = DataAccess.getClubs();

        observableClubs.addAll(retrievedClubs);
    }

    private void setupTableListener() {
        clubsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Club>() {
            @Override
            public void changed(ObservableValue<? extends Club> observable, Club oldValue, Club newValue) {
                // Update the labels with the details of the selected club
                if (newValue != null) {
                    showSelectedClubDetails(newValue);
                    isMember = newValue.getMembers().contains(getLoggedStudent());
                    updateJoinLeaveButton();
                } else {
                    clearLabels();
                }
            }
        });
    }

    private void showSelectedClubDetails(Club selectedClub) {
        clubIDLabel.setText(selectedClub.getClubId());
        clubNameLabel.setText(selectedClub.getClubName());
        clubTypeLabel.setText(selectedClub.getClubType());
        startedDateLabel.setText(selectedClub.getStartedDate().toString());
        clubAdvisorLabel.setText(selectedClub.getClubAdvisor().getName());

        if (selectedClub.getClubLogoPath() != null && !selectedClub.getClubLogoPath().isEmpty()) {
            String imagePath = "/Images/ClubLogos/" + selectedClub.getClubLogoPath();
            clubLogoView.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
        } else {
            clubLogoView.setImage(null);
        }
    }

    private void clearLabels() {
        clubNameLabel.setText("");
        clubTypeLabel.setText("");
        startedDateLabel.setText("");
        clubAdvisorLabel.setText("");
        clubLogoView.setImage(null);
    }

    public void setUser(Object user){
        if (user instanceof ClubAdvisor advisor) {
            setLoggedAdvisor(advisor);

        } else if (user instanceof Student student) {
            setLoggedStudent(student);
            setLoggedStudent(student);
        }
    }

    public void onJoinClubButtonClick(ActionEvent event) throws IOException {
        // Get the selected club from the TableView
        Club selectedClub = clubsTable.getSelectionModel().getSelectedItem();

        if (isMember) {
            // If the student is already a member, remove the membership
            selectedClub.getMembers().remove(getLoggedStudent());
            promptBoxController.showPromptMessage("You have left this Club Successfully");
            removeStudentFromClub(getLoggedStudent().getStudentId(), selectedClub.getClubId());
        } else {
            // If the student is not a member, add the membership
            selectedClub.addMember(getLoggedStudent());
            promptBoxController.showSuccessPrompt("You have joined this Club Successfully");
            addStudentToClub(getLoggedStudent().getStudentId(), selectedClub.getClubId());
        }

        isMember = selectedClub.getMembers().contains(getLoggedStudent()); // Check if the student is a member after the operation
        updateJoinLeaveButton();
    }



    public static void addStudentToClub(String studentId, String clubId) {
        try (Connection connection = DatabaseConnectionTest.getConnection()) {
            String query = "INSERT INTO Student_Club (StudentID, ClubID) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, clubId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeStudentFromClub(String studentId, String clubId) {
        try (Connection connection = DatabaseConnectionTest.getConnection()) {
            String query = "DELETE FROM Student_Club WHERE StudentID = ? AND ClubID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, clubId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
