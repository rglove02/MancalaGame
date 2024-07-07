package mancala;
import java.util.ArrayList;

public class MancalaGame {

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private Board board;

    public MancalaGame() {
        playerOne = new Player();
        playerTwo = new Player();
        currentPlayer = playerOne;
        board = new Board();
    }

    public MancalaGame(Player onePlayer, Player twoPlayer) {
        playerOne = onePlayer;
        playerTwo = twoPlayer;
        currentPlayer = playerOne;
        board = new Board();
    }

    //sets the board for the game
    public void setBoard(Board theBoard){
        board = theBoard;
    }

    //gets board for the game
    public Board getBoard(){
        return board;
    }

    //returns the current player
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    //gets num of stones in specific pit
    public int getNumStones(int pitNum) throws PitNotFoundException{ 
        //goes to the board and finds the pit and sees how many stones in it
        try{
            return board.getNumStones(pitNum);
        }catch (PitNotFoundException e){
            throw new PitNotFoundException();
        }
    }

    //creates an array list and adds the players into it
    public ArrayList<Player> getPlayers(){
        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.add(playerOne);
        playerList.add(playerTwo);

        return playerList;
    }

    //gets total num of stones in player's store
    public int getStoreCount(Player player) throws NoSuchPlayerException{ 
        //gets the player and their store then the total
        try {
            return player.getStoreCount();
        } catch (Exception e) {
            throw new NoSuchPlayerException();
        }
    }

    //AI Start
    //gets the winner
    public Player getWinner() throws GameNotOverException, PitNotFoundException, NoSuchPlayerException {
        //checks to see if game is over calling the function - if not then goes to the exception
        if (!isGameOver()) {
            throw new GameNotOverException();
        }

        //gets the toal amount of stones in the stores for each player
        int playerOneStoreCount = getStoreCount(playerOne);
        int playerTwoStoreCount = getStoreCount(playerTwo);

        //checks to see who has more stones or if its a tie
        if (playerOneStoreCount > playerTwoStoreCount) {
            return playerOne;
        } else if (playerTwoStoreCount > playerOneStoreCount) {
            return playerTwo;
        } else {
            return null; // Tie
        }
    }

    //checks if game is over
    public boolean isGameOver() {
        //tries to see if a side is empty but it pit not found then goes to the catch
        try {  
            return board.isSideEmpty(1) || board.isSideEmpty(7);
        } catch (PitNotFoundException e) {
            System.out.println("Error: Pit not found.");
            return false;
        }
    }
    //AI End

    public Player endAddUpStones() throws PitNotFoundException, GameNotOverException, NoSuchPlayerException{
        //moves remaining stones in other side to the store
        ArrayList<Store> stores = board.getStores();
        try{
            if(getBoard().isSideEmpty(1)){ //player1 side empty
                for(Pit pit: getBoard().getPits()){
                    stores.get(1).addStones(pit.getStoneCount());
                    pit.removeStones();
                }
            }else{
                for(Pit pit: getBoard().getPits()){
                    stores.get(0).addStones(pit.getStoneCount());
                    pit.removeStones();
                }
            }
        }catch (Exception e){
            throw new PitNotFoundException();
        }

        Player winnerPlayer = getWinner();
        return winnerPlayer;
    }
    //makes a move for current player
    public int move(int startPit) throws InvalidMoveException, PitNotFoundException{
        int totalStones = 0;

        board.moveStones(startPit, currentPlayer);

        for(int i = 0; i < 12; i++){
            totalStones += board.getNumStones(i);
        }
        //current amount of stones in all pf players pits
        return totalStones;
    }

    //sets current player
    public void setCurrentPlayer(Player player){
        currentPlayer = player;
    }

    //sets the members of the class for the players
    public void setPlayers(Player onePlayer, Player twoPlayer){
        playerOne = onePlayer;
        playerTwo = twoPlayer;
        setCurrentPlayer(playerOne);
        board.registerPlayers(playerOne, playerTwo);
    }

    //starts new game by resetting the board
    public void startNewGame() {
       board.resetBoard();
       currentPlayer = playerOne;
       board.registerPlayers(playerOne, playerTwo);
    }

    @Override
    public String toString(){
        return "MancalaGame{" + "playerOne=" + playerOne 
            + ", playerTwo=" + playerTwo 
            +  ", currentPlayer=" + currentPlayer + "}\n";
    }
}
