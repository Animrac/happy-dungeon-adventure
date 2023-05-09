package src.Model;

import java.util.Random;
import java.util.Scanner;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/07/23
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
    public static void main(String[] theArgs) {

        displayIntro();
        gamePlay();
    }

    /**
     * Prints out an introduction to the game.
     */
    public static void displayIntro() {
        System.out.println("You are about to begin a game of * Happy Dungeon Adventure! *");
        System.out.println();
        System.out.println("In this game you will choose a hero to represent you"
                + "\nas you enter a dungeon maze with mysterious rooms..."
                + "\n\nThere is only one entrance and exit to the maze, but before you may leave"
                + "\nyour hero must retrieve all the pillars of Object-Oriented Programming..."
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
