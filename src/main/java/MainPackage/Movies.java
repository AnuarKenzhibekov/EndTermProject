package MainPackage;

import Repositories.MovieRepository;
import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class Movies implements MovieActions{
    private int movieId;
    private String title;
    private String genre;
    private String duration;

    public Movies(String title, String genre, String duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public void displayMovieDetails() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            dbManager.getConnection();
            MovieRepository movierep = new MovieRepository(dbManager.getConnection());

            List<Movies> movies = movierep.getAllMovies();
            System.out.println("\u001B[32mAvailable Movies:\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            for (Movies movie : movies) {
                System.out.println("\u001B[34mTitle: " + movie.getTitle() +
                        ", Genre: " + movie.getGenre() +
                        ", Duration: " + movie.getDuration() + "\u001B[0m");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public class ConsoleMovieQueryStrategy implements MovieQueryStrategy {
        public void askMovie() {
            try{
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.getConnection();
                MovieRepository movierep = new MovieRepository(dbManager.getConnection());
                Scanner scanner = new Scanner(System.in);

                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mWhat movie do you want? (write number) ↓\u001B[0m");

                String askMovie;
            while (true) {
                askMovie = scanner.nextLine();

                if (askMovie.matches("\\d+")) {
                    int movieId = Integer.parseInt(askMovie);

                    Movies movie = movierep.getMovieById(movieId);
                    if (movie != null) {
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("\u001B[32mYour movie: " + movie.getTitle() + "\u001B[0m");
                        Showtimes.displayShowtimes(movieId);
                        break;
                    } else {
                        System.out.println("\u001B[33mMovie not found!\u001B[0m");
                        break;
                    }
                } else {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[33mWrite a valid Number!\u001B[0m");
                    askMovie();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
