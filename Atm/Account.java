package Atm;

public class Account {
    private String accountId;
    private String name;
    private String password;
    private double balance;

    public Account(String accountId, String name, String password, double balance) {
        try{
            if (!accountId.matches("\\d{13}")) {
                throw new IllegalArgumentException("Account ID must be 13 characters long.");
            }
            if (!name.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Name must not exceed 50 characters.");
            }
            if (!password.matches("\\d{4}")) {
                throw new IllegalArgumentException("Password must be exactly 4 characters.");
            }
            if (balance < 0 || balance > 1_000_000) {
                throw new IllegalArgumentException("Balance must be between 0 and 1,000,000.");
            }

            this.accountId = accountId;
            this.name = name;
            this.password = password;
            this.balance = balance;

        }catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        
    }
    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

}

