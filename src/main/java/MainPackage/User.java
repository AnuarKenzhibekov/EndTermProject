package MainPackage;

import Repositories.MovieRepository;
import Repositories.UserRepository;
import java.sql.SQLException;
import java.util.Scanner;

class RegisterUser implements UserActions {
    @Override
    public void handle(String userName, String email) {
        try (DatabaseManager dbManager = new DatabaseManager()) {
            UserRepository userrep = new UserRepository(dbManager.getConnection());
            dbManager.getConnection();
            userrep.createUser(new User(userName, email));
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[32mUser successfully created: \u001B[0m\u001B[34m" + userName + "\u001B[0m\u001B[32m!\u001B[0m");
            System.out.println("-------------------------------------------------------------------------");
            Movies movies = new Movies("daw", "dwda", "dwad");
            movies.displayMovieDetails();
            Movies.ConsoleMovieQueryStrategy queryStrategy = new Movies("daw", "dwda", "dwad").new ConsoleMovieQueryStrategy();
            queryStrategy.askMovie();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mUser already exists.\u001B[0m");
                User.handleRegistrationOrLogin();
                System.out.println("-------------------------------------------------------------------------");
            } else {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mError: \u001B[0m\u001B[32m" + e.getMessage() + "\u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }
}

class LoginUser implements UserActions {
    @Override
    public void handle(String userName, String email) {
        try (DatabaseManager dbManager = new DatabaseManager()) {
            MovieRepository movierep = new MovieRepository(dbManager.getConnection());
            UserRepository userrep = new UserRepository(dbManager.getConnection());

            User user = new User(userName, email);
            User log = userrep.findUser(user.getUserName(), user.getEmail());

            if (log != null) {
                System.out.println("\u001B[32mLogin successful! Welcome back, \u001B[0m\u001B[32m" + userName + "\u001B[0m \uD83D\uDE03");
                Movies movies = new Movies("Title", "Genre", "Duration");
                movies.displayMovieDetails();
                Movies.ConsoleMovieQueryStrategy queryStrategy = new Movies("Title", "Genre", "Duration").new ConsoleMovieQueryStrategy();
                queryStrategy.askMovie();
            } else {
                System.err.println("\u001B[31mInvalid username or email. Please try again.\u001B[0m");
            }
        } catch (SQLException e) {
            System.err.println("\u001B[31mError during database interaction: \u001B[0m\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }
}

public class User {
    private int userID;
    private String userName;
    private String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public static void handleRegistrationOrLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");

        String choice = "";
        while (choice.isEmpty()) {
            System.out.print("\u001B[32mRegistration or Login: \u001B[0m");
            choice = scanner.nextLine().trim();

            if (choice.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mYou should write something! \uD83D\uDE21 \u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }

        String name = "";
        while (name.isEmpty()) {
            System.out.print("\u001B[32mEnter your name: \u001B[0m");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mYou should write something! \uD83D\uDE21 \u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }

        String email = "";
        while (email.isEmpty()) {
            System.out.print("\u001B[32mEnter your email: \u001B[0m");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("\u001B[31mYou should write something! \uD83D\uDE21 \u001B[0m");
                System.out.println("-------------------------------------------------------------------------");
            }
        }

        UserActions userAction;
        if (choice.equalsIgnoreCase("Registration")) {
            userAction = new RegisterUser();
        } else if (choice.equalsIgnoreCase("Login")) {
            userAction = new LoginUser();
        } else {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\u001B[31mInvalid input! Please answer 'registration' or 'login' \uD83D\uDE21\u001B[0m");
            handleRegistrationOrLogin();
            return;
        }

        userAction.handle(name, email);
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
            handleUserFlow();
        }
    }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
