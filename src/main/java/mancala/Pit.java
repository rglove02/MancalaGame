package mancala;

public class Pit {
    private int stones;

    public Pit() {
        stones = 0;
    }

    public Pit(int initialStones) {
        stones = initialStones;
    }

    public void addStone() {
        stones++;
    }

    public void addStones(int stoneNum){
        stones += stoneNum;
    }

    public void removeStone() {
        stones--;
    }

    public int getStoneCount() {
        return stones;
    }

    public int removeStones() {
        int stonesNum = stones;
        stones = 0;
        return stonesNum;
    }

    @Override
    public String toString(){
        return "Stones: " + stones;
    }
}
