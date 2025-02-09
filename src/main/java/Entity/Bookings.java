package Entity;

public class Bookings {
    private static int bookingId;
    private int userId;
    private int showtimeId;
    private int seatId;
    private int hallId;
    private String status;

    private Movies movie;
    private Seats seat;

    public Bookings(int bookingId, int userId, int showtimeId, int seatId, int hallId, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
        this.hallId = hallId;
        this.status = status;
    }

    public static int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
