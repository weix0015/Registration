import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseManager {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");

    // Connect to database
    static boolean addUser(String name, String email, String phone, String address, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            // SQL query to insert a new user
            String sql = "INSERT INTO users (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";

            // Prepare the SQL statement with user data
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, phone);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, password);

                int addedRows = preparedStatement.executeUpdate();
                return addedRows > 0; // Return true if the user was added successfully
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}