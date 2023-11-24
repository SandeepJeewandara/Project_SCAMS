package DB_Operations;

import com.example.scams_ood.Club;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectedClubDB {

     static List<Club> clubsList = new ArrayList<>();


    public static List<Club> retrieveClubDetailsFromDatabase(String advisorId) {
        //List<Club> clubsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scams_db", "root", "")) {


            String query = "SELECT Club.ClubID, Club.Club_name,Club.Club_logo_path FROM Club_Advisor JOIN Club  ON Club_Advisor.AdvisorID = Club.AdvisorID WHERE Club_Advisor.AdvisorID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);
                //SELECT Club_name, Club_logo_path FROM club
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Club club = new Club();
                        club.setClubId(resultSet.getString("ClubID"));
                        club.setClubName(resultSet.getString("Club_name"));
                        //club.setImageLogoPath(resultSet.getString("Club_logo_path"));
                        clubsList.add(club);
                    }
                }
            }
        } catch (SQLException w) {
            w.printStackTrace();
        }
        return clubsList;

    }
}

