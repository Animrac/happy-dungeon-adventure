package src.Model;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

    /*

    Dungeon Map 2d Array Key:
    X - Wall
    O - Empty Room
    S - Start/Entrance (In)
    E - Exit (Out)
    A, E, I, P - Pillars

     */

public class Dungeon implements Serializable {

    private final int INITIAL_VALUE = -1;

    private final int DEFAULT_DUNGEON_DIMENSION = 12;

    /**
     * Default rows for the dungeon maze.
     */
    private int dungeonRows;

    /**
     * Default columns for the dungeon maze.
     */
    private int dungeonColumns;

    /**
     * This boolean tells us if the dungeon can be successfully traversed or not.
     */
    private static boolean itTraversed;

    private int currCol;

    private int currRow;

    /**
     * The dungeon row the player will start in.
     */
    private int startRow;

    /**
     * The dungeon column the player will start in.
     */
    private int startCol;

    /**
     * 2d array of the dungeon layout. This does not contain the rooms.
     */
    final private char[][] myDungeonLayout;

    /**
     * 2d array of the dungeon with Room objects.
     */
    private Room[][] myDungeonRooms;


    /**
     * Percentage that walls appear.
     */
    final private int PERCENTAGE_OF_WALLS = 40;


    /**
     * The constructor for a default dungeon, which have the dimensions of 12 x 12.
     */
    public Dungeon() {
        this.currCol = INITIAL_VALUE;
        this.currRow = INITIAL_VALUE;
        this.startCol = INITIAL_VALUE;
        this.startRow = INITIAL_VALUE;
        this.dungeonRows = DEFAULT_DUNGEON_DIMENSION;
        this.dungeonColumns = DEFAULT_DUNGEON_DIMENSION;
        this.myDungeonLayout = new char[dungeonRows][dungeonColumns];
        this.myDungeonRooms = new Room[dungeonRows][dungeonColumns];
        makeDungeon();
        addRooms();
    }

    /**
     * The constructor for a custom dungeon, which you can input the custom rows and column dimensions.
     */
    public Dungeon(final int theRows, final int theCols) {
        this.currCol = INITIAL_VALUE;
        this.currRow = INITIAL_VALUE;
        this.startCol = INITIAL_VALUE;
        this.startRow = INITIAL_VALUE;
        this.dungeonRows = theRows + 2;
        this.dungeonColumns = theCols + 2;
        this.myDungeonLayout = new char[dungeonRows][dungeonColumns];
        this.myDungeonRooms = new Room[dungeonRows][dungeonColumns];
        makeDungeon();
        addRooms();
    }

    /**
     * Randomly fills empty space with walls, the four pillars, the start, and the exit.
     */
    private void makeDungeon() {

        int pillarCount = 0; // To keep track that there will be four pillars.

        boolean isFourPillars = false, isExit = false, isStart = false;

        for (int i = 0; i < this.dungeonRows; i++) {
            for (int j = 0; j < this.dungeonColumns; j++) {

                int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);

                //add wall
                if (i == 0 || i == this.dungeonRows - 1 || j == 0 || j == this.dungeonColumns - 1
                        || randomNum <= PERCENTAGE_OF_WALLS) {
                    this.myDungeonLayout[i][j] = 'X';
                }
                //add empty room
                else {
                    this.myDungeonLayout[i][j] = 'O';
                }
            }
        }

        do { // Do add pillars, entrance, and exit to a random empty room,
             // while there are not enough pillars, no entrance, and no exit.
            int randomRow = ThreadLocalRandom.current().nextInt(1, dungeonRows - 2 + 1); //-2 for the walls, +1 for the boundary
            int randomCol = ThreadLocalRandom.current().nextInt(1,  dungeonColumns - 2 + 1);
            if (!isFourPillars && this.myDungeonLayout[randomRow][randomCol] == 'O') { //if there aren't four pillars yet and land in an empty room
                this.myDungeonLayout[randomRow][randomCol] = 'P';
                pillarCount++;
                if (pillarCount == 4) {
                    isFourPillars = true;
                }
            } else if (!isStart && this.myDungeonLayout[randomRow][randomCol] == 'O') { //if there isn't an entrance yet and land in an empty room
                this.myDungeonLayout[randomRow][randomCol] = 'S';
                this.startRow = randomRow;
                this.startCol = randomCol;
                isStart = true;
            } else if (!isExit && this.myDungeonLayout[randomRow][randomCol] == 'O') { //if there isn't an exit yet and land in an empty room
                this.myDungeonLayout[randomRow][randomCol] = 'E';
                isExit = true;
            }
        }
        while (!(isFourPillars && isExit && isStart));

