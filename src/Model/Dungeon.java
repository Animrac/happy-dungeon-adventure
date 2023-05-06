package src.Model;

import java.util.concurrent.ThreadLocalRandom;

public class Dungeon {

    public static void main(String[] args) { //this main method is just to test

        Dungeon d = null;
        char[][] tempDungeon = new char[0][]; //we don't need this at this scope level, it's just to test

        while (!(itTraversed)) {

            d = new Dungeon(DEFAULT_ROWS, DEFAULT_COLUMNS);
            tempDungeon = new char[DEFAULT_ROWS][DEFAULT_COLUMNS]; //to keep the original dungeon

            for (int i = 0; i < d.myDungeon.length; i++) {
                for (int j = 0; j < d.myDungeon.length; j++) {
                    tempDungeon[i][j] = d.myDungeon[i][j];
                }
            }

            isTraversable(tempDungeon, startRow, startCol, 0, false);
//            isTraversable(tempDungeon, startRow, startCol);

        }

        //VISUALIZATION
        System.out.println("Original:");
        for (int i = 0; i < d.myDungeon.length; i++) {
            for (int j = 0; j < d.myDungeon[i].length; j++) {
                System.out.print(d.myDungeon[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Traversed:");
        for (int i = 0; i < tempDungeon.length; i++) {
            for (int j = 0; j < tempDungeon[i].length; j++) {
                System.out.print(tempDungeon[i][j]);
            }
            System.out.println();
        }


    }

    private static boolean itTraversed = false;
    /**
     * 2d array dungeon maze.
     */
    final private char myDungeon[][];


    /**
     * Default rows for the dungeon maze.
     */
    final private static int DEFAULT_ROWS = 12;

    /**
     * Default columns for the dungeon maze.
     */
    final private static int DEFAULT_COLUMNS = 12;

    /**
     * Percentage that walls appear.
     */
    final private static int PERCENTAGE_OF_WALLS = 50;

    private static int startRow = -1;
    private static int startCol = -1;

    //TODO: When the Dungeon is initialized, it should ALREADY be traversable.
    // This means we have to check if it's traversable inside the Constructor.
    public Dungeon(final int theRows, final int theCols) {

        int pillarCount = 0;
        this.myDungeon = new char[theRows][theCols];

        int i, j;

        for (i = 0; i < theRows; i++) {
            for (j = 0; j < theCols; j++) {

                int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);

                //add wall
                if (i == 0 || i == theRows - 1 || j == 0 || j == theCols - 1 || randomNum <= PERCENTAGE_OF_WALLS) {
                    this.myDungeon[i][j] = 'X';
                }
                //add empty room
                else {
                    this.myDungeon[i][j] = 'O';
                }
            }
        }

        boolean isFourPillars = false, isExit = false, isStart = false;

        do {
            int randomRow = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            int randomCol = ThreadLocalRandom.current().nextInt(1, 10 + 1);

            if (!(isFourPillars) && this.myDungeon[randomRow][randomCol] == 'O') {
                this.myDungeon[randomRow][randomCol] = 'P';
                pillarCount++;
                if (pillarCount == 4) {
                    isFourPillars = true;
                }
            } else if (!(isStart) && this.myDungeon[randomRow][randomCol] == 'O') {
                this.myDungeon[randomRow][randomCol] = 'S';
                this.startRow = randomRow;
                this.startCol = randomCol;
                isStart = true;
            } else if (!(isExit) && this.myDungeon[randomRow][randomCol] == 'O') {
                this.myDungeon[randomRow][randomCol] = 'E';
                isExit = true;
            }
        } while (!(isFourPillars && isExit && isStart));

    }

    /* My original, less pretty code for isTraversable: */

    private static void isTraversable(char[][] theDungeon, int theCurrRow, int theCurrCol,
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

        if (theDungeon[theCurrRow][theCurrCol + 1] != 'X'){ //Look South.

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
            System.out.println(theTouchPillars);
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
}


