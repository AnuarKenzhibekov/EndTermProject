package Services;

import Repositories.MovieRepository;
import Repositories.UserRepository;
import DataBase.DatabaseManager;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    // Handle user registration process
    static class RegisterUser implements UserActions {
        @Override
        public void handle(String userName, String email) {
            try (DatabaseManager dbManager = new DatabaseManager()) {
                UserRepository userrep = new UserRepository(dbManager.getConnection());

                // Create a new user with the provided username and email
                userrep.createUser(new Entity.User(userName, email));
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mUser successfully created: \u001B[0m\u001B[34m" + userName + "\u001B[0m\u001B[32m!\u001B[0m");
                System.out.println("-------------------------------------------------------------------------");

                // Proceed to display movie details after successful registration
                MoviesService moviesService = new MoviesService();
                moviesService.displayMovieDetails();
                MoviesService.ConsoleMovieQueryStrategy queryStrategy = moviesService.new ConsoleMovieQueryStrategy();
                queryStrategy.askMovie();
            } catch (SQLException e) {
                // Check if the user already exists (handled by the SQL state 23505)
                if ("23505".equals(e.getSQLState())) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mUser already exists.\u001B[0m");
                    handleRegistrationOrLogin();
                    System.out.println("-------------------------------------------------------------------------");
                } else {
                    // Handle general SQL errors
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mError: \u001B[0m\u001B[32m" + e.getMessage() + "\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }
        }
    }

    // Handle user login process
    static class LoginUser implements UserActions {
        @Override
        public void handle(String userName, String email) {
            try (DatabaseManager dbManager = new DatabaseManager()) {
                MovieRepository movierep = new MovieRepository(dbManager.getConnection());
                UserRepository userrep = new UserRepository(dbManager.getConnection());

                // Create a user entity and try to find an existing user from the database
                Entity.User user = new Entity.User(userName, email);
                Entity.User log = userrep.findUser(user.getUserName(), user.getEmail());

                if (log != null) {
                    // Successful login, proceed to movie details
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[32mLogin successful! Welcome back, \u001B[0m\u001B[32m" + userName + "\u001B[0m \uD83D\uDE03");
                    MoviesService moviesService = new MoviesService();
                    moviesService.displayMovieDetails();
                    MoviesService.ConsoleMovieQueryStrategy queryStrategy = moviesService.new ConsoleMovieQueryStrategy();
                    queryStrategy.askMovie();

                } else {
                    // If no user is found with the provided details, show an error
                    System.err.println("\u001B[31mInvalid username or email. Please try again.\u001B[0m");
                }
            } catch (SQLException e) {
                // Handle database errors during login
                System.err.println("\u001B[31mError during database interaction: \u001B[0m\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        }
    }

    // Main method to handle the user flow of registration or login
    public static void handleRegistrationOrLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");

        String choice = "";
        while (true) {
            System.out.print("\u001B[32mRegistration or Login: \u001B[0m");
            choice = scanner.nextLine().trim();

            // Check if the user wants to register or log in
            if (choice.equalsIgnoreCase("Registration")) {
                String name = getValidInput("Enter your name: ");
                String email = getValidInput("Enter your email: ");
                UserActions userAction = new RegisterUser();
                userAction.handle(name, email);
                break;
            } else if (choice.equalsIgnoreCase("Login")) {
                String name = getValidInput("Enter your name: ");
                String email = getValidInput("Enter your email: ");
                UserActions userAction = new LoginUser();
                userAction.handle(name, email);
                break;
            } else {
                // Handle invalid input
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mInvalid input! Please answer 'registration' or 'login' \uD83D\uDE21\u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }

    // Helper method to get valid user input (name, email)
    private static String getValidInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (input.isEmpty()) {
            System.out.print("\u001B[32m" + prompt + "\u001B[0m");
            input = scanner.nextLine().trim();
            // If input is empty, ask the user to enter a valid value
            if (input.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mYou should write something! \uD83D\uDE21 \u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }
        return input;
    }

    // Handle the flow where the user is asked if they want to read reviews
    public static void handleUserFlow() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[32mDo you want to read reviews about our cinema? (yes or no)↓\u001B[0m");
        String readReviews = scanner.nextLine();

        if (readReviews.equalsIgnoreCase("Yes")) {
            // If yes, show reviews
            ReviewsService reviewsService = new ReviewsService();
            reviewsService.fetchReviews();
        } else if (readReviews.equalsIgnoreCase("No")) {
            // If no, proceed to registration/login
            handleRegistrationOrLogin();
        } else {
            // Handle invalid input
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[31mInvalid input! Please answer 'yes' or 'no' \uD83D\uDE21 \u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            handleUserFlow();
        }
    }
}
