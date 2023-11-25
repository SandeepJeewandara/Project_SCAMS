package Features;

import Database.DataAccess;
import com.example.scams_ood.Club;
import com.example.scams_ood.ClubAdvisor;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


import java.io.IOException;
import java.util.List;


public class DashboardController {

    public FontAwesomeIconView GeneratereportIcon;
    public FontAwesomeIconView TrackattendanceIcon;
    public AnchorPane pnCreateClub;
    public AnchorPane pnDashboard;
    public AnchorPane pnClubDetails;
    public AnchorPane pnScheduleEvent;
    public AnchorPane pnEventDetails;
    public AnchorPane pnTrackAttendance;
    public AnchorPane pnGenerateReports;
    public AnchorPane pnJoinClub;
    public AnchorPane pnJoinEvent;
    @FXML
    private Text ClubDetailstxt;

    @FXML
    private Text CreateClubtxt;

    @FXML
    private Text Dashboardtxt;

    @FXML
    private Text EventDetailstxt;

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

    private Boolean isAdvisor = true;

    private List<Club> clubs;
    private List<ClubAdvisor> clubAdvisors;

    @FXML
    private void initialize() {

        clubs = DataAccess.getClubs();
        clubAdvisors = DataAccess.getClubAdvisors();

        // Print Club data to console
        System.out.println("Clubs:");
        for (Club club : clubs) {
            System.out.println(club);
        }

        // Print ClubAdvisor data to console
        System.out.println("\nClub Advisors:");
        for (ClubAdvisor clubAdvisor : clubAdvisors) {
            System.out.println(clubAdvisor);
        }



        if (isAdvisor) {
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

        pnDashboard.toFront();
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {

        if (event.getSource() == dashboardButton) {

            pnDashboard.toFront();

        } else if (event.getSource() == createClubButton) {

            FXMLLoader creteClubLoader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/CreateClub.fxml"));
            AnchorPane pnCreateClubContent = creteClubLoader.load();
            CreateClubController createClubController=creteClubLoader.getController();
            pnCreateClub.getChildren().clear();
            pnCreateClub.getChildren().addAll(pnCreateClubContent);

            pnCreateClub.toFront();

        } else if (event.getSource() == clubDetailsButton) {

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

            pnJoinClub.toFront();

        } else if (event.getSource() == joinEventButton) {

            pnJoinEvent.toFront();

        }
    }
}
