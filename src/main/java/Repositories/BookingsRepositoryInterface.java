package Repositories;

import Entity.Bookings;
import java.sql.SQLException;
import java.util.List;

public interface BookingsRepositoryInterface {
    boolean createBooking(Bookings booking) throws SQLException;
    List<Bookings> getBookingsByUserId(int userId) throws SQLException;
    Bookings getBookingById(int bookingId) throws SQLException;
    boolean confirmBooking(int bookingId) throws SQLException;
}
