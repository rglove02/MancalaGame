My Prompts:
Prompt 1: Make all code in java for mancala game with these specifications:
All classes each have their own file
Class MancalaGame
mancala.MancalaGame

public class MancalaGame extends Object
Represents a Mancala game.
Method Details
setPlayers
public void setPlayers(Player onePlayer, Player twoPlayer)
Sets the players for the game.
Parameters:
onePlayer - Player one
twoPlayer - Player two
getPlayers
public ArrayList<Player> getPlayers()
Gets the players for the game.
Returns:
An ArrayList of the players in the game
getCurrentPlayer
public Player getCurrentPlayer()
Gets the current player.
Returns:
The current player
setCurrentPlayer
public void setCurrentPlayer(Player player)
sets the current player
setBoard
public void setBoard(Board theBoard)
Sets the board for the game.
Parameters:
theBoard - Board
getBoard
public Board getBoard()
Gets the board for the game.
Returns:
the Board for the game
getNumStones
public int getNumStones(int pitNum) throws PitNotFoundException
Gets the number of stones in a specific pit.
Parameters:
pitNum - The pit number
Returns:
The number of stones in the pit
Throws:
PitNotFoundException - If the pit number is invalid
move
public int move(int startPit) throws InvalidMoveException
Makes a move for the current player.
Parameters:
startPit - The pit from which to start the move
Returns:
the total number of stones remaining in the players side pits
Throws:
InvalidMoveException - If the move is invalid
IllegalStateException - If the player is not valid
getStoreCount
public int getStoreCount(Player player) throws NoSuchPlayerException
Gets the total number of stones in a player's store.
Parameters:
player - The player
Returns:
The total number of stones in the player's store
Throws:
NoSuchPlayerException - If the player is not found
getWinner
public Player getWinner() throws GameNotOverException
Gets the winner of the game.
Returns:
The winning player or null for a tie
Throws:
GameNotOverException - If the game is not over yet
isGameOver
public boolean isGameOver()
Checks if the game is over.
Returns:
True if the game is over, false otherwise
startNewGame
public void startNewGame()
Starts a new game by resetting the board.
toString
public String toString()
Generates a string representation of the game.
Overrides:
toString in class Object
Returns:
The string representation of the game and board
Class Board
java.lang.Object
mancala.Board

public class Board extends Object
Represents the board in the Mancala game.
Constructor Summary
Board()
Initializes a new Mancala board with pits and stores.
Method Details
setUpPits
public void setUpPits()
Establishes 12 empty Pits in the board
getPits
public ArrayList<Pit> getPits()
Returns the list of pits in the board, not including the stores
Returns:
ArrayList of pits
getStores
public ArrayList<Store> getStores()
Returns the list of stores in the board, not including the stores
Returns:
ArrayList of Stores
setUpStores
public void setUpStores()
Establishes 2 empty Stores in the board
initializeBoard
public void initializeBoard()
Initializes the board by distributing stones.
resetBoard
public void resetBoard()
Resets the board by redistributing stones but retains the players.
registerPlayers
public void registerPlayers(Player one, Player two)
Connects Players to their Stores. Will need to call methods in store and in player to ensure a two-way connection
Parameters:
one - Player one
two - Player two
moveStones
public int moveStones(int startPit, Player player) throws InvalidMoveException
Moves stones for the player starting from a specific pit.
Parameters:
startPit - The starting pit
player - The player making the move
Returns:
The total number of stones added to the corresponding store
Throws:
InvalidMoveException - If the move is invalid
distributeStones
public int distributeStones(int startingPoint) throws PitNotFoundException
Helper method that distributes stones into pits and stores, skipping the opponent's store.
Parameters:
startingPoint - The starting pit
Returns:
The total number of stones added to pits and stores
Throws:
PitNotFoundException - If the pit number is invalid
captureStones
public int captureStones(int stoppingPoint) throws PitNotFoundException
Captures stones from the opponent's pits.
Parameters:
stoppingPoint - The stopping pit
Returns:
The number of stones captured, if any
Throws:
PitNotFoundException - If the pit number is invalid
getNumStones
public int getNumStones(int pitNum) throws PitNotFoundException
Gets the number of stones in a specific pit.
Parameters:
pitNum - The pit number
Returns:
The number of stones in the pit
Throws:
PitNotFoundException - If the pit number is invalid
isSideEmpty
public boolean isSideEmpty(int pitNum) throws PitNotFoundException
Indicates whether one side of the board is empty. An empty side indicates the end of the game. more context: pits 1-6 are on one side of the board while pits 7-12 are on the other side of the board. if this method were called with a 3 as a parameter, it would return true if pits 1-6 were empty, false otherwise.
Parameters:
pitNum - The pit number
Returns:
true if the side of the board that includes the parameter pit number is empty
Throws:
PitNotFoundException
toString
public String toString()
Overrides:
toString in class Object

Class Player
java.lang.Object
mancala.Player

public class Player extends Object
Represents a player in the Mancala game.
Constructor Details
Player
public Player()
Initializes a new player.
Player
public Player(String name)
Initializes a new player with a name.
Parameters:
name - The player's name


Method Details
getName
public String getName()
Gets the name of the player.
Returns:
The player's name
setName
public void setName(String name)
Sets the player's name.
Parameters:
name - The player's name
getStore
public Store getStore()
Gets the player's store where they collect stones.
Returns:
The player's store
setStore
public void setStore(Store store)
Sets the player's store.
Parameters:
store - The player's store
getStoreCount
public int getStoreCount()
Gets the cound of the number of stones in the player's store where they collect stones.
Returns:
The count of stones in the player's store
toString
public String toString()
Overrides:
toString in class Object
Class Pit
java.lang.Object
mancala.Pit

