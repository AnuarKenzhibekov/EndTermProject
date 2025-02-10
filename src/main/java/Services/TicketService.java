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
            if (booking == null) {
                System.out.println("Booking not found.");
                return;
            }

            Movies movie = movieRepository.getMovieById(booking.getShowtimeId());
            Showtimes showtime = showtimeRepository.getShowtimeById(booking.getShowtimeId());
            Seats seat = seatsRepository.getSeatById(booking.getSeatId());

            System.out.println("===== Cinema Ticket =====");
            System.out.println("Movie: " + movie.getTitle());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Duration: " + movie.getDuration());
            System.out.println("Date: " + showtime.getDate());
            System.out.println("Showtime: " + showtime.getShowtime());
            System.out.println("Hall: " + showtime.getHallId());
            System.out.println("Seat: Row " + seat.getRow() + ", Number " + seat.getNumber());
            System.out.println("=========================");
        } catch (SQLException e) {
            System.out.println("Error fetching ticket details: " + e.getMessage());
        }
    }
}