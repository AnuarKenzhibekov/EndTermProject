package Repositories;

import Entity.Seats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatsRepository implements SeatsRepositoryInterface {
    private final Connection connection;

    // Constructor to initialize the repository with the database connection
    public SeatsRepository(Connection connection) {
        this.connection = connection;
    }

    // Helper method to create a Seats object from ResultSet data
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

    // Method to retrieve all available seats for a specific showtime
    public List<Seats> getAllAvailableSeats(int showtimeId) throws SQLException {
        String sql = "SELECT * FROM seats WHERE showtime_id = ? AND status = 'available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeId);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Seats> availableSeats = new ArrayList<>();

                // Loop through the result set and create Seat objects
                while (rs.next()) {
                    availableSeats.add(createSeat(rs));
                }
                return availableSeats; // Return the list of available seats
            }
        }
    }

    // Method to get a specific seat by its ID
    public Seats getSeatById(int seatId) throws SQLException {
        String sql = "SELECT * FROM seats WHERE seat_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, seatId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the seat details and create a Seats object
                    int showtimeId = rs.getInt("showtime_id");
                    int hallId = rs.getInt("hall_id");
                    int number = rs.getInt("number");
                    String row = rs.getString("row");
                    String status = rs.getString("status");
                    int movieId = rs.getInt("movie_id");

                    return new Seats(seatId, showtimeId, hallId, number, row, status, movieId);
                } else {
                    // Throw an exception if the seat is not found
                    throw new SQLException("Seat not found with ID: " + seatId);
                }
            }
        }
    }

    // Method to retrieve a seat by its row and number for a specific showtime
    public Seats getSeatByRowAndNumber(int showtimeId, String row, int number) throws SQLException {
        String sql = "SELECT s.* FROM seats s " +
                "JOIN hall h ON s.hall_id = h.hall_id " +
                "WHERE s.showtime_id = ? AND s.row = ? AND s.number = ? " +
                "AND h.status != 'not available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeId);
            stmt.setString(2, row);
            stmt.setInt(3, number);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Create and return the Seat object if it exists
                    return createSeat(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error in getSeatByRowAndNumber(): " + e.getMessage());
            e.printStackTrace();
        }

        return null; // Return null if the seat is not found
    }

    // Method to book a seat by updating its status
    @Override
    public boolean bookSeat(int seatId) throws SQLException {
        String sql = "UPDATE seats SET status = 'booked' WHERE seat_id = ? AND status = 'available'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, seatId);
            int rowsAffected = stmt.executeUpdate(); // Execute the update query
            return rowsAffected > 0; // Return true if a row was affected (seat booked)
        }
    }
}
