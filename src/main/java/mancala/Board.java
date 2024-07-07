package mancala;
import java.util.ArrayList;

public class Board{
    //AI Start
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    private boolean lastMoveEndOnStore;

    public Board() {
        pits = new ArrayList<>();
        stores = new ArrayList<>();
        lastMoveEndOnStore = false;
        
        initializeBoard();
    }
    //AI End

    //sets up 12 pits in arrayList
    public void setUpPits(){
        for(int i = 0; i < 12; i++){
            pits.add(i,new Pit(4));
        }
    }
    
    //gets list of pits
    public ArrayList<Pit> getPits(){
        return pits;
    }

    //gets list of store
    public ArrayList<Store> getStores(){
        return stores;
    }

    public boolean endedOnStore() {
        return lastMoveEndOnStore;
    }

    //establishes 2 empty stores
    public void setUpStores(){
        stores = new ArrayList<>();
        stores.add(new Store()); //player 1 Store
        stores.add(new Store());  //Player 2 Store
    }

    //initalizes board by distributing stones
    public void initializeBoard(){
        setUpPits();
        setUpStores();
    }

    //AI Start
    public void resetBoard() {
        // Reset the board by redistributing stones
        for (Pit pit : pits) {
            pit.removeStones(); // Empty all pits
        }
        
        // Reset the stores
        stores.get(0).emptyStore(); // Empty player one's store
        stores.get(1).emptyStore(); // Empty player two's store

        // Re-initialize pits with stones
        initializeBoard();
    }

    public void registerPlayers(Player one, Player two) {
        //assigns stores to players
        one.setStore(stores.get(0)); 
        two.setStore(stores.get(1));

        //assigns players to stores
        stores.get(0).setOwner(one); 
        stores.get(1).setOwner(two); 
    }
    //AI End

    public int moveStones(int startPit, Player player) throws InvalidMoveException, PitNotFoundException{

        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException();
         }else if((startPit > 0 && startPit <= 6) && (player == stores.get(1).getOwner())){ //if player is not 1
             throw new InvalidMoveException();
         } else if((startPit > 6 && startPit <= 12) && (player == stores.get(0).getOwner())){ //if player is not 2
            throw new InvalidMoveException();
        }else if(pits.get(startPit-1).getStoneCount() == 0){
            throw new InvalidMoveException();
        }else{
            distributeStones(startPit);
        }

