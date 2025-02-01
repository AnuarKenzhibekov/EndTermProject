package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Beginning beg = new Beginning();
        String start = beg.beginning();

        if (start.equalsIgnoreCase("Yes")) {
            User.handleUserFlow();
        } else if (start.equalsIgnoreCase("No")) {
            System.out.println("\u001B[32mOkay! Bye \uD83D\uDE22 \u001B[0m");
        } else {
            System.out.println("\u001B[31mInvalid input! Please answer 'yes' or 'no' \uD83D\uDE21 \u001B[0m");
            main(args);  // Restart the main method for correct input
        }
    }



}
