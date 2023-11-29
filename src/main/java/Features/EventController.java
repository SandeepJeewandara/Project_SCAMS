package Features;

import com.example.scams_ood.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EventController {


    @FXML
    private Label eventId;

    @FXML
    private Label eventName;

    private Event event;

    public void eventIconSetData(Event event) {
        this.event = event;
        eventName.setText(event.getEventName());
        eventId.setText((event.getEventId()));


    }


    public void switchToAttendance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/scams_ood/AttendanceTracking.fxml"));
        Parent root = loader.load();

        AttendanceTrackingController attendanceTrackingController = loader.getController();
        attendanceTrackingController.setStudentDataInTable(eventId); //Send the event id to the AttendanceTRackingController
//
//        group.getChildren().clear();
//        group.getChildren().add(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SCAMS - Attendance Tracking");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}