        // Return number of stones in store of the player
        if(startPit > 0 && startPit <= 6){
            return stores.get(0).getTotalStones(); //return stones in player one store
        }else{
            return stores.get(1).getTotalStones(); //return stones in player two store
        }
    }

    //sets player and then variables
    public int distributeStones(int startingPoint) throws PitNotFoundException{
        
        lastMoveEndOnStore = false;
        
        //checks pit is valid
        if(startingPoint < 1 || startingPoint > 12){
            throw new PitNotFoundException();
        }

        //gets the number of stones that need to be distributed and removes them
        Pit startingPit = pits.get(startingPoint - 1);
        int stonesToDistribute = startingPit.getStoneCount();
        startingPit.removeStones();

        // Loop to distribute stones until they are all gone
        int currentPitIndex = startingPoint - 1;
        int player;
        int capturedStones = 0;

        //checks to see where starting point is (if player1 or player2)
        if(startingPoint > 0 && startingPoint <= 6){
            player = 0;
        }else{
            player = 1;
        }

        //calls to distribute stones
        int indexEnd = distributing(stonesToDistribute, currentPitIndex, player);

        //if on the right side then sees if can capture stones and if it deos then adds to the total moved
        //add -1 bc already distribute to pit
        if(player == 0 && indexEnd >= 0 && indexEnd < 6 && pits.get(indexEnd).getStoneCount()-1 == 0){
            capturedStones =  captureStones(indexEnd);
            return stonesToDistribute + capturedStones -1;
            
        }else if(player == 1 && indexEnd >= 6 && indexEnd < 12){
            if(pits.get(indexEnd).getStoneCount()-1 == 0){
                capturedStones = captureStones(indexEnd);
                return stonesToDistribute + capturedStones -1;
            }
        }
        return stonesToDistribute + capturedStones;
    }

    public int distributing(int stonesToDistribute, int currentPitIndex, int player){
        while (stonesToDistribute > 0) {
             //to loop back to pit 1
            Pit currentPit = pits.get((currentPitIndex + 1)%12);
            currentPitIndex = (currentPitIndex + 1);
            //adds stone to pit
            currentPit.addStone();
            if(player == 0){
                 //player1
                //if hit pit number 6 (next pit is the store)
                if(currentPitIndex == 6){
                    stores.get(0).addStones(1);
                    if (stonesToDistribute >= 2){
                        stonesToDistribute--;
                    }else {
                        currentPit.removeStone();
                        lastMoveEndOnStore = true;
                    }
                }
            }else{  //player 2
                //if hit pit number 12 (next pit is the store)
                if(currentPitIndex == 12){
                    stores.get(1).addStones(1);
                    if (stonesToDistribute >= 2){
                        stonesToDistribute--;
                    }else {
                        currentPit.removeStone();
                        lastMoveEndOnStore = true;
                    }
                    currentPitIndex = currentPitIndex % 12;
                }
            }
            stonesToDistribute--;
        }
        return currentPitIndex;
    }

    public int captureStones(int stoppingPointIndex) throws PitNotFoundException {
        //check to see if the pit is in the range of 1-12
        if (stoppingPointIndex <= 0 || stoppingPointIndex > 12) {
            throw new PitNotFoundException();
        }

        int capturedStones = 0;
        Pit stoppingPit = pits.get(stoppingPointIndex);

        //if player 1 captures
        if(stoppingPointIndex >= 0 && stoppingPointIndex <6){
            capturedStones = 1; //to capture your stones
            stoppingPit.removeStones(); //remove stones from pit

            // Capture stones from the opposite pit
            int oppositePitIndex = 12 - stoppingPointIndex;
            Pit oppositePit = pits.get(oppositePitIndex - 1);
            capturedStones += oppositePit.getStoneCount(); //capture from opponent
            oppositePit.removeStones();

            stores.get(0).addStones(capturedStones);

        //if player2 captures
        } else if(stoppingPointIndex >= 6 && stoppingPointIndex <12){
            capturedStones = 1; //to capture your stones
            stoppingPit.removeStones(); //remove stones from pit

            // Capture stones from the opposite pit
            int oppositePitIndex = 12 - stoppingPointIndex;
            Pit oppositePit = pits.get(oppositePitIndex - 1);
            capturedStones += oppositePit.getStoneCount(); //capture from opponent
            oppositePit.removeStones();

            stores.get(1).addStones(capturedStones);
        }

        System.out.println(capturedStones + " stones captured!");

        // Return the number of captured stones
        return capturedStones;
    }
    //AI End

    //checks to make sure that its a pit from 1-12 and then gets the num of stones in it
    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum < 0 || pitNum > 11) {
            throw new PitNotFoundException();
        }
        return pits.get(pitNum).getStoneCount();
    }
    
    public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
        Boolean empty = true;

        //checks to see if it is in the range of the num of pits
        if (pitNum < 1 || pitNum > 12) {
            throw new PitNotFoundException();
        }

        //checks whch side and then checks to see if all on one side is empty
        if(pitNum >= 1 && pitNum < 6){
            for(int i = 0; i< 6; i++){
                if(pits.get(i).getStoneCount() != 0){
                    empty = false;
                }
            }
        }else{
            for(int i = 11; i> 5; i--){
                if(pits.get(i).getStoneCount() != 0){
                    empty = false;
                }
            }
        }
        return empty;
    }

    @Override
    public String toString(){
        return "NUmber of pits have" + pits.size();
        
    }
}
