package mancala;

public class Store {
    private Player owner;
    private int totalStones;

    public Store() {
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void addStones(int amount) {
        totalStones += amount;
    }

    public int getTotalStones() {
        return totalStones;
    }

    public int emptyStore() {
        int stones = totalStones;
        totalStones = 0;
        return stones;
    }

    @Override
    public String toString() {
        return "Store{" +
                "owner=" + owner +
                ", totalStones=" + totalStones +
                '}';
    }
}
