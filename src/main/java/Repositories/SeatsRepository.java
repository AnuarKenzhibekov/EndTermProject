package Repositories;

import MainPackage.Seats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsRepository implements SeatsRepositoryInterface {
    private final Connection connection;

    public SeatsRepository(Connection connection) {
        this.connection = connection;
    }

    private Seats createSeat(ResultSet rs) throws SQLException {
        return new Seats(
                rs.getInt("seat_id"),
                rs.getInt("showtime_id"),
                rs.getInt("hall_id"),
                rs.getInt("number"),
                rs.getString("row"),
                rs.getString("status"),
                rs.getInt("movie_id")
        );
    }

    public List<Seats> getAllAvailableSeats(int showtimeId) throws SQLException {
        String sql = "SELECT * FROM seats WHERE showtime_id = ? AND status = 'available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeId);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Seats> availableSeats = new ArrayList<>();

                while (rs.next()) {
                    availableSeats.add(createSeat(rs));
                }
                return availableSeats;
            }
        }
    }


    public Seats getSeatByRowAndNumber(int showtimeId, String row, int number) throws SQLException {
        String sql = "SELECT s.* FROM seats s " +
                "JOIN hall h ON s.hall_id = h.hall_id " +
                "WHERE s.showtime_id = ? AND s.row = ? AND s.number = ? AND h.status != 'not available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeId);
            stmt.setString(2, row);
            stmt.setInt(3, number);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createSeat(rs);
                }
            }
        }
        System.out.println("\u001B[31mSorry! but the hall is not available.\u001B[0m");
        return null;
    }

    @Override
    public boolean bookSeat(int seatId) throws SQLException {
        String sql = "UPDATE seats SET status = 'booked' WHERE seat_id = ? AND status = 'available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, seatId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}



