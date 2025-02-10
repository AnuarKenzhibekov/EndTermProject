package Repositories;

import Entity.Showtimes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeRepository implements ShowtimeRepositoryInterface {
    private final Connection connection;

    // Constructor to initialize the repository with the database connection
    public ShowtimeRepository(Connection connection) {
        this.connection = connection;
    }

    // Method to retrieve all showtimes for a specific movie ID
    public List<Showtimes> getShowtimesByMovieId(int movieId) throws SQLException {
        List<Showtimes> showtimesList = new ArrayList<>();
        String sql = "SELECT date, showtimes, hall_id FROM showtimes WHERE movie_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieId); // Set the movieId parameter for the SQL query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Add each showtime to the list
                    showtimesList.add(new Showtimes(rs.getString("date"), rs.getString("showtimes"), rs.getInt("hall_id")));
                }
            }
        }
        return showtimesList; // Return the list of showtimes
    }

    // Method to retrieve a specific showtime by its ID
    public Showtimes getShowtimeById(int showtimeId) throws SQLException {
        String sql = "SELECT * FROM showtimes WHERE showtime_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeId); // Set the showtimeId parameter for the SQL query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve and return the showtime details
                    String date = rs.getString("date");
                    String showtime = rs.getString("showtimes");
                    int hallId = rs.getInt("hall_id");
                    return new Showtimes(date, showtime, hallId);
                } else {
                    // Throw an exception if the showtime is not found
                    throw new SQLException("Showtime not found");
                }
            }
        }
    }
}
