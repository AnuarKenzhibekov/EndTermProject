package Repositories;

import MainPackage.Seats;
import java.sql.SQLException;
import java.util.List;

public interface SeatsRepositoryInterface {
    List<Seats> getAllAvailableSeats(int showtimeId) throws SQLException;

    Seats getSeatByRowAndNumber(int showtimeId, String row, int number) throws SQLException;

    boolean bookSeat(int seatId) throws SQLException;
}
