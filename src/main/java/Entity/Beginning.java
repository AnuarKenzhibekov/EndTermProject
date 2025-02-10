package Entity;

import java.util.Scanner;

public class Beginning {

    // Scanner instance for user input
    private final Scanner scanner = new Scanner(System.in);

    public String beginning() {
        // Display a welcome message and prompt the user for input
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\u001B[32mWelcome! In this system you can buy tickets! Do you want to continue (yes or no)? \u001B[0m");

        // Read and trim the user's input
        String input = scanner.nextLine().trim();

        System.out.println("-------------------------------------------------------------------------");

        // Convert input to lowercase for easier comparison and return it
        return input.toLowerCase();
    }
}