public class Pit extends Object
Represents a pit in the Mancala game.
Constructor Details
Pit
public Pit()
Initializes a new pit.


Method Details
getStoneCount
public int getStoneCount()
Gets the number of stones in the pit.
Returns:
The number of stones in the pit
addStone
public void addStone()
Adds a stone to the pit.
removeStones
public int removeStones()
Removes and returns the stones from the pit.
Returns:
The number of stones removed from the pit
toString
public String toString()
Overrides:
toString in class Object
Class Store
java.lang.Object
mancala.Store

public class Store extends Object
Represents a store in the Mancala game.
Constructor Details
Store
public Store()
Initializes a new store.


Method Details
setOwner
public void setOwner(Player player)
Sets the owner of the store.
Parameters:
player - The owner of the store
getOwner
public Player getOwner()
Gets the owner of the store.
Returns:
The owner of the store
addStones
public void addStones(int amount)
Adds stones to the store.
Parameters:
amount - The number of stones to add
getTotalStones
public int getTotalStones()
Gets the total number of stones in the store.
Returns:
The total number of stones in the store
emptyStore
public int emptyStore()
Empties the store and returns the number of stones that were in it.
Returns:
The number of stones in the store
toString
public String toString()
Overrides:
toString in class Object


Exception classes specifications:
Class GameNotOverException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.GameNotOverException
All Implemented Interfaces:
Serializable

public class GameNotOverException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
GameNotOverException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
GameNotOverException
public GameNotOverException()


Class InvalidMoveException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.InvalidMoveException
All Implemented Interfaces:
Serializable

public class InvalidMoveException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
InvalidMoveException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
InvalidMoveException
public InvalidMoveException()


Class NoSuchPlayerException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.NoSuchPlayerException
All Implemented Interfaces:
Serializable

public class NoSuchPlayerException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
NoSuchPlayerException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
NoSuchPlayerException
public NoSuchPlayerException()


Class PitNotFoundException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.PitNotFoundException
All Implemented Interfaces:
Serializable

public class PitNotFoundException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
PitNotFoundException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
PitNotFoundException
public PitNotFoundException()

Prompt 2: can you write the code for the methods pubic void initalizeBoard() and public void resetBoard() that is in the Board class

Prompt3: can you write the exception classes: Exception classes:
Class GameNotOverException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.GameNotOverException
All Implemented Interfaces:
Serializable

public class GameNotOverException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
GameNotOverException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
GameNotOverException
public GameNotOverException()


Class InvalidMoveException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.InvalidMoveException
All Implemented Interfaces:
Serializable

public class InvalidMoveException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
InvalidMoveException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
InvalidMoveException
public InvalidMoveException()


Class NoSuchPlayerException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.NoSuchPlayerException
All Implemented Interfaces:
Serializable

public class NoSuchPlayerException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
NoSuchPlayerException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
NoSuchPlayerException
public NoSuchPlayerException()


Class PitNotFoundException
java.lang.Object
java.lang.Throwable
java.lang.Exception
mancala.PitNotFoundException
All Implemented Interfaces:
Serializable

public class PitNotFoundException extends Exception
See Also:
Serialized Form
Constructor Summary
Constructors
Constructor
Description
PitNotFoundException()
 
Method Summary
Methods inherited from class java.lang.Throwable
addSuppressed, fillInStackTrace, getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed, initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace, toString
Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, wait, wait, wait
Constructor Details
PitNotFoundException
public PitNotFoundException()

prompt 4: Can you make a user interface and main method in one file called TextUI.java
• The main method to run the program will be in a class called TextUI that is in a package called ui.
• The TextUI class handles the user input for the game as well as the printing of all output. It is the class that
allows the game to be played.
• You must edit your build.gradle file is updated to reflect where your main method is.
• The TextUI class will have a private member variable of type MancalaGame as well as a member variable of
type Scanner.
7
• You may create as many methods for the TextUI class as you need.
• You will be asked to justify your design of this class during your personal interview

Prompt 5: Can you write the method in TextUI.java for private void printBoard()

Prompt 6: can you make the TextUI in package ui and then everything else in package mancala

prompt 7: code:
public boolean isGameOver() {
        return board.isSideEmpty(1) || board.isSideEmpty(7);
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

error: /course/coursework/Assignments/GP2/docs/aiReview/src/main/java/mancala/MancalaGame.java:68: error: unreported exception PitNotFoundException; must be caught or declared to be thrown
        return board.isSideEmpty(1) || board.isSideEmpty(7);

How do I fix the error?

prompt 8: error: private void handleMove() {
        System.out.print("Enter pit number to move stones from: ");
        int pitNum = scanner.nextInt();
        try {
            int endingPit = mancalaGame.move(pitNum);
            System.out.println("Stones moved to pit " + endingPit);
        } catch (InvalidMoveException | PitNotFoundException e) {
            System.out.println("Invalid move. Please try again.");
        }
    }

    Prompt 9: the code above, has an error: /course/coursework/Assignments/GP2/docs/aiReview/src/main/java/ui/TextUI.java:58: error: exception PitNotFoundException is never thrown in body of corresponding try statement
        } catch (PitNotFoundException e) {
how do I fix it?

Prompt 10: 
    