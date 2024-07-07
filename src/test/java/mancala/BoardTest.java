package mancala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class BoardTest {
    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.initializeBoard();
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        board.registerPlayers(player1, player2);
    }
    
    //makes that the 12 pits are set to 4
    @Test
    public void setUpPitsTest() {
        for (Pit pit : board.getPits()) {
            assertEquals(4, pit.getStoneCount());
        }
    }

    //makes the 2 stores are set to 0
    @Test
    public void setUpStoresTest() {
        for (Store store : board.getStores()) {
            assertEquals(0, store.getTotalStones());
        }
    }

    @Test
    public void resetBoardTest() {
        //calls method
        board.resetBoard();

        //checks that all pits have 4 stones
        assertEquals(4, board.getPits().get(0).getStoneCount());
        assertEquals(4, board.getPits().get(1).getStoneCount());
        assertEquals(4, board.getPits().get(2).getStoneCount());
        assertEquals(4, board.getPits().get(3).getStoneCount());
        assertEquals(4, board.getPits().get(4).getStoneCount());
        assertEquals(4, board.getPits().get(5).getStoneCount());
        assertEquals(4, board.getPits().get(6).getStoneCount());
        assertEquals(4, board.getPits().get(7).getStoneCount());
        assertEquals(4, board.getPits().get(8).getStoneCount());
        assertEquals(4, board.getPits().get(9).getStoneCount());
        assertEquals(4, board.getPits().get(10).getStoneCount());
        assertEquals(4, board.getPits().get(11).getStoneCount());


        //checks that all stores have 0 stones
        for (Store store : board.getStores()) {
            assertEquals(0, store.getTotalStones());
        }
    }

    @Test
    public void registerPlayersTest() {

        //checks that stores are owned by right players
        assertEquals(player1, board.getStores().get(0).getOwner());
        assertEquals(player2, board.getStores().get(1).getOwner());

        //checks that players relate to stores
        assertEquals(0, player1.getStoreCount());
        assertEquals(0, player2.getStoreCount());
    }

    @Test
    public void distributeStonesP1ToP2SideTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        //adds stones and calls method
        board.getPits().get(3).addStones(5);
       int totalNumStones = board.distributeStones(4);

        //checks pits
        assertEquals(0, board.getPits().get(0).getStoneCount());
        assertEquals(0, board.getPits().get(1).getStoneCount());
        assertEquals(0, board.getPits().get(2).getStoneCount());
        assertEquals(0, board.getPits().get(3).getStoneCount());
        assertEquals(1, board.getPits().get(4).getStoneCount());
        assertEquals(1, board.getPits().get(5).getStoneCount());
        assertEquals(1, board.getPits().get(6).getStoneCount());
        assertEquals(1, board.getPits().get(7).getStoneCount());
        assertEquals(0, board.getPits().get(8).getStoneCount());
        assertEquals(0, board.getPits().get(9).getStoneCount());
        assertEquals(0, board.getPits().get(10).getStoneCount());
        assertEquals(0, board.getPits().get(11).getStoneCount());

        //checks stores
        assertEquals(1, board.getStores().get(0).getTotalStones());
        assertEquals(0, board.getStores().get(1).getTotalStones());

        //checks the total num stones distributed
        assertEquals(5, totalNumStones);
    }

    @Test
    public void distributeStonesP2ToP1SideTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        //adds stones and calls method
        board.getPits().get(10).addStones(3);
       int totalNumStones = board.distributeStones(11);

        //checks pits
        assertEquals(1, board.getPits().get(0).getStoneCount());
        assertEquals(0, board.getPits().get(1).getStoneCount());
        assertEquals(0, board.getPits().get(2).getStoneCount());
        assertEquals(0, board.getPits().get(3).getStoneCount());
        assertEquals(0, board.getPits().get(4).getStoneCount());
        assertEquals(0, board.getPits().get(5).getStoneCount());
        assertEquals(0, board.getPits().get(6).getStoneCount());
        assertEquals(0, board.getPits().get(7).getStoneCount());
        assertEquals(0, board.getPits().get(8).getStoneCount());
        assertEquals(0, board.getPits().get(9).getStoneCount());
        assertEquals(0, board.getPits().get(10).getStoneCount());
        assertEquals(1, board.getPits().get(11).getStoneCount());

       // checks stores
        assertEquals(0, board.getStores().get(0).getTotalStones());
        assertEquals(1, board.getStores().get(1).getTotalStones());

        //checks the total num stones distributed
        assertEquals(3, totalNumStones);
    }

    @Test
    public void distributeStonesSameSideTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        //adds stones and calls method
        board.getPits().get(1).addStones(4);
       int totalNumStones = board.distributeStones(2);

        //checks pits
        assertEquals(0, board.getPits().get(0).getStoneCount());
        assertEquals(0, board.getPits().get(1).getStoneCount());
        assertEquals(1, board.getPits().get(2).getStoneCount());
        assertEquals(1, board.getPits().get(3).getStoneCount());
        assertEquals(1, board.getPits().get(4).getStoneCount());
        assertEquals(0, board.getPits().get(5).getStoneCount()); //stone gets captured

        assertEquals(0, board.getPits().get(6).getStoneCount());
        assertEquals(0, board.getPits().get(7).getStoneCount());
        assertEquals(0, board.getPits().get(8).getStoneCount());
        assertEquals(0, board.getPits().get(9).getStoneCount());
        assertEquals(0, board.getPits().get(10).getStoneCount());
        assertEquals(0, board.getPits().get(11).getStoneCount());

        //checks stores
        //stone gets captured bc it ends at a pit 6 w 0 stones
        assertEquals(1, board.getStores().get(0).getTotalStones());
        assertEquals(0, board.getStores().get(1).getTotalStones());

        //checks the total num stones distributed
        assertEquals(4, totalNumStones);
    }

    @Test
    public void distributeStonesOutOfBoundsTest() throws PitNotFoundException {
        //Test distributing stones from an invalid pit
        assertThrows(PitNotFoundException.class, () -> board.distributeStones(0));
        assertThrows(PitNotFoundException.class, () -> board.distributeStones(13));
    }

    @Test
    public void distributeStonesSkipP2StoreTest() throws PitNotFoundException {

        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        board.getPits().get(5).addStones(9);
        int totalStones = board.distributeStones(6);
        assertEquals(1, board.getPits().get(0).getStoneCount());
        assertEquals(1, board.getPits().get(1).getStoneCount());
        assertEquals(0, board.getPits().get(2).getStoneCount());
        assertEquals(0, board.getPits().get(3).getStoneCount());
        assertEquals(0, board.getPits().get(4).getStoneCount());
        assertEquals(0, board.getPits().get(5).getStoneCount());
        assertEquals(1, board.getPits().get(6).getStoneCount());
        assertEquals(1, board.getPits().get(7).getStoneCount());
        assertEquals(1, board.getPits().get(8).getStoneCount());
        assertEquals(1, board.getPits().get(9).getStoneCount());
        assertEquals(1, board.getPits().get(10).getStoneCount());
        assertEquals(1, board.getPits().get(11).getStoneCount());

     //checks stores
        assertEquals(1, board.getStores().get(0).getTotalStones());
        assertEquals(0, board.getStores().get(1).getTotalStones());

        //checks total distributed stones
        assertEquals(9, totalStones);
    }

    @Test
    public void distributeStonesSkipP1StoreTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        board.getPits().get(11).addStones(10);
        int totalStones = board.distributeStones(12);

        //checks pits
        assertEquals(1, board.getPits().get(0).getStoneCount());
        assertEquals(1, board.getPits().get(1).getStoneCount());
        assertEquals(1, board.getPits().get(2).getStoneCount());
        assertEquals(0, board.getPits().get(3).getStoneCount()); //the one stone gets captured
        assertEquals(1, board.getPits().get(4).getStoneCount());
        assertEquals(1, board.getPits().get(5).getStoneCount());

        assertEquals(1, board.getPits().get(6).getStoneCount());
        assertEquals(1, board.getPits().get(7).getStoneCount());
        assertEquals(0, board.getPits().get(8).getStoneCount());
        assertEquals(0, board.getPits().get(9).getStoneCount());
        assertEquals(0, board.getPits().get(10).getStoneCount());
        assertEquals(0, board.getPits().get(11).getStoneCount());

        //checks stores
        assertEquals(0, board.getStores().get(0).getTotalStones());
        //captures a stone 
        // 1(from going around) + 1 (capture opponents) + 1 (capture yours) = 3
        assertEquals(3, board.getStores().get(1).getTotalStones()); 

        //checks total distributed stones
        assertEquals(11, totalStones);
    }

    @Test
    public void captureStonesP1Test() throws PitNotFoundException {

        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        board.getPits().get(9).addStones(4);
        int capturedStones = board.captureStones(2);

        //checks pits
        assertEquals(0, board.getPits().get(0).getStoneCount());
        assertEquals(0, board.getPits().get(1).getStoneCount());
        assertEquals(0, board.getPits().get(2).getStoneCount());
        assertEquals(0, board.getPits().get(3).getStoneCount());
        assertEquals(0, board.getPits().get(4).getStoneCount());
        assertEquals(0, board.getPits().get(5).getStoneCount());
        assertEquals(0, board.getPits().get(6).getStoneCount());
        assertEquals(0, board.getPits().get(10).getStoneCount());
        assertEquals(0, board.getPits().get(11).getStoneCount());

        //checks stores
        assertEquals(5, board.getStores().get(0).getTotalStones());
        assertEquals(0, board.getStores().get(1).getTotalStones());

        //checks total distributed stones
        assertEquals(5, capturedStones);
    }

    @Test
    public void captureStonesP2Test() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }
    
        board.getPits().get(3).addStones(2);

        int capturedStones = board.captureStones(8);

        //check all the pits
        assertEquals(0, board.getPits().get(2).getStoneCount());
        assertEquals(0, board.getPits().get(3).getStoneCount());
        assertEquals(0, board.getPits().get(4).getStoneCount());
        assertEquals(0, board.getPits().get(5).getStoneCount());
        assertEquals(0, board.getPits().get(6).getStoneCount());
        assertEquals(0, board.getPits().get(7).getStoneCount());
        assertEquals(0, board.getPits().get(8).getStoneCount());
        assertEquals(0, board.getPits().get(9).getStoneCount());
        assertEquals(0, board.getPits().get(10).getStoneCount());
        assertEquals(0, board.getPits().get(11).getStoneCount());

        //checks stores
        assertEquals(0, board.getStores().get(0).getTotalStones());
        assertEquals(3, board.getStores().get(1).getTotalStones());

        //check if right amount of stones captured
         assertEquals(3, capturedStones);
    }

    @Test
    public void captureStonesOutOfBoundsTest() throws PitNotFoundException {
         assertThrows(PitNotFoundException.class, () -> board.captureStones(13));
    }

    @Test
    public void getNumStonesEmptyTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        assertEquals(0, board.getNumStones(0));
    }

    @Test
    public void getNumStonesP1StonesTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        //pit index is passed --> pit 3 is index 2
        board.getPits().get(2).addStones(5);
        assertEquals(5, board.getNumStones(2));
    }

    @Test
    public void getNumStonesP2StonesTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        //pit index is passed --> pit 7 is index 6
        board.getPits().get(6).addStones(20);
        assertEquals(20, board.getNumStones(6));
    }

    @Test
    public void getNumStonesOutOfBoundsTest() throws PitNotFoundException {
        assertThrows(PitNotFoundException.class, () -> board.getNumStones(13));
    }

    @Test
    public void isSideEmptySideNotEmptyTest() throws PitNotFoundException {
        assertFalse(board.isSideEmpty(1));
    }

    @Test
    public void isSideEmptyEmptyTest() throws PitNotFoundException {
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }

        assertTrue(board.isSideEmpty(7));
    }

    @Test
    public void isSideEmptyOutOfBoundsTest() throws PitNotFoundException {
        // Test checking empty side for an invalid pit
        assertThrows(PitNotFoundException.class, () -> board.isSideEmpty(0));
        assertThrows(PitNotFoundException.class, () -> board.isSideEmpty(13));
    }

    @Test
    public void isSideEmptyStartPit() throws PitNotFoundException{
        //empties pits
        for(Pit pits: board.getPits()){
            pits.removeStones();
        }
        
        assertTrue(board.isSideEmpty(4));
    }


    //given tests
    private int getPitValue(int pit){
        return board.getPits().get(pit-1).getStoneCount();
    }
