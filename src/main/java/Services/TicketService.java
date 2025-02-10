package Services;

import DataBase.DatabaseManager;
import Entity.Bookings;
import Entity.Movies;
import Entity.Seats;
import Entity.Showtimes;
import Repositories.BookingsRepository;
import Repositories.MovieRepository;
import Repositories.SeatsRepository;
import Repositories.ShowtimeRepository;

import java.sql.SQLException;

public class TicketService implements TicketActions {

    private final DatabaseManager dbManager;
    private final BookingsRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatsRepository seatsRepository;

    // Constructor to initialize repositories
    public TicketService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.bookingRepository = new BookingsRepository(dbManager.getConnection());
        this.movieRepository = new MovieRepository(dbManager.getConnection());
        this.showtimeRepository = new ShowtimeRepository(dbManager.getConnection());
        this.seatsRepository = new SeatsRepository(dbManager.getConnection());
    }

    // Method to print the ticket details
    public void printTicket(int bookingId) {
        final String GREEN = "\033[0;32m"; // Color code for green
        final String RESET = "\033[0m";   // Reset color code

        try {
            // Fetch booking details using bookingId
            Bookings booking = bookingRepository.getBookingById(bookingId);
            if (booking == null) {
                System.out.println(GREEN + "Booking not found." + RESET);
                return;
            }

            // Fetch related movie, showtime, and seat details
            Movies movie = movieRepository.getMovieById(booking.getShowtimeId());
            Showtimes showtime = showtimeRepository.getShowtimeById(booking.getShowtimeId());
            Seats seat = seatsRepository.getSeatById(booking.getSeatId());

            // Print the ticket details
            System.out.println(GREEN + "===== Cinema Ticket =====" + RESET);
            System.out.println(GREEN + "Movie: " + movie.getTitle() + RESET);
            System.out.println(GREEN + "Genre: " + movie.getGenre() + RESET);
            System.out.println(GREEN + "Duration: " + movie.getDuration() + RESET);
            System.out.println(GREEN + "Date: " + showtime.getDate() + RESET);
            System.out.println(GREEN + "Showtime: " + showtime.getShowtime() + RESET);
            System.out.println(GREEN + "Hall: " + showtime.getHallId() + RESET);
            System.out.println(GREEN + "Seat: Row " + seat.getRow() + ", Number " + seat.getNumber() + RESET);
            System.out.println(GREEN + "=========================" + RESET);
        } catch (SQLException e) {
            // Handle any SQL exceptions during ticket details fetching
            System.out.println(GREEN + "Error fetching ticket details: " + e.getMessage() + RESET);
        }
    }

}
