package src.Model;

import java.util.Scanner;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 04/24/23
 */
public class DungeonAdventure {


    /**
     * Driver method for class (and entire game)
     * Calls displayIntro() class to display an introduction to the game.
     * Calls HeroesVersusMonsters.gamePlay() to run game play.
     *
     * @param args
     */
//    public static void main(String[] args) {
//
//        //should probably research Java GUI stuff
//
//        displayIntro();
//        //HeroesVersusMonsters.gamePlay();
//    }

    public static void main(String[] args) { //this main method is just to test

        Dungeon layout = null;

        Scanner input = new Scanner(System.in);
        System.out.println("Would you like a custom dungeon? Y/N");

        String custom = input.nextLine();  // Read user input
        if (custom.equals("Y") || custom.equals("y") || custom.equals("yes") || custom.equals("Yes")) { //i know there are better ways but im lazy rn
            //TODO 3 works, but it's randomly generated... make it a big number for testing
            System.out.println("Rows (must be at least 3): ");
            int cRows = Integer.parseInt(input.nextLine()) + 2;
            System.out.println("Columns (must be at least 3): ");
            int cCols = Integer.parseInt(input.nextLine()) + 2;
            layout = new Dungeon(cRows, cCols);
        }
        else {
            layout = new Dungeon();
        }

//TODO for Room class
//        Dungeon.addRooms(layout);

        //VISUALIZATION
        System.out.println("Original:");
        for (int i = 0; i < layout.getMyDungeon().length; i++) {
            for (int j = 0; j < layout.getMyDungeon()[i].length; j++) {
                System.out.print(layout.getMyDungeon()[i][j]);
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
        System.out.println("You are about to begin a game of * HEROES VS MONSTERS *");
        System.out.println();
        System.out.println("In this game you will choose a hero to represent you"
                + "\nas you enter a dungeon maze with mysterious rooms..."
                + "\n\nThere is only one entrance and exit to the maze, but before you may leave"
                + "\nyour hero must retrieve two pieces of the Crown of Coding..."
                + "\n\nGood luck! You'll need it!\n");
    }

}
