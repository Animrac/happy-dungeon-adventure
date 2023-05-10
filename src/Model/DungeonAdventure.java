package src.Model;

import java.util.Scanner;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/9/23
 */
public class DungeonAdventure {


    /**
     * Driver method for class (and entire game)
     * Calls displayIntro() class to display an introduction to the game.
     * Calls HeroesVersusMonsters.gamePlay() to run game play.
     *
     * @param args
     */
    public static void main(String[] args) { //this main method is just to test
        displayIntro();
        Dungeon layout = null;
        Scanner input = new Scanner(System.in);

        System.out.println("--Would you like a custom dungeon? Y/N--");
        String custom = input.nextLine();  // Read user input

        if (custom.equals("Y") || custom.equals("y") || custom.equals("yes") || custom.equals("Yes")) { //i know there are better ways but im lazy rn
            System.out.println("--Rows (must be at least 5):--");
            int cRows = Integer.parseInt(input.nextLine()) + 2;
            System.out.println("--Columns (must be at least 5):--");
            int cCols = Integer.parseInt(input.nextLine()) + 2;
            layout = new Dungeon(cRows, cCols);
        }
        else {
            layout = new Dungeon();
        }

        //TODO: USE REGEX FOR INVALID INPUT
        //VISUALIZATION TEST CODE
        System.out.println("Dungeon:");
        for (int i = 0; i < layout.getMyDungeonLayout().length; i++) {
            for (int j = 0; j < layout.getMyDungeonLayout()[i].length; j++) {
                System.out.print(layout.getMyDungeonLayout()[i][j]);
            }
            System.out.println();
        }


        //getMyDungeonRooms()[y][x]
        System.out.println();

        System.out.println("Room (1,1):");
        System.out.print(layout.getMyDungeonRooms()[1][1].toString());

        System.out.println();

        System.out.println("Room (3,5):");
        System.out.print(layout.getMyDungeonRooms()[5][3].toString());

        System.out.println();

        System.out.println("Room (2,4):");
        System.out.print(layout.getMyDungeonRooms()[4][2].toString());

        //PRINT TEST ALL THE ROOMS EVERYWHERE, this does not include the boundary
        for (int i = 1; i < layout.getMyDungeonRooms().length; i++) {
            for (int j = 1; j < layout.getMyDungeonRooms()[i].length; j++) {
                System.out.println("Room (" + j + ", " + i + ")");
                System.out.println(layout.getMyDungeonRooms()[i][j]);
            }
            System.out.println();
        }


//        System.out.println();
//        System.out.println("Traversed:"); //shouldn't show any P, E, or S
//        for (int i = 0; i < tempDungeon.length; i++) {
//            for (int j = 0; j < tempDungeon[i].length; j++) {
//                System.out.print(tempDungeon[i][j]);
//            }
//            System.out.println();
//        }


    }
    /**
     * Prints out an introduction to the game.
     */
    public static void displayIntro() {
        System.out.println("You are about to begin a Happy Adventure!");
        System.out.println("\nBut wait!!!"
                + "\n\nUnfortunately, you were thrown in a dungeon by the scary Tom Nook because you forgot to pay your bills on time. "
                + "\n\nNow, you must venture on a dangerous expedition to collect the four pillars of OO, all while paying your rent to make it out of the dungeon alive."
                + "\n\nGood luck! You'll need it!\n");
    }

}
