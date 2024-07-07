package mancala;

public class Pit {
    private int stoneCount;

    public Pit() {
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public void addStone() {
        stoneCount++;
    }

    public int removeStones() {
        int stones = stoneCount;
        stoneCount = 0;
        return stones;
    }

    @Override
    public String toString() {
        return "Pit{" +
                "stoneCount=" + stoneCount +
                '}';
    }
}
