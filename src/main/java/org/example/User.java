package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class User {
    // Create attributes
    private int userID;
    private String UserName;
    private String Email;

    // Initialization
    public User(String UserName, String Email) {
        this.UserName = UserName;
        this.Email = Email;
    }

    // Registration method
    public void userRegistration(String getUserName, String getEmail) {
        try (DatabaseManager dbManager = new DatabaseManager()) {
            dbManager.connect();
            User user = new User(getUserName, getEmail);
            User createdUser = dbManager.createUser(user);
            System.out.println("\u001B[32mUser successfully created: \u001B[0m\u001B[34m" + UserName + "\u001B[0m\u001B[32m!\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
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
            if (log != null) {
                System.out.println("\u001B[32mLogin successful! Welcome back, \u001B[0m\u001B[32m" + UserName + "\u001B[0m \uD83D\uDE03");
            } else {
                System.err.println("\u001B[31mInvalid username or email. Please try again.\u001B[0m");
            }
        } catch (SQLException e) {
            System.err.println("\u001B[31mError during database interaction: \u001B[0m\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }


    // Getters and setters for user
    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    public String getUserName() { return UserName; }
    public void setUserName(String firstName) {this.UserName = UserName; }
    public String getEmail() { return Email; }
    public void setEmail(String lastName) { this.Email = Email; }
}