/* 
    private int getStoreValue(int store){
        return board.getStores().get(store-1).getTotalStones();
    }
*/

    @Test
    public void moveStonesCaptureStonesTest() throws InvalidMoveException, PitNotFoundException{
        //remove stones from pit 6
        board.getPits().get(5).removeStones();

        // Perform the move
        int stonesAddedToStore = board.moveStones(2, player1);
        assertEquals(0,getPitValue(2));
        assertEquals(5,getPitValue(3));
        assertEquals(5,getPitValue(4));
        assertEquals(5,getPitValue(5));
        assertEquals(0,getPitValue(6));
        assertEquals(0,getPitValue(7));
        assertEquals(5, player1.getStoreCount());

        assertEquals(5, stonesAddedToStore);
    }

    @Test
    public void moveStonesValidMoveTest() throws InvalidMoveException,PitNotFoundException {
        // Assuming you have a valid move (e.g., startPit = 1)
        int startPit = 3;

        // Perform the move
        int stonesAddedToStore = board.moveStones(startPit, player1);
        assertEquals(5,getPitValue(4));
        assertEquals(1,player1.getStoreCount());

        assertEquals(1, stonesAddedToStore);
    }

    @Test
    public void moveStonesInvalidMoveTest(){
        //Assuming you have an invalid move (e.g startPit = 0, which is out of bounds)

        // The method should throw an InvalidMoveException for an invalid move
        assertThrows(InvalidMoveException.class, () -> board.moveStones(14, player1));
    }
}

