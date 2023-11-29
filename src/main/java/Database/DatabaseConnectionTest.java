package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnectionTest {

    //Establish a Connection to the Database Using JDBC
    public static Connection getConnection() throws SQLException, IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/scams_db";
        String username = "root";
        String password = "";

        try {
            System.out.println();
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            // Log to Error or Throw Exception
            throw new SQLException("Error connecting to the database: " + e.getMessage(), e);

        }
    }

}
