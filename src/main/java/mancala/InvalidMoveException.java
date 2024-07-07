package mancala;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        System.out.println("That move is not valid, make another move");

        //go to enter number again
        //makeMove();
        //handleMove();
    }
}
