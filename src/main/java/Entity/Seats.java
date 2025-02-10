package Entity;

public class Seats {
    // Fields representing seat properties
    private int seatId;
    private int showtimeId;
    private int hallId;
    private int number;
    private String row;
    private String status;
    private int movieId;

    // Constructor to initialize seat details
    public Seats(int seatId, int showtimeId, int hallId, int number, String row, String status, int movieId) {
        this.seatId = seatId;
        this.showtimeId = showtimeId;
        this.hallId = hallId;
        this.number = number;
        this.row = row;
        this.status = status;
        this.movieId = movieId;
    }

    // Getter method for seat ID
    public int getSeatId() {
        return seatId;
    }

    // Setter method for seat ID
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    // Getter method for showtime ID
    public int getShowtimeId() {
        return showtimeId;
    }

    // Setter method for showtime ID
    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    // Getter method for hall ID
    public int getHallId() {
        return hallId;
    }

    // Setter method for hall ID
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    // Getter method for seat number
    public int getNumber() {
        return number;
    }

    // Setter method for seat number
    public void setNumber(int number) {
        this.number = number;
    }

    // Getter method for row
    public String getRow() {
        return row;
    }

    // Setter method for row
    public void setRow(String row) {
        this.row = row;
    }

    // Getter method for seat status
    public String getStatus() {
        return status;
    }

    // Setter method for seat status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter method for movie ID
    public int getMovieId() {
        return movieId;
    }

    // Override toString method to represent seat details in a formatted way
    @Override
    public String toString() {
        return "\u001B[32mRow: " + row + " | Seat: " + number + " | Hall: " + hallId + "\u001B[0m";
    }
}
