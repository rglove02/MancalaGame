package mancala;

public class GameNotOverException extends Exception {
    public GameNotOverException() {
        System.out.println("The game is not over yet");

        //goes back into the game loop to make moves
        //makeMove();
        
    }
}