        ensureTraversable();

    }

    /**
     * Creates a new dungeon if the current dungeon is not traversable.
     */
    private synchronized void ensureTraversable() {

        char[][] tempDungeon = new char[0][];

        boolean firstRun = true; // This switch has to be here to make sure another dungeon isn't created after
                                 // the first one without checking it.

        while (!(this.isItTraversable())) { // While the current Dungeon has not been determined to be traversable:

            if (!firstRun){ // If it's not the first run, then the first run failed.
                            // So, make a new Dungeon to see if that one is traversable.
                makeDungeon();
            }

            firstRun = false; // It's no longer the first run.

            tempDungeon = new char[this.dungeonRows][this.dungeonColumns]; // To keep the original dungeon.

            for (int i = 0; i < this.dungeonRows; i++) {
                for (int j = 0; j < this.dungeonColumns; j++) {
                    tempDungeon[i][j] = this.myDungeonLayout[i][j];
                }
            }

            isTraversable(tempDungeon, this.startRow, this.startCol, 0, false);
        }

    }

    /**
     * Ensures that the player can traverse and access the four pillars, the start, and the exit.
     * @param theDungeon The dungeon being checked.
     * @param theCurrRow The current row being checked.
     * @param theCurrCol The current column being checked.
     * @param theTouchPillars How many pillars can be accessed.
     * @param theTouchExit If the exit can be accessed.
     */
    public static synchronized void isTraversable(char[][] theDungeon, int theCurrRow, int theCurrCol,
                                     int theTouchPillars, boolean theTouchExit) {

        if (theDungeon[theCurrRow - 1][theCurrCol] != 'X'){ //Look North.

            if (theDungeon[theCurrRow - 1][theCurrCol] == 'P') {
                theTouchPillars++;
            }
            else if (theDungeon[theCurrRow - 1][theCurrCol] == 'E') {
                theTouchExit = true;
            }

            theDungeon[theCurrRow - 1][theCurrCol] = 'X'; //We don't need to look at it anymore.

            isTraversable(theDungeon, theCurrRow - 1, theCurrCol, theTouchPillars, theTouchExit);

        }

        if (theDungeon[theCurrRow][theCurrCol - 1] != 'X'){ //Look West.

            if (theDungeon[theCurrRow][theCurrCol - 1] == 'P') {
                theTouchPillars++;
            }
            else if (theDungeon[theCurrRow][theCurrCol - 1] == 'E') {
                theTouchExit = true;
            }

            theDungeon[theCurrRow][theCurrCol - 1] = 'X'; //We don't need to look at it anymore.

            isTraversable(theDungeon, theCurrRow, theCurrCol - 1, theTouchPillars, theTouchExit);

        }

        if (theDungeon[theCurrRow + 1][theCurrCol] != 'X'){ //Look South.

            if (theDungeon[theCurrRow + 1][theCurrCol] == 'P') {
                theTouchPillars++;
            }
            else if (theDungeon[theCurrRow + 1][theCurrCol] == 'E') {
                theTouchExit = true;
            }

            theDungeon[theCurrRow + 1][theCurrCol] = 'X'; //We don't need to look at it anymore.

            isTraversable(theDungeon, theCurrRow + 1, theCurrCol, theTouchPillars, theTouchExit);

        }

        if (theDungeon[theCurrRow][theCurrCol + 1] != 'X') { //Look East.

            if (theDungeon[theCurrRow][theCurrCol + 1] == 'P') {
                theTouchPillars++;
            }
            else if (theDungeon[theCurrRow][theCurrCol + 1] == 'E') {
                theTouchExit = true;
            }

            theDungeon[theCurrRow][theCurrCol + 1] = 'X'; //We don't need to look at it anymore.
            
            isTraversable(theDungeon, theCurrRow, theCurrCol + 1, theTouchPillars, theTouchExit);

        }

        if (theTouchPillars == 4 && theTouchExit) {
            itTraversed = true;
        }

    }

    /**
     * Adds Room objects to a 2d array based on the Dungeon 2d array.
     */
    public synchronized void addRooms(){
        for (int i = 0; i < this.myDungeonLayout.length; i++) { //ROW i
            for (int j = 0; j < this.myDungeonLayout[i].length; j++) { //COLUMN j
                this.myDungeonRooms[i][j] = new Room(myDungeonLayout, i, j, myDungeonLayout[i][j]);
            }
        }
    }

    /* Getters and setters */

    public boolean isItTraversable() {
        return itTraversed;
    }

    public char[][] getMyDungeonLayout() {
        return myDungeonLayout;
    }

    public Room[][] getMyDungeonRooms() {
        return myDungeonRooms;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int theStartRow) {
        startRow = theStartRow;
    } //might be useful for saving

    public void setStartCol(int theStartCol) {
        startCol = theStartCol;
    }

    public int getStartCol() {
        return startCol;
    }

    public char[][] getDungeonLayout() {
        return myDungeonLayout;
    }

//    public void setDungeonLayout(Dungeon dungeonLayout) {
//        this.myDungeonLayout = myDungeonLayout;
//    }

    public int getCurrCol(){
        return currCol;
    }
    public void setCurrCol(int theCol){
        currCol = theCol;
    }

    public int getCurrRow(){
        return currRow;
    }
    public void setCurrRow(int theRow){
        currRow = theRow;
    }

}


