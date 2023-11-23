package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/scams_db";
        String username = "root";
        String password = "";

        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {

            // Log the error or rethrow the exception
            throw new SQLException("Error connecting to the database: " + e.getMessage(), e);
        }
    }

}
