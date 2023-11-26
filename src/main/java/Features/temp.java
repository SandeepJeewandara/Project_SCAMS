package Features;

import com.example.scams_ood.ClubAdvisor;
import com.example.scams_ood.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class temp {

    public Label setUser;
    @FXML
    public void setUser(Object user) {
       if (user instanceof ClubAdvisor advisor) {
           setUser.setText("Welcome, " + advisor.getName()+ " (Advisor)");

        } else if (user instanceof Student student) {
            setUser.setText("Welcome, " + student.getStudentName() + " (Student)");
        }
    }
//}
}
