module com.example.scams_ood {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.scams_ood to javafx.fxml;
    exports com.example.scams_ood;
    exports Features;
}