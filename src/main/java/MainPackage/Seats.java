package MainPackage;
import Repositories.SeatsRepository;

import java.util.List;

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

    public static void displayAvailableSeats() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            SeatsRepository seatsrep = new SeatsRepository(dbManager.getConnection());

            List<Seats> availableSeats = seatsrep.getAllAvailableSeats();

            if (availableSeats.isEmpty()) {
                System.out.println("\u001B[31mNo available seats found.\u001B[0m");
            } else {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mAvailable Seats:\u001B[0m");
                for (Seats seat : availableSeats) {
                    System.out.println("Row: " + seat.getRow() + " | Number: " + seat.getNumber() + " | Status: " + seat.getStatus());
                }
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

