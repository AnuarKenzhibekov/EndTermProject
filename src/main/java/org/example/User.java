package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class User {
    // Create attributes
    private String UserName;
    private String Email;

    // Initialization
    public User(String UserName, String Email) {
        this.UserName = UserName;
        this.Email = Email;
    }

    // Registration method
    public void userRegistration() {
        try (DatabaseManager dbManager = new DatabaseManager()) {
            dbManager.connect();
            User user = dbManager.createUser(UserName, Email);
            System.out.println("\u001B[32mUser successfully created: \u001B[0m" + UserName + "\u001B[!\u001B[0m");
        } catch (SQLException e) {
            // Uniqueness error
            if ("23505".equals(e.getSQLState())) {
                System.err.println("User already exists.");
            } else {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    // Login method
    public void userLogin(String UserName, String Email) {
        try (DatabaseManager dbManager = new DatabaseManager()) {
            dbManager.connect();
            User log = dbManager.findUser(UserName, Email);
            if (log != null) {
                System.out.println("Login successful! Welcome back, " + UserName);
            } else {
                System.err.println("Invalid username or email. Please try again.");
            }
        } catch (SQLException e) {
            System.err.println("Error during database interaction: " + e.getMessage());
        }
    }


    // Getters and setters for user
    public String getUserName() { return UserName; }
    public void setUserName(String firstName) {this.UserName = UserName; }
    public String getEmail() { return Email; }
    public void setEmail(String lastName) { this.Email = Email; }
}

