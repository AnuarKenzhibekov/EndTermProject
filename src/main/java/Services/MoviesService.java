package Services;

import Entity.Movies;
import DataBase.DatabaseManager;
import Repositories.MovieRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MoviesService implements MovieActions {

    // Display all available movies
    @Override
    public void displayMovieDetails() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            MovieRepository movierep = new MovieRepository(dbManager.getConnection());

            List<Movies> movies = movierep.getAllMovies();
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mAvailable Movies:\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            // Print each movie's details
            for (Movies movie : movies) {
                System.out.println("\u001B[34mTitle: " + movie.getTitle() +
                        ", Genre: " + movie.getGenre() +
                        ", Duration: " + movie.getDuration() + "\u001B[0m");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Inner class for querying movies via console input
    public class ConsoleMovieQueryStrategy implements MovieQueryStrategy {

        // Ask the user for a movie number and display its showtimes
        public void askMovie() {
            try {
                DatabaseManager dbManager = new DatabaseManager();
                MovieRepository movierep = new MovieRepository(dbManager.getConnection());
                Scanner scanner = new Scanner(System.in);

                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mWhat movie do you want? (write number) ↓\u001B[0m");

                String askMovie;
                while (true) {
                    askMovie = scanner.nextLine();

                    // Check if input is a valid number
                    if (askMovie.matches("\\d+")) {
                        int movieId = Integer.parseInt(askMovie);

                        // Fetch and display movie details
                        Movies movie = movierep.getMovieById(movieId);
                        if (movie != null) {
                            System.out.println("-------------------------------------------------------------------------");
                            System.out.println("\u001B[32mYour movie: " + movie.getTitle() + "\u001B[0m");
                            ShowtimesService showtimesService = new ShowtimesService();
                            showtimesService.displayShowtimes(movieId);
                            break;
                        } else {
                            System.out.println("\u001B[33mMovie not found!\u001B[0m");
                            break;
                        }
                    } else {
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("\u001B[33mWrite a valid Number!\u001B[0m");
                        askMovie(); // Retry if input is not a number
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
