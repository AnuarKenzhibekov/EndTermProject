package Services;

import Entity.Showtimes;

import Repositories.ShowtimeRepository;
import DataBase.DatabaseManager;
import java.util.List;
import java.util.Scanner;

public class ShowtimesService implements ShowtimesActions {
    public void askShowtime() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mChoose your showtime (write number) ↓\u001B[0m");
            String inputShowtime = scanner.nextLine();

            if (inputShowtime.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mWrite number!\u001B[0m");
                continue;
            }

            try {
                int showtimeId = Integer.parseInt(inputShowtime);
                SeatsService seatsService = new SeatsService();
                seatsService.displayAvailableSeats(showtimeId);
                break;
            } catch (NumberFormatException e) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mInvalid input! Please enter a valid number.\u001B[0m");
            }
        }
    }



    public void displayShowtimes(int movieId) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            ShowtimeRepository showtimerep = new ShowtimeRepository(dbManager.getConnection());

            System.out.println("-------------------------------------------------------------------------");
            List<Showtimes> showtimeList = showtimerep.getShowtimesByMovieId(movieId);

            if (showtimeList.isEmpty()) {
                System.out.println("\u001B[31mNo showtimes found for the movie. Please try later!\u001B[0m");
                MoviesService moviesService = new MoviesService();
                MoviesService.ConsoleMovieQueryStrategy queryStrategy = moviesService.new ConsoleMovieQueryStrategy();
                queryStrategy.askMovie();
            } else {
                for (Showtimes showtime : showtimeList) {
                    System.out.println(showtime);
                }
                askShowtime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
