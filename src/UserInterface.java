import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final Account user;
    private final Slots slotMachine;

    public UserInterface(Scanner scanner, Account user) {
        this.scanner = scanner;
        this.user = user;
        this.slotMachine = new Slots();
    }

    public void start() {
        createAccount();

        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    playSlots();
                    break;
                case "2":
                    manageAccount();
                    break;
                case "3":
                    System.out.println("Thank you for playing!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createAccount() {
        System.out.println("\n=== CREATE ACCOUNT ===");
        user.setUsername(scanner);
        System.out.println("Account created successfully!");
        System.out.println(user.getAccountInfo());
    }

    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Play Slots");
        System.out.println("2. Account Management");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public void playSlots() {
        System.out.println("\n=== SLOTS GAME ===");
        System.out.println(user.getAccountInfo());

        while (true) {
            System.out.print("Enter bet (Min: 10, Max: " + user.getBalance() + ") or 'q' to quit: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                return; // Exit slots game
            }

            try {
                double betAmount = Double.parseDouble(input);

                if (betAmount < 10) {
                    System.out.println("Minimum bet is 10. Try again.");
                    continue;
                }
                if (!user.hasFunds(betAmount)) {
                    System.out.println("Insufficient funds. You have: " + user.getBalance());
                    continue;
                }

                if (user.withdraw(betAmount)) {
                    slotMachine.spinWithDelay(); // Uses the 2-second wait
                    slotMachine.printSlots();
                    double winnings = slotMachine.checkWins(betAmount);

                    if (winnings > 0) {
                        user.deposit(winnings);
                        System.out.println("🎉 You won: $" + winnings);
                    } else {
                        System.out.println("💸 No wins this time!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number or 'q' to quit.");
            }
        }
    }

    private void manageAccount() {
        while (true) {
            System.out.println("\n=== ACCOUNT MANAGEMENT ===");
            System.out.println(user.getAccountInfo());
            System.out.println("1. Deposit Money");
            System.out.println("2. View Balance");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    depositMoney();
                    break;
                case "2":
                    viewBalance();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void depositMoney() {
        System.out.print("\nEnter deposit amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            user.deposit(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
        }
    }

    private void viewBalance() {
        System.out.println("\n" + user.getAccountInfo());
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}