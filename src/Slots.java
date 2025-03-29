import java.util.Random;

public class Slots {
    private String[][] reels;
    private final Symbol[] symbols = {
            new Symbol("⭐", 1, 0.5),
            new Symbol("🍒", 2, 1),
            new Symbol("🍋", 3, 1.5),
            new Symbol("🍉", 4, 2),
            new Symbol("🍇", 5, 3),
            new Symbol("🔔", 6, 5),
            new Symbol("🍀", 7, 7),
            new Symbol("💎", 8, 10),
            new Symbol("❤️", 9, 15),
            new Symbol("🍑", 10, 20)
    };

    public Slots() {
        reels = new String[3][3];
        spin();
    }

    private static class Symbol {
        String emoji;
        int id;
        double multiplier;

        Symbol(String emoji, int id, double multiplier) {
            this.emoji = emoji;
            this.id = id;
            this.multiplier = multiplier;
        }
    }

    public void spin() {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int symbolIndex;
                if (rand.nextDouble() < 0.4) {
                    symbolIndex = rand.nextInt(3); // Common symbols (0-2)
                } else if (rand.nextDouble() < 0.7) {
                    symbolIndex = rand.nextInt(5); // Less common (0-4)
                } else if (rand.nextDouble() < 0.9) {
                    symbolIndex = rand.nextInt(7); // Rare (0-6)
                } else {
                    symbolIndex = rand.nextInt(symbols.length); // Very rare (all)
                }
                reels[i][j] = symbols[symbolIndex].emoji;
            }
        }
    }

    public void printSlots() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("- ");
            for (int j = 0; j < 3; j++) {
                System.out.print(reels[i][j] + " ");
            }
            System.out.println("-");
        }
        System.out.println("---------");
    }

    public double checkWins(double bet) {
        double totalWin = 0;

        // Check horizontal lines
        for (int i = 0; i < 3; i++) {
            if (reels[i][0].equals(reels[i][1]) && reels[i][0].equals(reels[i][2])) {
                double win = bet * getSymbolMultiplier(reels[i][0]) * 5; // 5x for full line
                System.out.println("Line " + (i+1) + " hit! (" + reels[i][0] + " " + reels[i][1] + " " + reels[i][2] + ")");
                System.out.println("Won: " + win);
                totalWin += win;
            }
        }

        // Check vertical lines
        for (int j = 0; j < 3; j++) {
            if (reels[0][j].equals(reels[1][j]) && reels[0][j].equals(reels[2][j])) {
                double win = bet * getSymbolMultiplier(reels[0][j]) * 3; // 3x for vertical
                System.out.println("Column " + (j+1) + " hit! (" + reels[0][j] + " " + reels[1][j] + " " + reels[2][j] + ")");
                System.out.println("Won: " + win);
                totalWin += win;
            }
        }

        // Check diagonals
        if (reels[0][0].equals(reels[1][1]) && reels[0][0].equals(reels[2][2])) {
            double win = bet * getSymbolMultiplier(reels[1][1]) * 7; // 7x for diagonal
            System.out.println("Diagonal \\ hit! (" + reels[0][0] + " " + reels[1][1] + " " + reels[2][2] + ")");
            System.out.println("Won: " + win);
            totalWin += win;
        }

        if (reels[0][2].equals(reels[1][1]) && reels[0][2].equals(reels[2][0])) {
            double win = bet * getSymbolMultiplier(reels[1][1]) * 7; // 7x for diagonal
            System.out.println("Diagonal / hit! (" + reels[0][2] + " " + reels[1][1] + " " + reels[2][0] + ")");
            System.out.println("Won: " + win);
            totalWin += win;
        }

        // Check for any two matching symbols (partial wins)
        for (int i = 0; i < 3; i++) {
            // Check horizontal two-of-a-kind
            if (reels[i][0].equals(reels[i][1])) {
                double win = bet * getSymbolMultiplier(reels[i][0]) * 0.5;
                System.out.println("Partial line " + (i+1) + " hit! (" + reels[i][0] + " " + reels[i][1] + ")");
                System.out.println("Won: " + win);
                totalWin += win;
            }
            if (reels[i][1].equals(reels[i][2])) {
                double win = bet * getSymbolMultiplier(reels[i][1]) * 0.5;
                System.out.println("Partial line " + (i+1) + " hit! (" + reels[i][1] + " " + reels[i][2] + ")");
                System.out.println("Won: " + win);
                totalWin += win;
            }
        }

        return totalWin;
    }
    // SpinJutsu Animation
    public void spinWithDelay() {
        System.out.println("\nSpinning...");
        spin();

        try {
            Thread.sleep(2000); // 2-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private double getSymbolMultiplier(String emoji) {
        for (Symbol symbol : symbols) {
            if (symbol.emoji.equals(emoji)) {
                return symbol.multiplier;
            }
        }
        return 0;
    }
}