package Entity;

public class Showtimes {
    // Fields representing showtime details
    private int showtime_id;
    private String date;
    private String showtime;
    private int hallId;

    // Constructor to initialize showtime details
    public Showtimes(String date, String showtime, int hallId) {
        this.hallId = hallId;
        this.date = date;
        this.showtime = showtime;
    }

    // Override toString method to return a formatted showtime string
    @Override
    public String toString() {
        String greenColor = "\u001B[32m";  // Color formatting for the output
        String resetColor = "\u001B[0m";  // Reset color
        return greenColor + "Date: " + date + " | Showtime: " + showtime + " | Hall: " + hallId + resetColor;
    }

    // Getter method for showtime ID
    public int getShowtime_id() {
        return showtime_id;
    }

    // Setter method for showtime ID
    public void setShowtime_id(int showtime_id) {
        this.showtime_id = showtime_id;
    }

    // Getter method for hall ID
    public int getHallId() {
        return hallId;
    }

    // Setter method for hall ID
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    // Getter method for date
    public String getDate() {
        return date;
    }

    // Setter method for date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter method for showtime
    public String getShowtime() {
        return showtime;
    }

    // Setter method for showtime
    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }
}
