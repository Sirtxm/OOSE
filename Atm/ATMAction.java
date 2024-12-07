package Atm;

public interface ATMAction {
    void checkBalance(Account account); 
    void withdraw(Account account); 
    void deposit(Account account); 
    void transfer(Account account1,Account account2); 
}
