package Entity;

public class Showtimes {
    private int showtime_id;
    private String date;
    private String showtime;
    private int hallId;

    public Showtimes(String date, String showtime, int hallId) {
        this.hallId = hallId;
        this.date = date;
        this.showtime = showtime;
    }

    @Override
    public String toString() {
        String greenColor = "\u001B[32m";
        String resetColor = "\u001B[0m";
        return greenColor + "Date: " + date + " | Showtime: " + showtime + " | Hall: " + hallId + resetColor;
    }


    public int getShowtime_id() {
        return showtime_id;
    }
    public void setShowtime_id(int showtime_id) {
        this.showtime_id = showtime_id;
    }

    public int getHallId() {
        return hallId;
    }
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }
}
