package org.example;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class User {
    // Create attributes
    private static int userID;
    private String userName;
    private String email;

    // Initialization
    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    // Registration method
    public void userRegistration(String getUserName, String getEmail) {
        try (DatabaseManager dbManager = new DatabaseManager()) {
            dbManager.connect();
            User user = new User(getUserName, getEmail);
            User createdUser = dbManager.createUser(user);
            System.out.println("\u001B[32mUser successfully created: \u001B[0m\u001B[34m" + userName + "\u001B[0m\u001B[32m!\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            Movies.displayAllMovies();
            Movies.askMovie();
        } catch (SQLException e) {
            // Uniqueness error
            if ("23505".equals(e.getSQLState())) {
                System.out.println("-------------------------------------------------------------------------");
                System.err.println("\u001B[31mUser already exists.\u001B[0m");
            } else {
                System.out.println("-------------------------------------------------------------------------");
                System.err.println("\u001B[31mError: \u001B[0m\u001B[32m" + e.getMessage() + "\u001B[0m");
            }
        }
    }

    // Login method
    public void userLogin(String getUserName, String getEmail) {
        try (DatabaseManager dbManager = new DatabaseManager()) {
            dbManager.connect();
            User user = new User(getUserName, getEmail);
            User log = dbManager.findUser(user);

            List<Movies> movies = dbManager.getAllMovies();
            if (log != null) {
                System.out.println("\u001B[32mLogin successful! Welcome back, \u001B[0m\u001B[32m" + userName + "\u001B[0m \uD83D\uDE03");
                System.out.println("-------------------------------------------------------------------------");

                if (movies.isEmpty()) {
                    System.out.println("\u001B[33mNo movies available.\u001B[0m");
                } else {
                    Movies.displayAllMovies();
                    Movies.askMovie();
                }
            } else {
                System.err.println("\u001B[31mInvalid username or email. Please try again.\u001B[0m");
            }
        } catch (SQLException e) {
            System.err.println("\u001B[31mError during database interaction: \u001B[0m\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    public static void handleRegistrationOrLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------------");
        System.out.print("\u001B[32mRegistration or Login: \u001B[0m");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Registration")) {
            System.out.println("-------------------------------------------------------------------------");

            String name = "";
            while (name.isEmpty()) {
                System.out.print("\u001B[32mEnter your name: \u001B[0m");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mYou should write something!\uD83D\uDE21 \u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }

            String email = "";
            while (email.isEmpty()) {
                System.out.print("\u001B[32mEnter your email: \u001B[0m");
                email = scanner.nextLine();
                if (email.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mYou should write something!\uD83D\uDE21\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }

            System.out.println("-------------------------------------------------------------------------");

            User user = new User(name, email);
            user.userRegistration(name, email);

        } else if (choice.equalsIgnoreCase("Login")) {
            System.out.println("-------------------------------------------------------------------------");

            String name = "";
            while (name.isEmpty()) {
                System.out.print("\u001B[32mEnter your name: \u001B[0m");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mYou should write something!\uD83D\uDE21\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }

            String email = "";
            while (email.isEmpty()) {
                System.out.print("\u001B[32mEnter your email: \u001B[0m");
                email = scanner.nextLine();
                if (email.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mYou should write something!\uD83D\uDE21\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }

            System.out.println("-------------------------------------------------------------------------");

            User user = new User(name, email);
            user.userLogin(name, email);

        } else {
            System.out.println("\u001B[31mInvalid input! Please answer 'registration' or 'login' \uD83D\uDE21\u001B[0m");
            handleRegistrationOrLogin();
        }
    }

    public static void handleUserFlow() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\u001B[32mDo you want to read reviews about our cinema? (yes or no)↓\u001B[0m");
        String readReviews = scanner.nextLine();

        if (readReviews.equalsIgnoreCase("Yes")) {
            Reviews.fetchReviews();
        } else if (readReviews.equalsIgnoreCase("No")) {
            handleRegistrationOrLogin();
        } else {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[31mInvalid input! Please answer 'yes' or 'no' \uD83D\uDE21 \u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            handleUserFlow();  // Retry if invalid input
        }
    }


    // Getters and setters for user
    public static int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    public String getUserName() { return userName; }
    public void setUserName(String firstName) {this.userName = userName; }
    public String getEmail() { return email; }
    public void setEmail(String lastName) { this.email = email; }
}
