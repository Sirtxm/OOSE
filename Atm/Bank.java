package Atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Account> accounts = new ArrayList<>();
    
    public void bankSystem(){
        createAccount();
        loginAccount();
    }
    
    public void createAccount() {
        System.out.print("Enter the number of accounts (1-5): ");
        int numOfAccounts;

        try {
            numOfAccounts = Integer.parseInt(scanner.nextLine());
            if (numOfAccounts < 1 || numOfAccounts > 5) {
                System.out.println("Number of accounts must be between 1 and 5.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        for (int i = 0; i < numOfAccounts; i++) {
            System.out.println("\nCreating Account No. " + (i + 1));

            System.out.print("Enter Account ID (13 characters): ");
            String accountId = scanner.nextLine();

            System.out.print("Enter Name (max 50 characters): ");
            String name = scanner.nextLine();

            System.out.print("Enter Password (4 digits): ");
            String password = scanner.nextLine();

            double balance = 0;
            while (true) {
                System.out.print("Enter Initial Balance (0 - 1,000,000): ");
                try {
                    balance = scanner.nextDouble();
                    scanner.nextLine(); // เคลียร์ buffer หลังรับค่า double
                    if (balance >= 0 && balance <= 1_000_000) {
                        break;
                    } else {
                        System.out.println("Balance must be between 0 and 1,000,000.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // เคลียร์ buffer
                }
            }

            Account account = new Account(accountId, name, password, balance);
            accounts.add(account);
            System.out.println("Account No. " + (i + 1) + " created successfully!");
        }
    }

    
    public void loginAccount() {
        System.out.println("\nATM ComputerThanyaburi Bank");

        System.out.print("Enter Account ID: ");
        String enteredId = scanner.nextLine();

        System.out.print("Enter Account Password: ");
        String enteredPassword = scanner.nextLine();

        boolean loginSuccess = false;
        for (Account account : accounts) {
            if (account.getAccountId().equals(enteredId) && account.verifyPassword(enteredPassword)) {
                loginSuccess = true;
                menu();
                break;
            }
        }

        if (!loginSuccess) {
            System.out.println("\nLogin failed! Incorrect Account ID or Password.");
        }
    }

    public void menu(){
            System.out.println("\nATM ComputerThanyaburi Bank ");
            System.out.println("1. Account Balance");
            System.out.println("2. Withdrawal");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    loginAccount();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    }
}

