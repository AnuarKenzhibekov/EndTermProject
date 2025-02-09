package Services;

import Entity.*;
import Repositories.*;
import DataBase.DatabaseManager;
import java.sql.SQLException;
import java.util.Scanner;

public class TicketService {

    private final DatabaseManager dbManager;
    private final BookingsRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatsRepository seatsRepository;

    public TicketService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.bookingRepository = new BookingsRepository(dbManager.getConnection());
        this.movieRepository = new MovieRepository(dbManager.getConnection());
        this.showtimeRepository = new ShowtimeRepository(dbManager.getConnection());
        this.seatsRepository = new SeatsRepository(dbManager.getConnection());
    }

    public void printTicket(int bookingId) {
        try {
            Bookings booking = bookingRepository.getBookingById(bookingId);
            Movies movie = movieRepository.getMovieById(booking.getShowtimeId());
            Showtimes showtime = showtimeRepository.getShowtimeById(booking.getShowtimeId());
            Seats seat = seatsRepository.getSeatById(booking.getSeatId());

            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mYour Ticket Details:\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mMovie: " + movie.getTitle() + "\u001B[0m");
            System.out.println("\u001B[32mShowtime: " + showtime.getDate() + " " + showtime.getShowtime() + "\u001B[0m");
            System.out.println("\u001B[32mSeat: Row " + seat.getRow() + " Number " + seat.getNumber() + "\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("\u001B[31mError: " + e.getMessage() + "\u001B[0m");
        }
    }
}
