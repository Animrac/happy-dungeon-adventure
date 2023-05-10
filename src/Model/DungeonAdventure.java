package src.Model;

import java.util.Random;
import java.util.Scanner;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/9/23
 */
public class DungeonAdventure {

    /**
     * Random object used to generate random monsters.
     */
    private static final Random MY_RANDOM = new Random();

    /**
     * Driver method for class (and entire game)
     * Calls displayIntro() class to display an introduction to the game.
     * Runs game play.
     *
     * @param theArgs
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
        } else {
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

        gamePlay();

    }


//        System.out.println();
//        System.out.println("Traversed:"); //shouldn't show any P, E, or S
//        for (int i = 0; i < tempDungeon.length; i++) {
//            for (int j = 0; j < tempDungeon[i].length; j++) {
//                System.out.print(tempDungeon[i][j]);
//            }
//            System.out.println();
//        }

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

    /**
     * Prompts user to enter name of monster,
     * and then randomly generates a kind of monster.
     *
     * @return randoMonster
     */
    public static Monster spawnMonster() {
        Scanner in  = new Scanner(System.in);

        System.out.print("First, choose a name for a monster: ");
        String name = in.next();

        int rand = MY_RANDOM.nextInt(3);

        Monster randoMonster = null;

        if (rand == 0) {
            randoMonster = new monsterOne(name);
        } else if (rand == 1) {
            randoMonster = new monsterTwo(name);
        } else {
            randoMonster = new monsterThree(name);
        }
        return randoMonster;
    }

    /**
     * Prompts user to name their hero,
     * and then allows them to pick between three types of heroes to play as.
     *
     * @return chosenHero
     */
    public static Hero chooseHero() {
        Scanner in = new Scanner(System.in);

        System.out.print("Now choose a name for your hero: ");
        String name = in.next();

        System.out.println("Time to choose your hero!");
        System.out.println();
        System.out.println("1: heroOne \n2: heroTwo \n3: heroThree");
        System.out.println();

        Hero chosenHero = null;

        if (in.nextInt() == 1) {
            chosenHero = new heroOne(name);
        } else if (in.nextInt() == 2) {
            chosenHero = new heroTwo(name);
        } else if (in.nextInt() == 3) {
            chosenHero = new heroThree(name);
        }
        return chosenHero;
    }

    public static void battle(Hero hero, Monster monster) {
        while (hero.alive() && monster.alive() && !hero.runAway()) {
            System.out.println(hero.getName() + " hit points: " + hero.getHealth());
            System.out.println(monster.getName() + "hit points: " + monster.getHealth());
            hero.attack(monster);

            if(!hero.runAway()) {
                monster.attack(hero);
                System.out.println("Press enter for next round");
                Adventurer.input.nextLine();
            }
            else {
                System.out.println(hero.getName() + " bravely ran away...");
            }
        }
        if(monster.alive()) {
            System.out.println(monster.getName() + " defeated " + hero.getName() + "!");
        }
        else {
            System.out.println(hero.getName() + " defeated " + monster.getName() + "!");
        }
    }

    /**
     *
     */
    public static void gamePlay() {
        Monster monster = spawnMonster();
        System.out.println("Oh no! A " + monster.getClass() + " has spawned!\nIt is named " + monster.getName());
        System.out.println();

        Hero hero = chooseHero();
        System.out.println("Woah! " + hero.getName() + ", a " + hero.getClass() + ", dares to enter the dungeon!");
        System.out.println();

        battle(hero, monster);
    }
}
