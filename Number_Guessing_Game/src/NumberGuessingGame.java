import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.println("Welcome to the Number Guessing Game, " + playerName + "!");
        System.out.println("Before we begin, here are the instructions:");
        System.out.println("1. Player has to think of a number between 1 and 100.");
        System.out.println("2. Player have 5 attempts to guess the number.");
        System.out.println("3. After each guess, I will tell you if your guess is too low, too high, or correct.");
        System.out.println("4. If you guess the number within 5 attempts, you win!");

        // Ask if the user wants to start the game
        System.out.print("Do you want to start the game? (yes/no): ");
        String startGame = scanner.next();

        if (!startGame.equalsIgnoreCase("yes")) {
            System.out.println("Thanks for visiting! Goodbye!");
            return;
        }

        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int rounds = 0;
        int wins = 0;
        int totalAttempts = 0;

        while (true) {
            rounds++;
            int secretNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;

            System.out.println("\nRound " + rounds);
            System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange + ".");

            while (attempts < maxAttempts) {
                attempts++;
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess == secretNumber) {
                    System.out.println("Congratulations, " + playerName + "! You guessed the number in " + attempts + " attempts.");
                    wins++;
                    totalAttempts += attempts;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you ran out of attempts. The number was " + secretNumber + ".");
                totalAttempts += maxAttempts;
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("\nGame over!");
        System.out.println("You played " + rounds + " rounds and won " + wins + ".");
        if (wins > 0) {
            System.out.printf("Your average attempts per win: %.2f\n", (double) totalAttempts / wins);
        } else {
            System.out.println("You did not win any rounds.");
        }
    }
}