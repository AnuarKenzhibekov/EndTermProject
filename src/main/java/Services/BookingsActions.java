package Services;

import java.util.Scanner;

public interface BookingsActions {
    void createBooking(int showtimeId, int seatId, int hallId);
    void getUserBookings(int userId);
    void confirmBooking(int bookingId);
}
