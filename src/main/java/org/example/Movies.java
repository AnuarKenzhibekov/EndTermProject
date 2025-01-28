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

    public void displayAllMovies() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            List<Movies> movies = dbManager.getAllMovies();
            System.out.println("Available Movies:");
            for (Movies movie : movies) {
                System.out.println("Title: " + movie.getTitle() +
                        ", Genre: " + movie.getGenre() +
                        ", Duration: " + movie.getDuration());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
