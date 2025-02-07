package scrublegame;

import java.util.Scanner;

public class ScrubleGame {
    private static String playerOne;
    private static String playerTwo;
    private static String playedWord = "";
    private static String alphabetList = "abcdefghijklmnopqrstuvwxyz";
    private static String usedLetters = "";
    private static final String vowels = "aeiou";

    private static String currentPlayer;
    private static int playerNumber = 1;
    private static int p1Score = 0;
    private static int p2Score = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Welcome to my Scruble Game" +
                "\nEnter (y) to play the game or any key to exit: ");
        if (scanner.next().equalsIgnoreCase("y")) {
            scanner.nextLine(); // Consume newline left-over

            // Prompt for player names
            System.out.print("\nEnter Player One Name: ");
            playerOne = scanner.nextLine();

            System.out.print("\nEnter Player Two Name: ");
            playerTwo = scanner.nextLine();

            // Start the game
            startGame();
        } else {
            System.out.println("Exiting the game. Goodbye!");
            System.exit(0);
        }
    }

    private static void startGame() {
        while (!playedWord.equals("###")) {
            determineCurrentPlayer();

            System.out.println("\nRemaining Alphabets: " + alphabetList);
            System.out.print(currentPlayer + ", please enter a word: ");
            playedWord = scanner.next().toLowerCase();

            if (!playedWord.equals("###")) {
                validatePlayedWord();
            }
        }

        displayScores();
    }

    private static void determineCurrentPlayer() {
        if (playerNumber == 1) {
            currentPlayer = playerOne;
            playerNumber = 2;
        } else {
            currentPlayer = playerTwo;
            playerNumber = 1;
        }
    }

    private static void validatePlayedWord() {
        System.out.print("\nEnter (y) if both players agree on the played word, or any key to disagree: ");
        if (scanner.next().equalsIgnoreCase("y")) {
            if (!containsUsedLetters(playedWord)) {
                updateAlphabetList();
                updatePlayerScore();
            } else {
                System.out.println("Sorry, the played word contains already used consonants.");
            }
        } else {
            System.out.println("Word rejected by players.");
            playedWord = "";
        }
    }

    private static boolean containsUsedLetters(String word) {
        for (char c : usedLetters.toCharArray()) {
            if (word.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    // Update only consonants in the alphabet list
    private static void updateAlphabetList() {
        for (char c : playedWord.toCharArray()) {
            if (!vowels.contains(String.valueOf(c)) && alphabetList.contains(String.valueOf(c))) {
                usedLetters += c;  // Track used consonants
                alphabetList = alphabetList.replace(String.valueOf(c), "_");  // Replace consonants with '_'
            }
        }
    }

    private static void updatePlayerScore() {
        if (currentPlayer.equals(playerOne)) {
            p1Score += playedWord.length();  // Full word length counts for the score
        } else {
            p2Score += playedWord.length();
        }
    }

    private static void displayScores() {
        System.out.println("\nGame Over! Final Scores:");
        System.out.println(playerOne + " Score: " + p1Score);
        System.out.println(playerTwo + " Score: " + p2Score);
    }
}