// /***************
//  * This test serves the sole purpose of verifying the existence of methods in the class.
// //  * It does not validate the types of expected return values, but it does confirm the 
// presence of a return type if expected.
//  * Please note that this form of testing is not suitable for comprehensive unit testing.
//  * It has been designed solely as a tool to identify missing methods within your implementation.
//  */
// // public class BoardTest {

// //     @Test
// //     void testBoardMethodsExist() {
// //         Method[] methods = Board.class.getDeclaredMethods();

// //         // Assertions for void methods
// //         assertAll(
// //             () -> assertTrue(hasVoidMethod(methods, "resetBoard"), "resetBoard method is missing"),
// //             () -> assertTrue(hasVoidMethod(methods, "initializeBoard"), "initializeBoard method is missing"),
// //             () -> assertTrue(hasVoidMethod(methods, "setUpPits"), "setUpPits method is missing"),
// //             () -> assertTrue(hasVoidMethod(methods, "setUpStores"), 
// //               "setUpStores method is missing"),
// //             () -> assertTrue(hasVoidMethod(methods, "registerPlayers", 
// //             Player.class, Player.class), "registerPlayers method is missing")
// //         );

// //         // Assertions for methods with non-void return types
// //         assertAll
// //(
// //             () -> assertTrue(hasMethod(methods, "moveStones", 
// //              int.class, Player.class), "moveStones method is missing"),
// //             () -> assertTrue(hasMethod(methods, "distributeStones", 
// //                 int.class), "distributeStones method is missing"),
// //             () -> assertTrue(hasMethod(methods, "captureStones", int.class)
// //                , "captureStones method is missing"),
// //             () -> assertTrue(hasMethod(methods, "getPits"), "getPits method is missing"),
// //             () -> assertTrue(hasMethod(methods, "getStores")
// //                 , "getStores method is missing"),
// //             () -> assertTrue(hasMethod(methods, "isSideEmpty",
// //                 int.class), "isSideEmpty method is missing"),            
// //             () -> assertTrue(hasMethod(methods, "getNumStones", int.class), "getNumStones method is missing"),
// //             () -> assertTrue(hasMethod(methods, "toString"), "toString method is missing")
// //         );
// //     }

// //     private boolean hasVoidMethod(Method[] methods, String methodName, Class<?>... parameterTypes) {
// //         for (Method method : methods) {
// //             if (method.getName().equals(methodName) && method.getReturnType() == void.class &&
// //                 parameterTypesMatch(method.getParameterTypes(), parameterTypes)) {
// //                 return true;
// //             }
// //         }
// //         return false;
// //     }

// //     private boolean hasMethod(Method[] methods, String methodName, Class<?>... parameterTypes) {
// //         for (Method method : methods) {
// //             if (method.getName().equals(methodName) && method.getReturnType() != void.class &&
// //                 parameterTypesMatch(method.getParameterTypes(), parameterTypes)) {
// //                 return true;
// //             }
// //         }
// //         return false;
// //     }

// //     private boolean parameterTypesMatch(Class<?>[] parameterTypes, Class<?>... expectedTypes) {
// //         if (parameterTypes.length != expectedTypes.length) {
// //             return false;
// //         }
// //         for (int i = 0; i < parameterTypes.length; i++) {
// //             if (!parameterTypes[i].equals(expectedTypes[i])) {
// //                 return false;
// //             }
// //         }
// //         return true;
// //     }
// // }