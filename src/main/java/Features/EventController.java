package Features;

import com.example.scams_ood.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class EventController {


    @FXML
    private ImageView Image;

    @FXML
    private Button eventButton;

    @FXML
    private Label eventId;

    @FXML
    private Label eventName;

    private Event event;

    public void eventIconSetData(Event event){
        this.event = event;
        eventName.setText(event.getEventName());
        eventId.setText((event.getEventId()));

        //Image image = new Image(getClass().getResourceAsStream(club.getImageLogoPath()));
        //clubImage.setImage(image);

    }









    /*

    public void switchToAttendance(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/scams_ood/AttendanceTracking.fxml"));

        AttendanceTrackingController attendanceController = loader.getController();
        attendanceController.setClubId(club.getClubId());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("SCAMS - Attendance Tracking");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     */
    public void switchToAttendance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/AttendanceTracking.fxml"));
        Parent root = loader.load();

        AttendanceTrackingController attendanceTrackingController = loader.getController();
        attendanceTrackingController.setStudentDataInTable(eventId);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SCAMS - Attendance Tracking");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }







}



