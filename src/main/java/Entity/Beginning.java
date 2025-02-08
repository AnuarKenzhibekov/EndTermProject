package Entity;

import java.util.Scanner;

public class Beginning {

    private final Scanner scanner = new Scanner(System.in);

    public String beginning() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\u001B[32mWelcome! In this system you can buy tickets! Do you want to continue (yes or no)? \u001B[0m");
        String input = scanner.nextLine().trim();
        System.out.println("-------------------------------------------------------------------------");
        return input.toLowerCase();
    }
}
