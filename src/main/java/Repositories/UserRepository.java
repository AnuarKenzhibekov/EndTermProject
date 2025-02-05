package Repositories;

import MainPackage.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User createUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, email) VALUES (?, ?) RETURNING user_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setUserID(rs.getInt("user_id"));
                    return user;
                }
            }
        }
        return null;
    }

    public User findUser(String userName, String email) throws SQLException {
        String sql = "SELECT user_id, username, email FROM users WHERE username = ? AND email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("username"), rs.getString("email"));
                }
            }
        }
        return null;
    }
}
