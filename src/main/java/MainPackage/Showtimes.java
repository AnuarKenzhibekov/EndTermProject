package MainPackage;
import Repositories.ShowtimeRepository;
import java.util.Scanner;
import java.util.List;

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

    public static void askDate(){
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\u001B[32mPlease enter the date:\u001B[0m");
        Scanner scanner = new Scanner(System.in);
        String inputDate = scanner.nextLine();
        if (inputDate.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[31mPlease enter the date!\u001B[0m");
            askDate();
        }
        else {
            askTime();
        }
    }

    public static void askTime(){
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\u001B[32mPlease enter the time:\u001B[0m");
        Scanner scanner = new Scanner(System.in);
        String inputTime = scanner.nextLine();
        if (inputTime.isEmpty()){
            System.out.println("\u001B[31mWrite Time!\u001B[0m");
            askTime();
        }
        else {
            Seats.displayAvailableSeats();
        }
    }


    public static void displayShowtimes(int movieId) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            ShowtimeRepository showtimerep = new ShowtimeRepository(dbManager.getConnection());

            System.out.println("-------------------------------------------------------------------------");
            List<Showtimes> showtimeList = showtimerep.getShowtimesByMovieId(movieId);

            if (showtimeList.isEmpty()) {
                System.out.println("\u001B[31mNo showtimes found for the movie. Please try later!\u001B[0m");
                Movies.ConsoleMovieQueryStrategy queryStrategy = new Movies("Title", "Genre", "Duration").new ConsoleMovieQueryStrategy();
                queryStrategy.askMovie();
            } else {
                for (Showtimes showtime : showtimeList) {
                    System.out.println(showtime);
                }
                askDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
