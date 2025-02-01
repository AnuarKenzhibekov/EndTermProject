package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movies {
    private int movieId;
    private String title;
    private String genre;
    private String duration;

    public Movies(String title, String genre, String duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public static void displayAllMovies() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            List<Movies> movies = dbManager.getAllMovies();
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

    public static void askMovie() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            Scanner scanner = new Scanner(System.in);
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mWhat movie do you want? (write number)↓\u001B[0m");

            String askMovie = scanner.nextLine();

            if (askMovie.matches("\\d+")) {
                int movieId = Integer.parseInt(askMovie);
                Movies movie = dbManager.getMovieById(movieId);

                if (movie != null) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[32mYour movie: " + movie.getTitle() + "\u001B[0m");
                } else {
                    System.out.println("\u001B[33mMovie not found!\u001B[0m");
                }
            } else {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[33mWrite Number!\u001B[0m");
                Movies.askMovie();
            }

            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int getMovieId() {
        return movieId;
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
