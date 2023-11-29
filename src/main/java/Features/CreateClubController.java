package Features;

import Database.DataAccess;
import Database.DatabaseConnectionTest;
import com.example.scams_ood.Club;
import com.example.scams_ood.ClubAdvisor;
import com.example.scams_ood.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
import java.util.List;


public class CreateClubController {

    @FXML
    public ComboBox<String> ClubAdvisorInput;
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

    // FXML elements
    private File selectedImageFile;
    private Student LoggedStudent;
    private ClubAdvisor loggedAdvisor;
    private PromptBoxController promptBoxController = new PromptBoxController();



    // Initialization method for the controller
    @FXML
    private void initialize() {

    }


    // Method to handle club type selection
    public void onClubTypeSelected() {
        String selectedType = clubTypeInput.getValue();
    }


    //Method for Choose a Picture from Local Drive
    public void onChooseImageBtnClick() {

        // Logic to choose an image file using FileChooser
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

    // Method to set the logged-in user (either student or advisor)
    public void setUser(Object user){
        if (user instanceof ClubAdvisor advisor) {
            setLoggedAdvisor(advisor);
            ClubAdvisorInput.setValue(advisor.getAdvisorId());

        } else if (user instanceof Student student) {
            setLoggedStudent(student);
            setLoggedStudent(student);
        }
    }


    // Getter and setter methods
    public Student getLoggedStudent() {
        return LoggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        LoggedStudent = loggedStudent;
    }

    public ClubAdvisor getLoggedAdvisor() {
        return loggedAdvisor;
    }

    public void setLoggedAdvisor(ClubAdvisor loggedAdvisor) {
        this.loggedAdvisor = loggedAdvisor;
    }


    // Method to Create a Club Object and Insert iy into Database
    public void onCreateBtnClick() throws IOException {
        String clubID = clubIDInput.getText();
        String clubName = clubNameInput.getText();
        String clubType = clubTypeInput.getValue();
        LocalDate startDate = clubStartDateInput.getValue();
        String clubAdvisor = ClubAdvisorInput.getValue();
        String description = clubDescriptionInput.getText();
        String imageFileName = null;

        //Check if Club ID is Valid
        if (isValidClubId(clubID)) {

            //Validations For Club name, Club Type and Club Date
            if(isValidField(clubName, "Club Name")&&isValidField(clubType, "Club Type")&&isValidField(startDate, "Club Started Date")){
                if (selectedImageFile != null) {
                    String destinationFolderPath = "src/main/resources/Images/ClubLogos";

                    try {
                        Path destinationFolder = Path.of(destinationFolderPath);
                        Files.createDirectories(destinationFolder);

                        imageFileName = selectedImageFile.getName();

                        Path destinationFilePath = destinationFolder.resolve(imageFileName);
                        Files.copy(selectedImageFile.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //Create a Club Object and add into Main List
                Club newClub = new Club(clubID, clubName, clubType, startDate, description, imageFileName,loggedAdvisor);
                DataAccess.addClub(newClub);
                insertClubIntoDatabase(newClub); //Insert New Object into Database

            }
        } else {
            //Preview Success Message to the User
            promptBoxController.showPromptMessage("Club ID must be unique and have exactly 4 characters.");
        }

    }

    //Method to Insert Club Details into the Database
    private void insertClubIntoDatabase(Club club) {
        try {
            //Get the Connection
            Connection connection = DatabaseConnectionTest.getConnection();
            if (connection == null) {
                System.err.println("Error occurred when Connected to the Database. Please Check Your JDBC and Database Server");
                return;
            }

            // SQL Query for Inserting Data into the Club Table
            String sql = "INSERT INTO Club (ClubID, Club_name, Club_type, Started_date, Club_description, Club_logo_path, AdvisorID) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, club.getClubId());
                    preparedStatement.setString(2, club.getClubName());
                    preparedStatement.setString(3, club.getClubType());
                    preparedStatement.setDate(4, java.sql.Date.valueOf(club.getStartedDate()));
                    preparedStatement.setString(5, club.getClubDescription());
                    preparedStatement.setString(6, club.getClubLogoPath());
                    preparedStatement.setString(7,loggedAdvisor.getAdvisorId());

                    // Execute the SQL Query
                    preparedStatement.executeUpdate();

                    //Preview Success Message to the User
                    System.out.println("Club details inserted into the database successfully!");
                    promptBoxController.showSuccessPrompt("Club Details Inserted Successfully!");
                    clearInputFields();
                }

            } catch (SQLException e) {
                System.err.println("Error inserting club details into the database: " + e.getMessage());
                promptBoxController.showPromptMessage("Error Inserting Club Details into the Database");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to Validate Input Fields
    private boolean isValidField(String fieldValue, String fieldName) throws IOException {
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            String errorMessage = fieldName + " cannot be empty.";
            promptBoxController.showPromptMessage(errorMessage);
            return false;
        }
        return true;
    }

    // Method to Validate Date Input Field
    private boolean isValidField(LocalDate fieldValue, String fieldName) throws IOException {
        if (fieldValue == null) {
            String errorMessage = fieldName + " cannot be empty.";
            promptBoxController.showPromptMessage(errorMessage);
            return false;
        }
        return true;
    }


    // Method for validate Club ID
    private boolean isValidClubId(String clubId) {
        if (clubId.length() != 4) {
            return false;
        }

        for (Club existingClub : DataAccess.getClubs()) {
            if (existingClub.getClubId().equals(clubId)) {
                return false;
            }
        }

        return true;
    }

    // Method to clear input fields after club creation
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
