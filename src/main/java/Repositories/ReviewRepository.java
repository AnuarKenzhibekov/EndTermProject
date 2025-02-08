package Repositories;

import Entity.Reviews;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRepository implements ReviewRepositoryInterface {
    private final Connection connection;

    public ReviewRepository(Connection connection) {
        this.connection = connection;
    }

    public Reviews getReviewByUserId(int userId) throws SQLException {
        String sql = "SELECT u.userName, tr.feedback, tr.grade FROM users u JOIN theater_reviews tr ON u.user_id = tr.user_id WHERE u.user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reviews(rs.getString("userName"), rs.getString("feedback"), rs.getInt("grade"));
                }
            }
        }
        return null;
    }
}

