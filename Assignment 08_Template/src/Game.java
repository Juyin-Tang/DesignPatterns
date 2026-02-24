public abstract class Game {

    public final void play(int numberOfPlayers) {
        // Template method defining the skeleton of the game algorithm
        initializeGame(numberOfPlayers);
        int playerInTurn = 0;
        while (!endOfGame()) {
            playSingleTurn(playerInTurn);
            playerInTurn = ++playerInTurn % numberOfPlayers;
        }
        displayWinner();
    }

    public abstract void initializeGame(int numberOfPlayers);
    public abstract boolean endOfGame();
    public abstract void playSingleTurn(int player);
    public abstract void displayWinner();
}