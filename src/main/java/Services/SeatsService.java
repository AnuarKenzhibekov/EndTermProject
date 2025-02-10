package Services;

import DataBase.DatabaseManager;
import Entity.Bookings;
import Entity.Seats;
import Entity.User;
import Repositories.BookingsRepository;
import Repositories.MovieRepository;
import Repositories.SeatsRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SeatsService implements SeatsActions {

    // Display available seats for a given showtime
    public void displayAvailableSeats(int showtimeId) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            SeatsRepository seatsrep = new SeatsRepository(dbManager.getConnection());

            // Get all available seats for the showtime
            List<Seats> availableSeats = seatsrep.getAllAvailableSeats(showtimeId);

            if (availableSeats.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mNo available seats found.\u001B[0m");
                ShowtimesService showtimesService = new ShowtimesService();
                showtimesService.askShowtime();
            } else {
                // Display available seats, sorted by row and number
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mAvailable Seats:\u001B[0m");

                availableSeats.sort(Comparator.comparing(Seats::getRow).thenComparing(Seats::getNumber));

                List<String> rowSeats = new ArrayList<>();
                for (Seats seat : availableSeats) {
                    String row = seat.getRow();
                    int number = seat.getNumber();
                    rowSeats.add("\u001B[32mRow: " + row + " | Number: " + number + "\u001B[0m");
                }

                // Print seats in a formatted manner
                for (int i = 0; i < rowSeats.size(); i++) {
                    if (i % 2 == 0) {
                        System.out.print(rowSeats.get(i) + "   ");
                    } else {
                        System.out.println(rowSeats.get(i));
                    }
                }

                System.out.println();
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mWould you like to book a seat? (yes/no)\u001B[0m");

                Scanner scanner = new Scanner(System.in);
                String userResponse = scanner.nextLine().trim().toLowerCase();

                if ("yes".equals(userResponse)) {
                    System.out.println("-------------------------------------------------------------------------");
                    askAndBookSeat(showtimeId);  // Proceed to booking if user chooses 'yes'
                } else if ("no".equals(userResponse)) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mBooking canceled.\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                    MoviesService moviesService = new MoviesService();
                    moviesService.displayMovieDetails();
                    MoviesService.ConsoleMovieQueryStrategy queryStrategy = moviesService.new ConsoleMovieQueryStrategy();
                    queryStrategy.askMovie();
                } else {
                    // Handle invalid input
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mInvalid input! Please type 'yes' or 'no'.\u001B[0m");
                    displayAvailableSeats(showtimeId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Prompt user to choose a seat and handle booking
    public void askAndBookSeat(int showtimeId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();
        SeatsRepository seatsrep = new SeatsRepository(dbManager.getConnection());
        BookingsRepository bookingsRepository = new BookingsRepository(dbManager.getConnection());

        while (true) {
            String row;
            int number;

            // Ask user to choose a row
            while (true) {
                System.out.println("\u001B[32mChoose a row (a, b):\u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
                row = scanner.nextLine().trim();
                if (!row.isEmpty()) break;
                System.out.println("\u001B[31mInvalid input. Row cannot be empty.\u001B[0m");
            }

            // Ask user to choose a seat number
            while (true) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mChoose a seat number:\u001B[0m");
                String input = scanner.nextLine().trim();

                if (!input.isEmpty()) {
                    try {
                        number = Integer.parseInt(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\u001B[31mInvalid input. Please enter a valid seat number.\u001B[0m");
                    }
                } else {
                    System.out.println("\u001B[31mInvalid input. Seat number cannot be empty.\u001B[0m");
                }
            }

            // Check seat availability
            Seats seat = seatsrep.getSeatByRowAndNumber(showtimeId, row, number);

            if (seat == null) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mHall is not available right now!.\u001B[0m");
            } else if ("not available".equalsIgnoreCase(seat.getStatus())) {
                System.out.println("\u001B[31mThis seat is not available. Please choose another one.\u001B[0m");
            } else if (!"available".equalsIgnoreCase(seat.getStatus())) {
                System.out.println("\u001B[31mThis seat is already booked.\u001B[0m");
            } else {
                // Proceed to book the seat
                boolean isBooked = seatsrep.bookSeat(seat.getSeatId());

                if (isBooked) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[32mBooking successful! Here is your ticket ↓\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");

                    Bookings booking = new Bookings(
                            Bookings.getBookingId(),
                            User.getUserID(),
                            showtimeId,
                            seat.getSeatId(),
                            seat.getHallId(),
                            "booked"
                    );
                    boolean bookingSuccess = bookingsRepository.createBooking(booking);

                    if (bookingSuccess) {
                        TicketService ticketService = new TicketService(new DatabaseManager());
                        ticketService.printTicket(showtimeId);
                    } else {
                        System.out.println("\u001B[31mFailed to create booking.\u001B[0m");
                    }
                    return;
                } else {
                    System.out.println("\u001B[31mFailed to book the seat. Please try again later.\u001B[0m");
                }
            }
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mPlease try again.\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
        }
    }

}
