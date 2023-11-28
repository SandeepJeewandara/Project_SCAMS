package com.example.scams_ood;

import Database.DataAccess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class JoinClubController {
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

    @FXML
    private void initialize() {
        initializeTable();
        displayClubs();
        setupTableListener();
    }

    private void initializeTable() {
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        clubsTable.getColumns().add(clubNameColumn);

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

    public void onJoinClubButtonClick(ActionEvent event) {
        // Get the selected club from the TableView
        Club selectedClub = clubsTable.getSelectionModel().getSelectedItem();
        selectedClub.addMember(getLoggedStudent());
        System.out.println(getLoggedStudent().getStudentName());

    }
}
