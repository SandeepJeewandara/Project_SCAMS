package Features;

import Database.DatabaseConnectionTest;
import com.example.scams_ood.Club;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class CreateClubController {

    @FXML
    private TextField clubIDInput;

    @FXML
    private TextField clubNameInput;

    @FXML
    private ChoiceBox<String> clubTypeInput;

    @FXML
    private DatePicker clubStartDateInput;

    @FXML
    private TextArea clubDescriptionInput;

    @FXML
    private Pane clubLogoInput;

    private File selectedImageFile;

    public void onClubTypeSelected() {

        String selectedType = clubTypeInput.getValue();
    }


    public void onChooseImageBtnClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Club Logos");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        selectedImageFile = fileChooser.showOpenDialog(null);

        if (selectedImageFile != null) {
            // Display the selected image in the AnchorPane
            Image image = new Image(selectedImageFile.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(179.0);
            imageView.setFitWidth(179.0);

            // Remove existing children from clubLogoInput
            clubLogoInput.getChildren().clear();

            // Add the ImageView to the clubLogoInput
            clubLogoInput.getChildren().add(imageView);
        }
    }

    public void onCreateBtnClick() {
        String clubID = clubIDInput.getText();
        String clubName = clubNameInput.getText();
        String clubType = clubTypeInput.getValue();
        LocalDate startDate = clubStartDateInput.getValue();
//        String clubAdvisor = ClubAdvisorInput.getValue().toString();
        String description = clubDescriptionInput.getText();
        String imageFileName = null;


        if (selectedImageFile != null) {
            // Save the selected image to the specific folder
            String destinationFolderPath = "src/main/resources/Images/ClubLogos";

            try {
                // Create the destination folder if it doesn't exist
                Path destinationFolder = Path.of(destinationFolderPath);
                Files.createDirectories(destinationFolder);

                // Set the image file name to the Item object
                imageFileName = selectedImageFile.getName();

                // Copy the selected image file to the destination folder
                Path destinationFilePath = destinationFolder.resolve(imageFileName);
                Files.copy(selectedImageFile.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(destinationFilePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Club newClub = new Club(clubID, clubName, clubType, startDate, description, imageFileName, null);

        // Insert data into the database
        insertClubIntoDatabase(newClub);
    }


    private void insertClubIntoDatabase(Club club) {
        try {
            Connection connection = DatabaseConnectionTest.getConnection();

            if (connection == null) {
                System.err.println("Error encountered while attempting to connect to the database");
                return;
            }

            // SQL query for inserting data into the Club table
            String sql = "INSERT INTO Club (ClubID, Club_name, Club_type, Started_date, Club_description, Club_logo_path, AdvisorID) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, club.getClubId());
                    preparedStatement.setString(2, club.getClubName());
                    preparedStatement.setString(3, club.getClubType());
                    preparedStatement.setDate(4, java.sql.Date.valueOf(club.getStartedDate()));
                    preparedStatement.setString(5, club.getClubDescription());
                    preparedStatement.setString(6, club.getClubLogoPath());
                    preparedStatement.setString(7, "A001");  // This is Need to Change

                    // Execute the SQL query
                    preparedStatement.executeUpdate();

                    System.out.println("Club details inserted into the database successfullys!");
                    clearInputFields();
                }

            } catch (SQLException e) {
                System.err.println("Error inserting club details into the database: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearInputFields(){
        clubIDInput.clear();
        clubNameInput.clear();
        clubTypeInput.setValue(null);
        clubStartDateInput.setValue(null);
        clubDescriptionInput.clear();
        clubLogoInput.getChildren().clear();
        selectedImageFile = null;
    }





}
