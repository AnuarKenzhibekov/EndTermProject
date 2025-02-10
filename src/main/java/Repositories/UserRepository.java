package Repositories;

import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserRepositoryInterface {
    private final Connection connection;

    // Constructor to initialize the repository with the database connection
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    // Method to create a new user and return the created User object with a generated user_id
    public User createUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, email) VALUES (?, ?) RETURNING user_id"; // SQL query to insert a user and return the user_id

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName()); // Set the username parameter for the SQL query
            stmt.setString(2, user.getEmail()); // Set the email parameter for the SQL query

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setUserID(rs.getInt("user_id")); // Set the generated user_id to the User object
                    return user; // Return the created User object with the user_id
                }
            }
        }
        return null; // Return null if the user was not created
    }

    // Method to find a user based on the username and email
    public User findUser(String userName, String email) throws SQLException {
        String sql = "SELECT user_id, username, email FROM users WHERE username = ? AND email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName); // Set the username parameter for the SQL query
            stmt.setString(2, email); // Set the email parameter for the SQL query

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("email")); // Create User object from the query result
                    user.setUserID(rs.getInt("user_id")); // Set the user_id from the result set
                    return user; // Return the User object
                }
            }
        }
        return null; // Return null if no matching user is found
    }
}
