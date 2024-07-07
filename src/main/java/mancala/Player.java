package mancala;

public class Player{
    private String name;
    private Store store;

    public Player() {
        name = "Player";
    }

    public Player(String namePlayer) {
        name = namePlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String namePlayer) {
        name = namePlayer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store storeNum) {
        store = storeNum;
    }

    public int getStoreCount() {
        return store.getTotalStones();
    }

    @Override
    public String toString() {
        return name;
    }
    
}
