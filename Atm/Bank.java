package Atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank implements ATMAction {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Account> accounts = new ArrayList<>();
    
    public void bankSystem(){
        boolean isAdminLoggedIn = adminLogin();
        if (!isAdminLoggedIn) {
        System.out.println("Access denied. Exiting the system...");
        return; 
    }
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

            System.out.print("Enter ID card (13 characters): ");
            String id = scanner.nextLine();

            System.out.print("Gender (Male/Female): ");
            String gender = scanner.nextLine();

            double balance = 0;
            while (true) {
                System.out.print("Enter Initial Balance (0 - 1,000,000): ");
                try {
                    balance = scanner.nextDouble();
                    scanner.nextLine(); 
                    if (balance >= 0 && balance <= 1_000_000) {
                        break;
                    } else {
                        System.out.println("Balance must be between 0 and 1,000,000.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); 
                }
            }

            Account account = new Account(accountId, name, password, balance, id , gender );
            accounts.add(account);
            System.out.println("Account No. " + (i + 1) + " created successfully!");
        }
    }

    
    public void loginAccount() {
        System.out.println("\nATM ComputerThanyaburi Bank");

        while(true){
        System.out.print("Enter Account ID: ");
        String enteredId = scanner.nextLine();

        System.out.print("Enter Account Password: ");
        String enteredPassword = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getAccountId().equals(enteredId) && account.verifyPassword(enteredPassword)) {
                System.out.println("\nLogin successful!");
                menu(account);
                return;
            }
        }

        
            System.out.println("\nLogin failed! Incorrect Account ID or Password.");
        }
    }

    public void menu(Account account){
        System.out.println("\nATM ComputerThanyaburi Bank ");
        while(true){
        System.out.println("1. Account Balance");
        System.out.println("2. Withdrawal");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
        System.out.print("Choose: ");
        String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    checkBalance(account);
                    break;
                case "2":
                    withdraw(account );
                    break;
                case "3":
                    deposit(account );
                    break;
                case "4":
                    transfer(account, getAnotherAccount() );
                    break;
                case "5":
                    loginAccount();
                    return;
            }
            scanner.nextLine();
        }
    }
    
    public boolean adminLogin() {
        System.out.println("\n=== Admin Login ===");

        Manager manager = new Manager("1234567890123","John Down","Male","admin","1234");
    
    
        System.out.print("Enter Admin Username: ");
        String enteredUsername = scanner.nextLine();
    
        System.out.print("Enter Admin Password: ");
        String enteredPassword = scanner.nextLine();
    
        if (enteredUsername.equals(manager.getUsername()) && enteredPassword.equals(manager.getPassword())) {
            System.out.println("\nAdmin login successful!");
            return true;
        } else {
            System.out.println("\nLogin failed! Incorrect Admin Username or Password.");
            return false;
        }
    }
    
    @Override
    public void checkBalance(Account account) {
        System.out.println("Your account balance is : " + account.getBalance());
    }

    @Override
    public void withdraw(Account account) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= account.getBalance()) {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            System.out.println("Withdrawal successful. Your new balance is: " + newBalance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    @Override
    public void deposit(Account account) {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

    if (amount > 0) {
        double newBalance = account.getBalance() + amount;
        System.out.println("Deposit successful. Your new balance is: " + newBalance);
        account.setBalance(newBalance);
    } else {
        System.out.println("Invalid deposit amount. Please enter a positive number.");
    }
    }

    @Override
    public void transfer(Account account1,Account account2) {
        if (account2 == null) {
            System.out.println("Transfer aborted. Destination account not found.");
            return; 
        }
        System.out.print("Enter transfer amount : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        if (amount > account1.getBalance()) {
            System.out.println("Transfer failed. Insufficient balance.");
            return;
        }

        double newBalance1 = account1.getBalance() - amount;
        double newBalance2 = account2.getBalance() + amount;

        account1.setBalance(newBalance1);
        account2.setBalance(newBalance2);

        System.out.println("Transfer successful!");
        System.out.println("Your new balance is : "+ newBalance1);
    }

    private Account getAnotherAccount(){
        System.out.print("Enter destination Account ID : ");
        String destinationId = scanner.nextLine();

        for (Account account : accounts){
            if (account.getAccountId().equals(destinationId)) {
                return account;
            }
        }
        
        return null;
    }
}

