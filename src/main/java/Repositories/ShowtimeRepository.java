package Repositories;

import Entity.Showtimes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeRepository implements ShowtimeRepositoryInterface{
    private final Connection connection;

    public ShowtimeRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Showtimes> getShowtimesByMovieId(int movieId) throws SQLException {
        List<Showtimes> showtimesList = new ArrayList<>();
        String sql = "SELECT date, showtimes, hall_id FROM showtimes WHERE movie_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    showtimesList.add(new Showtimes(rs.getString("date"), rs.getString("showtimes"), rs.getInt("hall_id")));
                }
            }
        }
        return showtimesList;
    }

    public Showtimes getShowtimeById(int showtimeId) throws SQLException {
        String sql = "SELECT * FROM showtimes WHERE showtime_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String date = rs.getString("date");
                    String showtime = rs.getString("showtime");
                    int hallId = rs.getInt("hall_id");
                    return new Showtimes(date, showtime, hallId);
                } else {
                    throw new SQLException("Showtime not found");
                }
            }
        }
    }
}


