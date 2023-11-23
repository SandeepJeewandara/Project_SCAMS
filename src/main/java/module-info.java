module com.example.scams_ood {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.scams_ood to javafx.fxml;
    exports com.example.scams_ood;
    exports Features;

    opens Features to javafx.fxml;
    opens Database to javafx.fxml;
}