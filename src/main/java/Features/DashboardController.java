package Features;

import Database.DataAccess;
import com.example.scams_ood.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;


public class DashboardController {

    @FXML
    public FontAwesomeIconView GeneratereportIcon;
    @FXML
    public FontAwesomeIconView TrackattendanceIcon;
    @FXML
    public AnchorPane pnCreateClub;
    @FXML
    public AnchorPane pnDashboard;
    @FXML
    public AnchorPane pnClubDetails;
    @FXML
    public AnchorPane pnScheduleEvent;
    @FXML
    public AnchorPane pnEventDetails;
    @FXML
    public AnchorPane pnTrackAttendance;
    @FXML
    public AnchorPane pnGenerateReports;
    @FXML
    public AnchorPane pnJoinClub;
    @FXML
    public AnchorPane pnJoinEvent;
    @FXML
    public Label nameLabel;
    @FXML
    public Label usenameLabel;
    @FXML
    public Button exitButton;
    @FXML
    private Text CreateClubtxt;
    @FXML
    private Text GenerateReportstxt;
    @FXML
    private Text ScheduleEventtxt;
    @FXML
    private Text TrackAttendancetxt;
    @FXML
    private Button backToLogin;
    @FXML
    private Button clubDetailsButton;
    @FXML
    private Button createClubButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button eventDetailsButton;
    @FXML
    private Button generateReportsButton;
    @FXML
    private AnchorPane group;
    @FXML
    private Button joinClubButton;
    @FXML
    private Button joinEventButton;
    @FXML
    private Button scheduleEventButton;
    @FXML
    private Button trackAttendanceButton;

    //InClass Variables
    private Boolean isAdvisor;
    private List<Club> clubs;
    private List<ClubAdvisor> clubAdvisors;
    private List<Student> students;
    private List<Event> events;

    private ClubAdvisor loggedAdvisor;
    private Student loggedStudent;


    @FXML
    private void initialize() {

        clubs = DataAccess.getClubs();
        clubAdvisors = DataAccess.getClubAdvisors();
        students=DataAccess.getStudents();
        events=DataAccess.getEvents();


        // Print club details in the console
        System.out.println("Clubs:");
        for (Club club : clubs) {
            System.out.println(club);
        }

        // Print club Advisor details in the console
        System.out.println("\nClub Advisors:");
        for (ClubAdvisor clubAdvisor : clubAdvisors) {
            System.out.println(clubAdvisor);
        }

        // Print Student details in the console
        System.out.println("\nStudent:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Print Event details in the console
        System.out.println("\nEvent:");
        for (Event event : events) {
            System.out.println(event);
        }

        pnDashboard.toFront();
    }

    public ClubAdvisor getLoggedAdvisor() {
        return loggedAdvisor;
    }

    public void setLoggedAdvisor(ClubAdvisor loggedAdvisor) {
        this.loggedAdvisor = loggedAdvisor;
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }

    //Main Dashboard with Panes
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {

        if (event.getSource() == dashboardButton) {

            pnDashboard.toFront();

        } else if (event.getSource() == createClubButton) {

            FXMLLoader creteClubLoader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/CreateClub.fxml"));
            AnchorPane pnCreateClubContent = creteClubLoader.load();
            CreateClubController createClubController=creteClubLoader.getController();
            createClubController.setUser(loggedAdvisor);



            pnCreateClub.getChildren().clear();
            pnCreateClub.getChildren().addAll(pnCreateClubContent);

            pnCreateClub.toFront();

        } else if (event.getSource() == clubDetailsButton) {

            FXMLLoader viewClubLoader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/ViewClubs.fxml"));
            AnchorPane pnViewClubContent = viewClubLoader.load();
            ViewClubsController viewClubsController=viewClubLoader.getController();

            pnClubDetails.getChildren().clear();
            pnClubDetails.getChildren().addAll(pnViewClubContent);

            pnClubDetails.toFront();

        } else if (event.getSource() == scheduleEventButton) {

            pnScheduleEvent.toFront();

        } else if (event.getSource() == eventDetailsButton) {

            pnEventDetails.toFront();

        } else if (event.getSource() == trackAttendanceButton) {

            pnTrackAttendance.toFront();

        } else if (event.getSource() == generateReportsButton) {

            pnGenerateReports.toFront();

        } else if (event.getSource() == joinClubButton) {

            FXMLLoader joinClubLoader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/JoinClub.fxml"));
            AnchorPane pnJoinClubContent = joinClubLoader.load();
            JoinClubController joinClubController =joinClubLoader.getController();
            joinClubController.setUser(loggedStudent);

            pnJoinClub.getChildren().clear();
            pnJoinClub.getChildren().addAll(pnJoinClubContent);
            pnJoinClub.toFront();

        } else if (event.getSource() == joinEventButton) {

            pnJoinEvent.toFront();

        }
    }

    @FXML
    //Check if logged user is a Club Advisor or Student
    public void setUser(Object user, boolean isAdvisor) {
        this.isAdvisor=isAdvisor;

        if (user instanceof ClubAdvisor advisor) {
            nameLabel.setText(advisor.getName());
            usenameLabel.setText(advisor.getUsername());
            setLoggedAdvisor(advisor);


        } else if (user instanceof Student student) {
            nameLabel.setText(student.getStudentName());
            usenameLabel.setText(student.getUsername());
            setLoggedStudent(student);
        }

        // Adjust visibility of functionalities based on the user's role
        if(isAdvisor){
            joinClubButton.setVisible(false);
            joinEventButton.setVisible(false);
        } else {
            createClubButton.setVisible(false);
            scheduleEventButton.setVisible(false);
            trackAttendanceButton.setVisible(false);
            generateReportsButton.setVisible(false);
            TrackAttendancetxt.setVisible(false);
            GenerateReportstxt.setVisible(false);
            TrackattendanceIcon.setVisible(false);
            GeneratereportIcon.setVisible(false);
            CreateClubtxt.setText("JOIN CLUB");
            ScheduleEventtxt.setText("JOIN EVENT");
        }
    }


    @FXML
    public void onExitButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/scams_ood/Sign_in.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
