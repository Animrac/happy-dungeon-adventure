package src.Model;

import java.util.concurrent.ThreadLocalRandom;

public class Dungeon {
    /**
     * Default rows for the dungeon maze.
     */
    private static int dungeonRows;

    /**
     * Default columns for the dungeon maze.
     */
    private static int dungeonColumns;
    private static boolean itTraversed;

    private static int startRow = -1;
    private static int startCol = -1;
    /**
     * 2d array of the dungeon layout. This does not contain the rooms.
     */
    final private char[][] myDungeonLayout;

    /**
     * 2d array of the dungeon with Room objects.
     */
    private Room[][] myDungeon;


    /**
     * Percentage that walls appear.
     */
    final private static int PERCENTAGE_OF_WALLS = 50;


    public Dungeon() { //default 12x12
        this.dungeonRows = 12;
        this.dungeonColumns = 12;
        this.myDungeonLayout = new char[dungeonRows][dungeonColumns];
        makeDungeon();
    }

    public Dungeon(final int theRows, final int theCols) { //custom dungeon
        this.dungeonRows = theRows;
        this.dungeonColumns = theCols;
        this.myDungeonLayout = new char[theRows][theCols];
        makeDungeon();
    }

    /**
     * Randomly fills empty space with walls, the four pillars, the start, and the exit.
     */
    private void makeDungeon() {

        int pillarCount = 0;

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

        do {
            int randomRow = ThreadLocalRandom.current().nextInt(1, dungeonRows - 2 + 1); //-2 for the walls, +1 for the boundary
            int randomCol = ThreadLocalRandom.current().nextInt(1,  dungeonColumns - 2 + 1);

            if (!(isFourPillars) && this.myDungeonLayout[randomRow][randomCol] == 'O') {
                this.myDungeonLayout[randomRow][randomCol] = 'P';
                pillarCount++;
                if (pillarCount == 4) {
                    isFourPillars = true;
                }
            } else if (!isStart && this.myDungeonLayout[randomRow][randomCol] == 'O') {
                this.myDungeonLayout[randomRow][randomCol] = 'S';
                this.startRow = randomRow;
                this.startCol = randomCol;
                isStart = true;
            } else if (!isExit && this.myDungeonLayout[randomRow][randomCol] == 'O') {
                this.myDungeonLayout[randomRow][randomCol] = 'E';
                isExit = true;
            }
        } while (!(isFourPillars && isExit && isStart));

        ensureTraversable();

    }

    /**
     * Creates a new dungeon if the current dungeon is not traversable.
     */
    private void ensureTraversable() {

        char[][] tempDungeon = new char[0][];
        boolean firstRun = true;

        while (!(this.isItTraversable())) {

            if (!firstRun){
                makeDungeon();
            }

            firstRun = false;

            tempDungeon = new char[this.dungeonRows][this.dungeonColumns]; //to keep the original dungeon

            for (int i = 0; i < this.dungeonRows; i++) {
                for (int j = 0; j < this.dungeonColumns; j++) {
                    tempDungeon[i][j] = this.myDungeonLayout[i][j];
                }
            }

            isTraversable(tempDungeon, this.startRow, this.startCol, 0, false);
//            System.out.println(this.isItTraversable());
//            isTraversable(tempDungeon, startRow, startCol);
        }

//        //this is good test code
//        System.out.println();
//        System.out.println("Traversed:"); //shouldn't show any P, E, or S
//        for (int i = 0; i < tempDungeon.length; i++) {
//            for (int j = 0; j < tempDungeon[i].length; j++) {
//                System.out.print(tempDungeon[i][j]);
//            }
//            System.out.println();
//        }

    }

    /* My original, less pretty code for isTraversable: */

    /**
     * Ensures that the player can traverse and access the four pillars, the start, and the exit.
     * @param theDungeon The dungeon being checked.
     * @param theCurrRow The current row being checked.
     * @param theCurrCol The current column being checked.
     * @param theTouchPillars How many pillars can be accessed.
     * @param theTouchExit If the exit can be accessed.
     */
    public static void isTraversable(char[][] theDungeon, int theCurrRow, int theCurrCol,
                                         int theTouchPillars, boolean theTouchExit) {

        if (theDungeon[theCurrRow - 1][theCurrCol] != 'X'){ //Look West.

            if (theDungeon[theCurrRow - 1][theCurrCol] == 'P') {
                theTouchPillars++;
            }
            else if (theDungeon[theCurrRow - 1][theCurrCol] == 'E') {
                theTouchExit = true;
            }

            theDungeon[theCurrRow - 1][theCurrCol] = 'X'; //We don't need to look at it anymore.

            isTraversable(theDungeon, theCurrRow - 1, theCurrCol, theTouchPillars, theTouchExit);

        }

        if (theDungeon[theCurrRow][theCurrCol - 1] != 'X'){ //Look North.

            if (theDungeon[theCurrRow][theCurrCol - 1] == 'P') {
                theTouchPillars++;
            }
            else if (theDungeon[theCurrRow][theCurrCol - 1] == 'E') {
                theTouchExit = true;
            }

            theDungeon[theCurrRow][theCurrCol - 1] = 'X'; //We don't need to look at it anymore.

            isTraversable(theDungeon, theCurrRow, theCurrCol - 1, theTouchPillars, theTouchExit);

        }

        if (theDungeon[theCurrRow + 1][theCurrCol] != 'X'){ //Look East.

            if (theDungeon[theCurrRow + 1][theCurrCol] == 'P') {
                theTouchPillars++;
            }
            else if (theDungeon[theCurrRow + 1][theCurrCol] == 'E') {
                theTouchExit = true;
            }

            theDungeon[theCurrRow + 1][theCurrCol] = 'X'; //We don't need to look at it anymore.

            isTraversable(theDungeon, theCurrRow + 1, theCurrCol, theTouchPillars, theTouchExit);

        }

        if (theDungeon[theCurrRow][theCurrCol + 1] != 'X') { //Look South.

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
//            System.out.println(theTouchPillars);
            itTraversed = true;
        }

    }

