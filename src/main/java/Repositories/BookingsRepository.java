package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingsRepository {

    private final Connection connection;

    public BookingsRepository(Connection connection) {
        this.connection = connection;
    }


    public boolean createBooking(int userId, int showtimeId, int seatId, int hallId, String status) throws SQLException {
        String sql = "INSERT INTO bookings (user_id, showtime_id, seat_id, hall_id, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, showtimeId);
            stmt.setInt(3, seatId);
            stmt.setInt(4, hallId);
            stmt.setString(5, status);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
