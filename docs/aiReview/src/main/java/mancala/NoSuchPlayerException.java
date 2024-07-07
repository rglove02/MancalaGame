package mancala;

public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException() {
        super("Player not found");
    }
}
