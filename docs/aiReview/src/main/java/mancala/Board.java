package mancala;
import java.util.ArrayList;

public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;

    public Board() {
        pits = new ArrayList<>();
        stores = new ArrayList<>();
        setUpPits();
        setUpStores();
    }

    public void setUpPits() {
        for (int i = 0; i < 12; i++) {
            pits.add(new Pit());
        }
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setUpStores() {
        stores.add(new Store());
        stores.add(new Store());
    }

    public void initializeBoard() {
        // Distribute stones in pits for the initial setup
        int initialStoneCount = 4; // Number of stones in each pit initially

        for (Pit pit : pits) {
            for (int i = 0; i < initialStoneCount; i++) {
                pit.addStone();
            }
        }
    }

    public void resetBoard() {
        // Reset the board by redistributing stones
        for (Pit pit : pits) {
            pit.removeStones(); // Empty all pits
        }

        // Re-initialize pits with stones
        initializeBoard();
        
        // Reset the stores
        stores.get(0).emptyStore(); // Empty player one's store
        stores.get(1).emptyStore(); // Empty player two's store
    }

    public void registerPlayers(Player one, Player two) {
        one.setStore(stores.get(0));
        two.setStore(stores.get(1));
        stores.get(0).setOwner(one);
        stores.get(1).setOwner(two);
    }

    public int moveStones(int startPit, Player player) throws InvalidMoveException {
        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException();
        }

        Pit startingPit = pits.get(startPit - 1);
        int stonesToMove = startingPit.getStoneCount();
        if (stonesToMove == 0) {
            throw new InvalidMoveException();
        }

        startingPit.removeStones();

        int currentPitIndex = startPit;
        while (stonesToMove > 0) {
            currentPitIndex = (currentPitIndex + 1) % 14;
            if (currentPitIndex == 0) {
                currentPitIndex = 1;
            }
            Pit currentPit = pits.get(currentPitIndex - 1);
            if (currentPitIndex == 13) {
                player.getStore().addStones(1);
            } else if (currentPitIndex <= 6) {
                currentPit.addStone();
            } else {
                if (stonesToMove == 1 && currentPit.getStoneCount() == 0) {
                    int oppositePitIndex = 14 - currentPitIndex;
                    player.getStore().addStones(oppositePitIndex);
                    currentPit.removeStones();
                } else {
                    currentPit.addStone();
                }
            }
            stonesToMove--;
        }

        // Return the index of the last pit where a stone was placed
        return currentPitIndex;
    }

    public int distributeStones(int startingPoint) throws PitNotFoundException {
        if (startingPoint < 1 || startingPoint > 12) {
            throw new PitNotFoundException();
        }

        Pit startingPit = pits.get(startingPoint - 1);
        int stonesToDistribute = startingPit.getStoneCount();
        startingPit.removeStones();

        int currentPitIndex = startingPoint;
        while (stonesToDistribute > 0) {
            currentPitIndex = (currentPitIndex + 1) % 14;
            if (currentPitIndex == 0) {
                currentPitIndex = 1;
            }
            Pit currentPit = pits.get(currentPitIndex - 1);
            currentPit.addStone();
            stonesToDistribute--;
        }

        // Return the index of the last pit where a stone was placed
        return currentPitIndex;
    }

    public int captureStones(int stoppingPoint) throws PitNotFoundException {
        if (stoppingPoint < 1 || stoppingPoint > 12) {
            throw new PitNotFoundException();
        }

        Pit stoppingPit = pits.get(stoppingPoint - 1);
        int capturedStones = stoppingPit.getStoneCount();
        stoppingPit.removeStones();

        // Capture stones from the opposite pit
        int oppositePitIndex = 14 - stoppingPoint;
        Pit oppositePit = pits.get(oppositePitIndex - 1);
        capturedStones += oppositePit.getStoneCount();
        oppositePit.removeStones();

        // Return the number of captured stones
        return capturedStones;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum < 1 || pitNum > 12) {
            throw new PitNotFoundException();
        }

        return pits.get(pitNum - 1).getStoneCount();
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
        if (pitNum < 1 || pitNum > 12) {
            throw new PitNotFoundException();
        }

        int start = (pitNum <= 6) ? 1 : 7;
        int end = (pitNum <= 6) ? 6 : 12;

        for (int i = start; i <= end; i++) {
            if (pits.get(i - 1).getStoneCount() > 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Board{" +
                "pits=" + pits +
                ", stores=" + stores +
                '}';
    }
}
