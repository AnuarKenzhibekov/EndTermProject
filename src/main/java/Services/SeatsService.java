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
    public void displayAvailableSeats(int showtimeId) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            SeatsRepository seatsrep = new SeatsRepository(dbManager.getConnection());

            List<Seats> availableSeats = seatsrep.getAllAvailableSeats(showtimeId);

            if (availableSeats.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mNo available seats found.\u001B[0m");
                ShowtimesService showtimesService = new ShowtimesService();
                showtimesService.askShowtime();
            } else {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mAvailable Seats:\u001B[0m");

                availableSeats.sort(Comparator.comparing(Seats::getRow).thenComparing(Seats::getNumber));

                List<String> rowSeats = new ArrayList<>();

                for (Seats seat : availableSeats) {
                    String row = seat.getRow();
                    int number = seat.getNumber();
                    rowSeats.add("\u001B[32mRow: " + row + " | Number: " + number + "\u001B[0m");
                }

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
                    askAndBookSeat(showtimeId);
                } else if ("no".equals(userResponse)) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mBooking canceled.\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                    MoviesService moviesService = new MoviesService();
                    moviesService.displayMovieDetails();
                    MoviesService.ConsoleMovieQueryStrategy queryStrategy = moviesService.new ConsoleMovieQueryStrategy();
                    queryStrategy.askMovie();
                } else {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mInvalid input! Please type 'yes' or 'no'.\u001B[0m");
                    displayAvailableSeats(showtimeId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void askAndBookSeat(int showtimeId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();
        SeatsRepository seatsrep = new SeatsRepository(dbManager.getConnection());
        BookingsRepository bookingsRepository = new BookingsRepository(dbManager.getConnection());
        MovieRepository moviesrep = new MovieRepository(dbManager.getConnection());

        String row;
        int number;

        while (true) {
            System.out.println("\u001B[32mChoose a row (a,b):\u001B[0m");
            row = scanner.nextLine().trim();
            if (!row.isEmpty()) break;
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[31mInvalid input. Row cannot be empty.\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
        }

        while (true) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mChoose a seat number:\u001B[0m");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    number = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mInvalid input. Please enter a valid seat number.\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            } else {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mInvalid input. Seat number cannot be empty.\u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }

        Seats seat = seatsrep.getSeatByRowAndNumber(showtimeId, row, number);

        if (seat != null && "available".equals(seat.getStatus())) {
            boolean isBooked = seatsrep.bookSeat(seat.getSeatId());

            if (isBooked) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mSeat booked successfully!\u001B[0m");

                Bookings booking = new Bookings(Bookings.getBookingId(), User.getUserID(), showtimeId, seat.getSeatId(), seat.getHallId(), "booked");
                boolean bookingSuccess = bookingsRepository.createBooking(booking);

                if (bookingSuccess) {
                    System.out.println("\u001B[32mBooking successful! Here is your ticket:\u001B[0m");
                    TicketService ticketService = new TicketService(new DatabaseManager());
                    ticketService.printTicket(showtimeId);
                } else {
                    System.out.println("\u001B[31mFailed to create booking.\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mFailed to book the seat. Please try again later.\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mThis seat is not available or does not exist.\u001B[0m");
        }
    }
}