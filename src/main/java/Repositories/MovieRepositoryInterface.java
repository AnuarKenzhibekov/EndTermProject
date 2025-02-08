package Repositories;

import Entity.Movies;
import java.sql.SQLException;
import java.util.List;

public interface MovieRepositoryInterface {
    List<Movies> getAllMovies() throws SQLException;
    Movies getMovieById(int movieId) throws SQLException;
}

