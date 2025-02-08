package Repositories;

import Entity.Movies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements MovieRepositoryInterface {
    private final Connection connection;

    public MovieRepository(Connection connection) {
        this.connection = connection;
    }

    public Movies getMovieById(int movieId) throws SQLException {
        String sql = "SELECT * FROM movies WHERE movie_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String genre = rs.getString("genre");
                    String duration = rs.getString("duration");

                    // Возвращаем объект Movies
                    return new Movies(title, genre, duration);
                } else {
                    throw new SQLException("Movie not found for ID: " + movieId);
                }
            }
        }
    }

    public List<Movies> getAllMovies() throws SQLException {
        List<Movies> moviesList = new ArrayList<>();
        String sql = "SELECT title, genre, duration FROM movies";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                moviesList.add(new Movies(rs.getString("title"), rs.getString("genre"), rs.getString("duration")));
            }
        }
        return moviesList;
    }
}

