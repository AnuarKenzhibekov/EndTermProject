package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Movies {
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

    public static void askAboutMovie() {

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
