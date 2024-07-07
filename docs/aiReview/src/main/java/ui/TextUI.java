package ui;

import mancala.*;

import java.util.Scanner;
import java.util.ArrayList;

public class TextUI {
    private MancalaGame mancalaGame;
    private Scanner scanner;

    public TextUI() {
        mancalaGame = new MancalaGame();
        scanner = new Scanner(System.in);
    }

    private void printBoard() {
        ArrayList<Pit> pits = mancalaGame.getBoard().getPits();
        ArrayList<Store> stores = mancalaGame.getBoard().getStores();
    
        System.out.println("Current Board:");
        System.out.println("Player Two's Side: ");
        System.out.println("  12  11  10   9   8   7");
        System.out.print("[" + pits.get(11).getStoneCount() + "] ");
        for (int i = 10; i >= 6; i--) {
            System.out.print("[" + pits.get(i).getStoneCount() + "] ");
        }
        System.out.print("[" + stores.get(1).getTotalStones() + "]");
        System.out.println();
    
        System.out.println("Player One's Side: ");
        System.out.print("[" + stores.get(0).getTotalStones() + "]");
        for (int i = 0; i <= 5; i++) {
            System.out.print("[" + pits.get(i).getStoneCount() + "] ");
        }
        System.out.print("[" + pits.get(6).getStoneCount() + "] ");
        System.out.println();
        System.out.println("   1   2   3   4   5   6");
    }
    

    private void printPlayers() {
        ArrayList<Player> players = mancalaGame.getPlayers();
        System.out.println("Players:");
        for (Player player : players) {
            System.out.println(player.getName() + " - Stones in Store: " + player.getStoreCount());
        }
    }

    private void handleMove() {
        System.out.print("Enter pit number to move stones from: ");
        int pitNum = scanner.nextInt();
        try {
            int endingPit = mancalaGame.move(pitNum);
            System.out.println("Stones moved to pit " + endingPit);
        } catch (InvalidMoveException e) {
            System.out.println("Invalid move. Please try again.");
        }
    }    

    public void startGame() {
        System.out.println("Welcome to Mancala!");
        System.out.print("Enter Player One's name: ");
        String playerOneName = scanner.next();
        System.out.print("Enter Player Two's name: ");
        String playerTwoName = scanner.next();

        Player playerOne = new Player(playerOneName);
        Player playerTwo = new Player(playerTwoName);

        mancalaGame.setPlayers(playerOne, playerTwo);
        mancalaGame.setBoard(new Board());
        mancalaGame.getBoard().registerPlayers(playerOne, playerTwo);
        mancalaGame.getBoard().initializeBoard();

        mancalaGame.setCurrentPlayer(playerOne);

        while (!mancalaGame.isGameOver()) {
            printBoard();
            printPlayers();
            handleMove();
            // Switch player after each move
            mancalaGame.setCurrentPlayer(mancalaGame.getCurrentPlayer() == playerOne ? playerTwo : playerOne);
        }

        // Game over, determine the winner
        Player winner;
        try {
            winner = mancalaGame.getWinner();
            if (winner != null) {
                System.out.println("Congratulations, " + winner.getName() + " wins!");
            } else {
                System.out.println("It's a tie!");
            }
        } catch (GameNotOverException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        textUI.startGame();
    }
}
