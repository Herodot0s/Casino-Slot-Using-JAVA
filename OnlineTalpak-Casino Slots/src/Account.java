import java.util.Scanner;

public class Account {
    private double balance;
    private String username;

    public Account() {
        this.balance = 250.0; // Starting balance
        this.username = "";
    }

    public void setUsername(Scanner scanner) {
        System.out.print("What is your desired username?: ");
        this.username = scanner.nextLine();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            this.balance += amount;
            System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, balance);
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
            return false;
        } else {
            this.balance -= amount;
            System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, balance);
            return true;
        }
    }

    public boolean hasFunds(double amount) {
        return this.balance >= amount;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountInfo() {
        return String.format("Account: %s | Balance: $%.2f", username, balance);
    }
}