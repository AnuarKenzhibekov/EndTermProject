package Repositories;

import Entity.Reviews;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRepository implements ReviewRepositoryInterface {
    private final Connection connection;

    // Constructor to initialize the repository with the database connection
    public ReviewRepository(Connection connection) {
        this.connection = connection;
    }

    // Method to retrieve a review by a specific user ID
    public Reviews getReviewByUserId(int userId) throws SQLException {
        String sql = "SELECT u.userName, tr.feedback, tr.grade FROM users u JOIN theater_reviews tr ON u.user_id = tr.user_id WHERE u.user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Create and return a Reviews object with the user's review details
                    return new Reviews(rs.getString("userName"), rs.getString("feedback"), rs.getInt("grade"));
                }
            }
        }
        return null; // Return null if no review is found for the user ID
    }
}
