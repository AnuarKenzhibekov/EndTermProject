package Repositories;

import Entity.Showtimes;

import java.sql.SQLException;
import java.util.List;

public interface ShowtimeRepositoryInterface {
    public List<Showtimes> getShowtimesByMovieId(int movieId) throws SQLException;
}
