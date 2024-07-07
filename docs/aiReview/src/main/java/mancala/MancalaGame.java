package mancala;
import java.util.ArrayList;

public class MancalaGame {
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private Board board;

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        playerOne = onePlayer;
        playerTwo = twoPlayer;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void setBoard(Board theBoard) {
        board = theBoard;
    }

    public Board getBoard() {
        return board;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        return board.getNumStones(pitNum);
    }

    public int move(int startPit) throws InvalidMoveException {
        return board.moveStones(startPit, currentPlayer);
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException {
        return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException {
        if (!isGameOver()) {
            throw new GameNotOverException();
        }

        int playerOneStoreCount = playerOne.getStore().getTotalStones();
        int playerTwoStoreCount = playerTwo.getStore().getTotalStones();

        if (playerOneStoreCount > playerTwoStoreCount) {
            return playerOne;
        } else if (playerTwoStoreCount > playerOneStoreCount) {
            return playerTwo;
        } else {
            return null; // Tie
        }
    }

    public boolean isGameOver() {
        try {
            return board.isSideEmpty(1) || board.isSideEmpty(7);
        } catch (PitNotFoundException e) {
            System.out.println("Error: Pit not found.");
            return false;
        }
    }

    public void startNewGame() {
        board.resetBoard();
    }

    @Override
    public String toString() {
        return "MancalaGame{" +
                "playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", currentPlayer=" + currentPlayer +
                ", board=" + board +
                '}';
    }
}
