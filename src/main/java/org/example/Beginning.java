package org.example;

import java.util.Scanner;

public class Beginning {
    public String beginning() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\u001B[32mWelcome! In this system you can buy tickets! Do you want to continue (yes or no)? \u001B[0m");
        String input = scanner.nextLine();
        System.out.println("-------------------------------------------------------------------------");
        return input;
    }
}
