package org.example;

import java.util.Scanner;

public class Reviews {
    private String userName;
    private String feedback;
    private int grade;

    public Reviews(String userName, String feedback, int grade) {
        this.userName = userName;
        this.feedback = feedback;
        this.grade = grade;
    }


    public static void fetchReviews() {
        Scanner scanner = new Scanner(System.in);
        int userId = 1;

        while (true) {
            try (DatabaseManager dbManager = new DatabaseManager()) {
                Reviews review = dbManager.getReviewByUserId(userId);

                if (review != null) {  // If review exists
                    System.out.println("-------------------------------------------");
                    System.out.println("\u001B[32mUser: \u001B[0m\u001B[32m" + review.getUserName() + "\u001B[0m");
                    System.out.println("\u001B[32mFeedback: \u001B[0m\u001B[32m" + review.getFeedback() + "\u001B[0m");
                    System.out.println("\u001B[32mOverall Rating: \u001B[0m\u001B[32m" + review.getGrade() + "\u001B[0m");

                    // Ask for next review or exit
                    System.out.println("-------------------------------------------");
                    System.out.println("\u001B[32mWrite 'next' to get the next review or 'exit' to exit ↓\u001B[0m");
                    String input = scanner.nextLine();

                    if ("next".equalsIgnoreCase(input)) {
                        userId++;  // Increment userID for the next review
                    } else if ("exit".equalsIgnoreCase(input)) {
                        System.out.println("-------------------------------------------");
                        break;  // Exit the loop and go back to main menu
                    } else {
                        System.out.println("-------------------------------------------");
                        System.out.println("\u001B[31mInvalid input. Please type 'next' or 'exit'\uD83D\uDE21\u001B[0m");
                    }
                } else {
                    System.out.println("-------------------------------------------");
                    System.out.println("\u001B[31mSorry, but you have seen all reviews! \uD83D\uDE22\u001B[0m");
                    System.out.println("-------------------------------------------");
                    break;  // Exit if no more reviews are available
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        User.handleUserFlow();  // After finishing review fetch, return to user flow
    }
    public String getUserName() {
        return userName;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getGrade() {
        return grade;
    }
}
