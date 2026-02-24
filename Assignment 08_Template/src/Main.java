public class Main {
    public static void main(String[] args) {
        // Create and play the game with 2 players
        Game game = new GuessNumberGame();
        game.play(2);  // The template method runs everything!
    }
}