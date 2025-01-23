package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Beginning beg = new Beginning();
        String start = beg.beginning();
        String a2 = null;
        if (start.equals("Yes")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("\u001B[32mDo you want to read reviews about our cinema?↓\u001B[0m");
            String a1 = scanner.nextLine();
            a2 = null;
            if (a1.equals("No")) {
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------------");
                System.out.print("\u001B[32mRegistration or Login: \u001B[0m");
                a2 = scanner.nextLine();
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------------");
            } else if (a1.equals("Yes")) {
            }


            // Registration logic
            if (a2.equals("Registration")) {
                System.out.println(" ");
                System.out.print("\u001B[32mEnter your name: \u001B[0m");
                String userName = scanner.nextLine();
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------------");
                System.out.println(" ");
                System.out.print("\u001B[32mEnter your email: \u001B[0m");
                String email = scanner.nextLine();
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------------");
                User user = new User(userName, email);
                user.userRegistration();
            }
            // Login logic
            else if (a2.equals("Login")) {
                System.out.println(" ");
                System.out.print("\u001B[32mEnter your name: \u001B[0m");
                String userName = scanner.nextLine();
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------------");
                System.out.println(" ");
                System.out.print("\u001B[32mEnter your email: \u001B[0m");
                String email = scanner.nextLine();
                System.out.println(" ");
                System.out.println("-------------------------------------------------------------------------");
                User user = new User(userName, email);
                user.userLogin(userName, email);
            } else if (start.equals("No")) {
                System.out.println(" ");
                System.out.println("\u001B[32mOkey! bye :(\u001B[0m");
                System.out.println(" ");
            }
        }
    }
}

