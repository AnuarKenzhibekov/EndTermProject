package Services;

import Entity.*;
import Repositories.BookingsRepository;
import Repositories.UserRepository;
import DataBase.DatabaseManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookingsService implements BookingsActions {

    private final DatabaseManager dbManager;
    private static BookingsRepository bookingRepository;
    private final UserRepository userRepository;

    // Constructor to initialize repositories
    public BookingsService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.bookingRepository = new BookingsRepository(dbManager.getConnection());
        this.userRepository = new UserRepository(dbManager.getConnection());
    }

    // Create a new booking for a user
    public void createBooking(int showtimeId, int seatId, int hallId) {
        Scanner scanner = new Scanner(System.in);

        // Input username and email
        System.out.println("\u001B[32mEnter your username:\u001B[0m");
        String userName = scanner.nextLine().trim();
        System.out.println("\u001B[32mEnter your email:\u001B[0m");
        String email = scanner.nextLine().trim();

        try {
            User user = userRepository.findUser(userName, email);

            // Check if user exists
            if (user == null) {
                System.out.println("\u001B[31mUser not found! Please check your username and email.\u001B[0m");
                return;
            }

            // Create booking and attempt to save it
            Bookings booking = new Bookings(Bookings.getBookingId(), User.getUserID(), showtimeId, seatId, hallId, "booked");
            boolean isBooked = bookingRepository.createBooking(booking);

            if (isBooked) {
                System.out.println("\u001B[32mSeat booked successfully!\u001B[0m");
            } else {
                System.out.println("\u001B[31mFailed to book the seat. Please try again later.\u001B[0m");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get and display a user's bookings
    public void getUserBookings(int userId) {
        try {
            List<Bookings> bookings = bookingRepository.getBookingsByUserId(userId);

            if (bookings.isEmpty()) {
                System.out.println("\u001B[31mNo bookings found.\u001B[0m");
            } else {
                System.out.println("\u001B[32mYour Bookings:\u001B[0m");
                for (Bookings booking : bookings) {
                    System.out.println("\u001B[32mBooking ID: " + booking.getBookingId() + " Showtime ID: " + booking.getShowtimeId() + "\u001B[0m");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Confirm a booking
    public void confirmBooking(int bookingId) {
        try {
            Bookings booking = bookingRepository.getBookingById(bookingId);

            if (booking != null && "booked".equals(booking.getStatus())) {
                boolean isConfirmed = bookingRepository.confirmBooking(bookingId);
                if (isConfirmed) {
                    System.out.println("\u001B[32mBooking confirmed successfully!\u001B[0m");
                } else {
                    System.out.println("\u001B[31mFailed to confirm booking.\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mBooking not found or already confirmed.\u001B[0m");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
