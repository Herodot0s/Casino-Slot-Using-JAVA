import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account user = new Account();
        UserInterface ui = new UserInterface(scanner, user);
        ui.start();
        scanner.close();
    }
}