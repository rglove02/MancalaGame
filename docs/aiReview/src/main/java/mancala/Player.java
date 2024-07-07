package mancala;

public class Player {
    private String name;
    private Store store;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getStoreCount() {
        return store.getTotalStones();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", store=" + store +
                '}';
    }
}
