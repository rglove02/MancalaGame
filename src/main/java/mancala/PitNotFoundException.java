package mancala;

public class PitNotFoundException extends Exception {
    public PitNotFoundException() {
        System.out.println("Pit not found");
    }
}
