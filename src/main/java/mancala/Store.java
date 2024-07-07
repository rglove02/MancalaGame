package mancala;

public class Store {
    private Player owner;
    private int totalStones;

    public Store() {
        totalStones = 0;
    }

    public void addStones(int stones) {
        totalStones += stones;
    }

    public int emptyStore(){
        int num = totalStones;
        totalStones = 0;
        return num;
    }

    public Player getOwner(){
        return owner;
    }

    public int getTotalStones(){
        return totalStones;
    }

    public void setOwner(Player player){
        owner = player;
    }

    @Override
    public String toString(){
        return "Owner=" + owner.getName() + "  Stones: " + totalStones;
    }
}
