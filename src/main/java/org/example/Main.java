package org.example;

import java.util.Scanner;

public class Main {
    public static int userId = 1;

    public static void main(String[] args) {
        Beginning beg = new Beginning();
        String start = beg.beginning();

        if (start.equalsIgnoreCase("Yes")) {
            handleUserFlow();
        } else if (start.equalsIgnoreCase("No")) {
            System.out.println("\u001B[32mOkay! Bye \uD83D\uDE22 \u001B[0m");
        } else {
            System.out.println("\u001B[31mInvalid input! Please answer 'yes' or 'no'.\u001B[0m");
            main(args);  // Restart the main method for correct input
        }
    }

    private static void handleUserFlow() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\u001B[32mDo you want to read reviews about our cinema? (yes or no)↓\u001B[0m");
        String readReviews = scanner.nextLine();

        if (readReviews.equalsIgnoreCase("Yes")) {
            fetchReviews(userId);
        } else if (readReviews.equalsIgnoreCase("No")) {
            handleRegistrationOrLogin();
        } else {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[31mInvalid input! Please answer 'yes' or 'no'.\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            handleUserFlow();  // Retry if invalid input
        }
    }

    private static void handleRegistrationOrLogin() {
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
                    System.out.println("\u001B[31mYou should write something!\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }

            String email = "";
            while (email.isEmpty()) {
                System.out.print("\u001B[32mEnter your email: \u001B[0m");
                email = scanner.nextLine();
                if (email.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mYou should write something!\u001B[0m");
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
                    System.out.println("\u001B[31mYou should write something!\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }

            String email = "";
            while (email.isEmpty()) {
                System.out.print("\u001B[32mEnter your email: \u001B[0m");
                email = scanner.nextLine();
                if (email.isEmpty()) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mYou should write something!\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }

            System.out.println("-------------------------------------------------------------------------");

            User user = new User(name, email);
            user.userLogin(name, email);

        } else {
            System.out.println("\u001B[31mInvalid input! Please answer 'registration' or 'login'.\u001B[0m");
            handleRegistrationOrLogin();
        }
    }


    public static void fetchReviews(int userID) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try (DatabaseManager dbManager = new DatabaseManager()) {
                Reviews review = dbManager.getReviewByUserId(userID);

                if (review != null) {  // If review exists
                    System.out.println("-------------------------------------------");
                    System.out.println("\u001B[32mUser: \u001B[0m\u001B[32m" + review.getUserName() + "\u001B[0m");
                    System.out.println("\u001B[32mFeedback: \u001B[0m\u001B[32m" + review.getFeedback() + "\u001B[0m");
                    System.out.println("\u001B[32mOverall Rating: \u001B[0m\u001B[32m" + review.getGrade() + "\u001B[0m");

                    // Ask for next review or exit
                    System.out.println("-------------------------------------------");
                    System.out.println("\u001B[32mWrite 'next' to get the next review or 'exit' to exit.↓\u001B[0m");
                    String input = scanner.nextLine();

                    if ("next".equalsIgnoreCase(input)) {
                        userID++;  // Increment userID for the next review
                    } else if ("exit".equalsIgnoreCase(input)) {
                        System.out.println("-------------------------------------------");
                        break;  // Exit the loop and go back to main menu
                    } else {
                        System.out.println("-------------------------------------------");
                        System.out.println("\u001B[31mInvalid input. Please type 'next' or 'exit'.\u001B[0m");
                    }
                } else {
                    System.out.println("-------------------------------------------");
                    System.out.println("\u001B[31mSorry, but you have seen all reviews!\u001B[0m");
                    System.out.println("-------------------------------------------");
                    break;  // Exit if no more reviews are available
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        handleUserFlow();  // After finishing review fetch, return to user flow
    }
}
