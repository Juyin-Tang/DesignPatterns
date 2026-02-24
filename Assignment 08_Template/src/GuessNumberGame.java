public class GuessNumberGame extends Game {
    private int secretNumber;
    private int currentGuess;
    private boolean gameWon;
    private int winner;

    @Override
    public void initializeGame(int numberOfPlayers) {
        secretNumber = (int)(Math.random() * 100) + 1;
        gameWon = false;
        winner = -1;

        System.out.println("=== Guess the Number Game Started! ===");
        System.out.println("Number of players: " + numberOfPlayers);
        System.out.println("I've picked a number between 1-100. Players take turns guessing!");
        System.out.println("========================================");
    }

    @Override
    public boolean endOfGame() {
        return gameWon;
    }

    @Override
    public void playSingleTurn(int player) {
        currentGuess = (int)(Math.random() * 100) + 1;

        System.out.println("Player " + (player + 1) + " guesses: " + currentGuess);

        if (currentGuess == secretNumber) {
            System.out.println("Correct! You got it!");
            gameWon = true;
            winner = player;
        } else if (currentGuess < secretNumber) {
            System.out.println("Too low! Try again.");
        } else {
            System.out.println("Too high! Try again.");
        }
    }

    @Override
    public void displayWinner() {
        if (winner != -1) {
            System.out.println("\n Game Over! The winner is Player " + (winner + 1) + "! ");
            System.out.println(" The secret number was: " + secretNumber);
        } else {
            System.out.println("\n Game ended unexpectedly with no winner.");
        }
    }
}