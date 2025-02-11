import Entity.Beginning;
import Services.UserService;

public class
Main {
    public static void main(String[] args) {
        // Create an instance of Beginning and start the process
        Beginning beg = new Beginning();
        String start = beg.beginning();

        // Check the user's input and proceed accordingly
        if (start.equalsIgnoreCase("Yes")) {
            // If the user agrees, proceed with the user flow
            UserService.handleUserFlow();
        } else if (start.equalsIgnoreCase("No")) {
            // If the user declines, display a goodbye message
            System.out.println("\u001B[32mOkay! Bye \uD83D\uDE22 \u001B[0m");
        } else {
            // If the input is invalid, prompt the user again
            System.out.println("\u001B[31mInvalid input! Please answer 'yes' or 'no' \uD83D\uDE21 \u001B[0m");
            main(args);  // Restart the main method for correct input
        }
    }
}
