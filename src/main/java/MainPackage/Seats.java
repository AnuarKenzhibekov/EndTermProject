package MainPackage;
import Repositories.SeatsRepository;

import java.util.*;

public class Seats {
    private int seatId;
    private int showtimeId;
    private int hallId;
    private int number;
    private String row;
    private String status;
    private int movieId;

    public Seats(int seatId, int showtimeId, int hallId, int number, String row, String status, int movieId) {
        this.seatId = seatId;
        this.showtimeId = showtimeId;
        this.hallId = hallId;
        this.number = number;
        this.row = row;
        this.status = status;
        this.movieId = movieId;
    }

    public static void displayAvailableSeats(int showtimeId) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            SeatsRepository seatsrep = new SeatsRepository(dbManager.getConnection());

            List<Seats> availableSeats = seatsrep.getAllAvailableSeats(showtimeId);

            if (availableSeats.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mNo available seats found.\u001B[0m");
                Showtimes.askShowtime();
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
                    Movies.ConsoleMovieQueryStrategy queryStrategy = new Movies("Title", "Genre", "Duration").new ConsoleMovieQueryStrategy();
                    Movies movies = new Movies("Title", "Genre", "Duration");
                    movies.displayMovieDetails();
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


    public static void askAndBookSeat(int showtimeId) {
        try {
            Scanner scanner = new Scanner(System.in);
            DatabaseManager dbManager = new DatabaseManager();
            SeatsRepository seatsrep = new SeatsRepository(dbManager.getConnection());

            System.out.println("\u001B[32mChoose a row (a,b):\u001B[0m");
            String row = scanner.nextLine().trim();

            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mChoose a seat number:\u001B[0m");
            int number = Integer.parseInt(scanner.nextLine().trim());

            Seats seat = seatsrep.getSeatByRowAndNumber(showtimeId, row, number);

            if (seat != null && "available".equals(seat.getStatus())) {
                boolean isBooked = seatsrep.bookSeat(seat.getSeatId());
                if (isBooked) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[32mSeat booked successfully!\u001B[0m");
                } else {
                    System.out.println("\u001B[31mFailed to book the seat. Please try again later.\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mThis seat is not available or does not exist.\u001B[0m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

