package Services;

import Repositories.MovieRepository;
import Repositories.UserRepository;
import DataBase.DatabaseManager;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    static class RegisterUser implements UserActions {
        @Override
        public void handle(String userName, String email) {
            try (DatabaseManager dbManager = new DatabaseManager()) {
                UserRepository userrep = new UserRepository(dbManager.getConnection());
                userrep.createUser(new Entity.User(userName, email));
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[32mUser successfully created: \u001B[0m\u001B[34m" + userName + "\u001B[0m\u001B[32m!\u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
                MoviesService moviesService = new MoviesService();
                moviesService.displayMovieDetails();
                MoviesService.ConsoleMovieQueryStrategy queryStrategy = moviesService.new ConsoleMovieQueryStrategy();
                queryStrategy.askMovie();
            } catch (SQLException e) {
                if ("23505".equals(e.getSQLState())) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mUser already exists.\u001B[0m");
                    handleRegistrationOrLogin();
                    System.out.println("-------------------------------------------------------------------------");
                } else {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[31mError: \u001B[0m\u001B[32m" + e.getMessage() + "\u001B[0m");
                    System.out.println("-------------------------------------------------------------------------");
                }
            }
        }
    }

    static class LoginUser implements UserActions {
        @Override
        public void handle(String userName, String email) {
            try (DatabaseManager dbManager = new DatabaseManager()) {
                MovieRepository movierep = new MovieRepository(dbManager.getConnection());
                UserRepository userrep = new UserRepository(dbManager.getConnection());

                Entity.User user = new Entity.User(userName, email);
                Entity.User log = userrep.findUser(user.getUserName(), user.getEmail());

                if (log != null) {
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.println("\u001B[32mLogin successful! Welcome back, \u001B[0m\u001B[32m" + userName + "\u001B[0m \uD83D\uDE03");
                    MoviesService moviesService = new MoviesService();
                    moviesService.displayMovieDetails();
                    MoviesService.ConsoleMovieQueryStrategy queryStrategy = moviesService.new ConsoleMovieQueryStrategy();
                    queryStrategy.askMovie();

                } else {
                    System.err.println("\u001B[31mInvalid username or email. Please try again.\u001B[0m");
                }
            } catch (SQLException e) {
                System.err.println("\u001B[31mError during database interaction: \u001B[0m\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        }
    }


    public static void handleRegistrationOrLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");

        String choice = "";
        while (true) {
            System.out.print("\u001B[32mRegistration or Login: \u001B[0m");
            choice = scanner.nextLine().trim();

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
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mInvalid input! Please answer 'registration' or 'login' \uD83D\uDE21\u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }

    private static String getValidInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (input.isEmpty()) {
            System.out.print("\u001B[32m" + prompt + "\u001B[0m");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mYou should write something! \uD83D\uDE21 \u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }
        return input;
    }


    public static void handleUserFlow() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[32mDo you want to read reviews about our cinema? (yes or no)↓\u001B[0m");
        String readReviews = scanner.nextLine();

        if (readReviews.equalsIgnoreCase("Yes")) {
            ReviewsService reviewsService = new ReviewsService();
            reviewsService.fetchReviews();
        } else if (readReviews.equalsIgnoreCase("No")) {
            handleRegistrationOrLogin();
        } else {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[31mInvalid input! Please answer 'yes' or 'no' \uD83D\uDE21 \u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            handleUserFlow();
        }
    }
}
