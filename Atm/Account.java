package Atm;

public class Account extends Person{
    private String accountId;
    private String password;
    private double balance;

    public Account(String accountId, String name, String password, double balance, String id, String gender ) {
            super(id, name, gender);
            validateAccountId(accountId);
            this.accountId = accountId;
            validatePassword(password);
            this.password = password;
            validateBalance(balance);
            this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance){
        validateBalance(balance);
        this.balance = balance; 
    }

    public boolean verifyPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
    
    private void validateAccountId(String accountId){
        if (!accountId.matches("\\d{13}")) {
            throw new IllegalArgumentException("Account ID must be 13 characters long.");
        }
    }

    private void validatePassword(String password){
        if (!password.matches("\\d{4}")) {
            throw new IllegalArgumentException("Password must be exactly 4 characters.");
        }
    }

    private void validateBalance(double balance){
        if (balance < 0 || balance > 1_000_000) {
            throw new IllegalArgumentException("Balance must be between 0 and 1,000,000.");
        }
    }
}

