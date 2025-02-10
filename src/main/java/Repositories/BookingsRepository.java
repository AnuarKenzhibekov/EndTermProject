package Repositories;

import Entity.Bookings;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsRepository implements BookingsRepositoryInterface {
    private final Connection connection;

    // Constructor to initialize the repository with the database connection
    public BookingsRepository(Connection connection) {
        this.connection = connection;
    }

    // Method to create a new booking in the database
    public boolean createBooking(Bookings booking) throws SQLException {
        String sql = "INSERT INTO bookings (user_id, showtime_id, seat_id, hall_id, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getShowtimeId());
            stmt.setInt(3, booking.getSeatId());
            stmt.setInt(4, booking.getHallId());
            stmt.setString(5, booking.getStatus());

            int rowsAffected = stmt.executeUpdate();  // Execute the update and check the number of affected rows
            if (rowsAffected > 0) {
                return true;  // Return true if the booking was successfully created
            }
        }
        return false;  // Return false if the booking creation failed
    }

    // Method to retrieve all bookings made by a specific user
    public List<Bookings> getBookingsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE user_id = ?";
        List<Bookings> bookings = new ArrayList<>();  // List to store bookings

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {  // Execute the query and iterate through the result set
                while (rs.next()) {
                    bookings.add(new Bookings(
                            rs.getInt("booking_id"),
                            rs.getInt("user_id"),
                            rs.getInt("showtime_id"),
                            rs.getInt("seat_id"),
                            rs.getInt("hall_id"),
                            rs.getString("status")
                    ));
                }
            }
        }
        return bookings;  // Return the list of bookings
    }

    // Method to retrieve a specific booking by its ID
    public Bookings getBookingById(int bookingId) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Bookings(
                            rs.getInt("booking_id"),
                            rs.getInt("user_id"),
                            rs.getInt("showtime_id"),
                            rs.getInt("seat_id"),
                            rs.getInt("hall_id"),
                            rs.getString("status")
                    );
                }
            }
        }
        return null;  // Return null if no booking was found for the given ID
    }

    // Method to confirm a booking by updating its status to 'confirmed'
    public boolean confirmBooking(int bookingId) throws SQLException {
        String sql = "UPDATE bookings SET status = 'confirmed' WHERE booking_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;  // Return true if the booking was successfully confirmed
        }
    }
}
