package ui;

//imports all classes in mancala package
import mancala.MancalaGame;
import mancala.Pit;
import mancala.Player;
import mancala.Store;
import mancala.GameNotOverException;
import mancala.InvalidMoveException;
import mancala.PitNotFoundException;
import mancala.NoSuchPlayerException;

import java.util.Scanner;
import java.util.ArrayList;

public class TextUI {
    private MancalaGame mancalaGame;
    private Scanner scanner;

    public TextUI() {
        mancalaGame = new MancalaGame();
        scanner = new Scanner(System.in);
    }

    //AI Start
    //prints visual of board
    public void displayBoard() {
        ArrayList<Pit> pits = mancalaGame.getBoard().getPits();
        ArrayList<Store> stores = mancalaGame.getBoard().getStores();
    
        System.out.println("\nCurrent Board:");
        System.out.println("      Player Two's Side ");
        System.out.println(" Store  12  11  10   9   8   7");
        System.out.print("        ");
        //System.out.print("[  " + stores.get(1).getTotalStones() + "  ] ");
        for (int i = 11; i >= 6; i--) {
            System.out.print("[" + pits.get(i).getStoneCount() + "] ");
        }
        System.out.println();
        System.out.println("[  " + stores.get(1).getTotalStones() + "  ]                         [  " 
        + stores.get(0).getTotalStones() + "  ] ");
        System.out.print("        ");
        for (int i = 0; i <= 5; i++) {
            System.out.print("[" + pits.get(i).getStoneCount() + "] ");
        }
        //System.out.print("[  " + stores.get(0).getTotalStones() + "  ] ");
        System.out.println();
        System.out.println("         1   2   3   4   5   6   Store");
        System.out.println("      Player One's Side ");
    }
    
    public void startGame() throws InvalidMoveException, PitNotFoundException, 
        GameNotOverException, NoSuchPlayerException {

        //welcome message plus gets players names
        System.out.println("\nWelcome to Mancala!");

        instructions();

        System.out.print("\nEnter Player One's name: ");
        String playerOneName = scanner.next();
        System.out.print("Enter Player Two's name: ");
        String playerTwoName = scanner.next();

        Player playerOne = new Player(playerOneName);
        Player playerTwo = new Player(playerTwoName);

        //initalizes the players in game and the board
        mancalaGame.setPlayers(playerOne, playerTwo);
        mancalaGame.getBoard().registerPlayers(playerOne, playerTwo);

        //sets the curent Player to player 1
        mancalaGame.setCurrentPlayer(playerOne);

        makeMove(playerOne, playerTwo);
    }
    //AI end

    public void instructions(){
        System.out.println("---------------------------------------");
        System.out.println("How to Play: ");
        System.out.println("1. The game starts with a board containing 12 small pits and 2 large stores, "
                + "each pit containing a certain number of stones.\r\n" 
                + "2. Players take turns moving stones from one of their pits.\r\n"
                + "3. Stones are distributed counterclockwise, skipping the opponent's store.\r\n"
                + "4. If the last stone lands in a player's store, they get another turn.\r\n"
                + "5. If the last stone lands in an empty pit on the player's side, they capture that stone and " 
                + "any stones in the opposite pit, placing them in their store.\r\n" 
                + "6. The game ends when all the pits on one player's side are empty. "
                + "The player with stones left in their pits captures those stones to their store." 
                + "The player with the most stones in their store wins.");
        System.out.println("---------------------------------------");
    }

    //loop for players to make moves
    public void makeMove(Player playerOne, Player playerTwo) throws GameNotOverException, 
        PitNotFoundException, NoSuchPlayerException {
            
        while (!mancalaGame.isGameOver()) {
            displayBoard();
            
            handleMove();

            // Switch player after each move
            //checks to see currentPlayer and if ended on store
            if (!mancalaGame.getBoard().endedOnStore()) {
                if(mancalaGame.getCurrentPlayer() == playerOne){
                    mancalaGame.setCurrentPlayer(playerTwo);
                }else{
                    mancalaGame.setCurrentPlayer(playerOne);
                }
            }else{
                System.out.println(mancalaGame.getCurrentPlayer() + ", you go again!");
            }
        }
        Player winnerPlayer = mancalaGame.endAddUpStones();
        displayBoard();
        winnerMessage(winnerPlayer);
    }

    //gets input on pit and tehn sees if 
    public void handleMove() {
        System.out.print("\n" + mancalaGame.getCurrentPlayer() + ", enter pit number to move stones from: ");
        int pitNum = scanner.nextInt();

        //trys to move the stones and checks to see if possible
        try {
            mancalaGame.move(pitNum);
        } catch (Exception e) {
            handleMove();
        }
    } 

    //prints winner message
    public void winnerMessage(Player player){
        if(player != null){
            System.out.println("\n" + player.getName() + " is the winner!!");
            System.out.println(player.getName() + " has a total of " 
            +  player.getStoreCount() + " stones in their store");
        }else{
            System.out.println("The game is a Tie!");
        }
    }

    public static void main(String[] args) throws InvalidMoveException, PitNotFoundException, 
        GameNotOverException, NoSuchPlayerException{

        TextUI textUI = new TextUI();
        textUI.startGame();
    }
}