    /* ChatGPT with some cleanup for isTraversable, but buggy: */

//    private static int theTouchPillars = 0;
//    private static boolean theTouchExit = false;
//
//    private static void isTraversable(char[][] theDungeon, int theCurrRow, int theCurrCol) {
//        traverseWest(theDungeon, theCurrRow, theCurrCol);
//        traverseNorth(theDungeon, theCurrRow, theCurrCol);
//        traverseEast(theDungeon, theCurrRow, theCurrCol);
//        traverseSouth(theDungeon, theCurrRow, theCurrCol);
//
        //TODO: There is a bug here. We might touch the SAME pillar more than once. This does not seem to apply to my code.
//        if (theTouchPillars >= 4 && theTouchExit) {
//            itTraversed = true;
//        }
//
//    }
//
//    private static void traverseWest(char[][] theDungeon, int theCurrRow, int theCurrCol) {
//        if (theDungeon[theCurrRow - 1][theCurrCol] != 'X') {
//
//            if (theDungeon[theCurrRow - 1][theCurrCol] == 'P') {
//                theTouchPillars++;
//            } else if (theDungeon[theCurrRow - 1][theCurrCol] == 'E') {
//                theTouchExit = true;
//            }
//
//            theDungeon[theCurrRow - 1][theCurrCol] = 'X';
//            isTraversable(theDungeon, theCurrRow - 1, theCurrCol);
//        }
//    }
//
//    private static void traverseNorth(char[][] theDungeon, int theCurrRow, int theCurrCol) {
//        if (theDungeon[theCurrRow][theCurrCol - 1] != 'X') {
//
//            if (theDungeon[theCurrRow][theCurrCol - 1] == 'P') {
//                theTouchPillars++;
//            } else if (theDungeon[theCurrRow][theCurrCol - 1] == 'E') {
//                theTouchExit = true;
//            }
//
//            theDungeon[theCurrRow][theCurrCol - 1] = 'X';
//            isTraversable(theDungeon, theCurrRow, theCurrCol - 1);
//        }
//    }
//
//    private static void traverseEast(char[][] theDungeon, int theCurrRow, int theCurrCol) {
//        if (theDungeon[theCurrRow + 1][theCurrCol] != 'X') {
//
//            if (theDungeon[theCurrRow + 1][theCurrCol] == 'P') {
//                theTouchPillars++;
//            } else if (theDungeon[theCurrRow + 1][theCurrCol] == 'E') {
//                theTouchExit = true;
//            }
//
//            theDungeon[theCurrRow + 1][theCurrCol] = 'X';
//            isTraversable(theDungeon, theCurrRow + 1, theCurrCol);
//        }
//    }
//
//    private static void traverseSouth(char[][] theDungeon, int theCurrRow, int theCurrCol) {
//        if (theDungeon[theCurrRow][theCurrCol + 1] != 'X') {
//            if (theDungeon[theCurrRow][theCurrCol + 1] == 'P') {
//                theTouchPillars++;
//            } else if (theDungeon[theCurrRow][theCurrCol + 1] == 'E') {
//                theTouchExit = true;
//            }
//
//            theDungeon[theCurrRow][theCurrCol + 1] = 'X';
//            isTraversable(theDungeon, theCurrRow, theCurrCol + 1);
//
//        }
//    }


    /* Getters and setters */

    public boolean isItTraversable() {
        return itTraversed;
    }

    public char[][] getMyDungeon() {
        return myDungeonLayout;
    }

    public int getPercentageOfWalls() {
        return PERCENTAGE_OF_WALLS;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int theStartRow) {
        startRow = theStartRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int theStartCol) {
        startCol = theStartCol;
    }

//    //TODO For the Room class.
//    public void addRooms(Dungeon theDungeonLayout){
//        for (int i = 0; i < theDungeonLayout.myDungeonLayout.length; i++) {
//            for (int j = 0; j < theDungeonLayout.myDungeonLayout[i].length; j++) {
//                this.myDungeon[i][j] = new Room(theDungeonLayout.myDungeonLayout[i][j]);
//            }
//        }
//    }

}


