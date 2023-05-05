package src.Model;

import java.util.concurrent.ThreadLocalRandom;

public class Dungeon {


    public static void main(String[] args) {
//        System.out.println();
        Dungeon d = new Dungeon(DEFAULT_ROWS, DEFAULT_COLUMNS);
        for (int i = 0; i < d.myDungeon.length; i++) {
            for (int j = 0; j < d.myDungeon.length; j++) {
                System.out.print(d.myDungeon[i][j]);
            }
            System.out.println();
        }
    }


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
    final private static int PERCENTAGE_OF_WALLS = 30;


    public Dungeon(final int theRows, final int theCols) {


        int pillarCount = 0;
        this.myDungeon = new char[theRows][theCols];

        int i, j;

        for (i = 0; i < theRows; i++) {
            for (j = 0; j < theCols; j++) {

                int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);

                //add wall
                if (i == 0 || i == theRows - 1 || j == 0 || j == theCols - 1 || randomNum <= PERCENTAGE_OF_WALLS) {
                    myDungeon[i][j] = 'X';
                }
                //add empty room
                else {
                    myDungeon[i][j] = 'O';
                }
            }
        }

            boolean isFourPillars = false, isExit = false, isStart = false;

            do {
                int randomRow = ThreadLocalRandom.current().nextInt(1, 10 + 1);
                int randomCol = ThreadLocalRandom.current().nextInt(1, 10 + 1);

                if (!(isFourPillars) && myDungeon[randomRow][randomCol] == 'O') {
                    myDungeon[randomRow][randomCol] = 'P';
                    pillarCount++;
                    if (pillarCount == 4) {
                        isFourPillars = true;
                    }
                } else if (!(isStart) && myDungeon[randomRow][randomCol] == 'O') {
                    myDungeon[randomRow][randomCol] = 'S';
                    isStart = true;
                } else if (!(isExit) && myDungeon[randomRow][randomCol] == 'O') {
                    myDungeon[randomRow][randomCol] = 'E';
                    isExit = true;
                }
            } while (!(isFourPillars && isExit && isStart));


        //check if traversable
        //IDEA: shuffle array if something isn't traversable? or maybe just call Dungeon() again
//        for (i = 1; i < theRows - 1; i++){
//            for (j = 1; j < theCols - 1; j++){
//
//            }
//        }

    }
}


