package Repositories;

import MainPackage.Reviews;
import MainPackage.Showtimes;

import java.sql.SQLException;
import java.util.List;

public interface ShowtimeRepositoryInterface {
    public List<Showtimes> getShowtimesByMovieId(int movieId) throws SQLException;
}
