package Services;

import java.sql.SQLException;

public interface SeatsActions {
    void displayAvailableSeats(int showtimeId);
    void askAndBookSeat(int showtimeId) throws SQLException;
}
