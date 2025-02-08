package Entity;

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
        this.number = number;;
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

    public int getMovieId() {
        return movieId;
    }

    @Override
    public String toString() {
        return "\u001B[32mRow: " + row + " | Seat: " + number + " | Hall: " + hallId + "\u001B[0m";
    }
}

