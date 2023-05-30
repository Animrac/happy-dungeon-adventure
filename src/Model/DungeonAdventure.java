package src.Model;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/29/23
 */
public class DungeonAdventure {

    private static final DungeonAdventure instance = new DungeonAdventure();

    /**
     * Random object used to generate random monsters.
     */
    private static final Random MY_RANDOM = new Random();

    private String myName;

    private String myClass;

    private String myDifficulty;

    private boolean inGame = false;

    private Dungeon myDungeonLayout;

    private Inventory myInventory = new Inventory();

    private Room myRoom;

    private String currScene;

    private String[] myPillars = new String[]{"ABSTRACTION", "ENCAPSULATION", "INHERITANCE", "POLYMORPHISM"};

    /**
     * Randomly generates a kind of monster.
     *
     * @return randoMonster
     */
    public static Monster spawnMonster() {
//        Scanner in  = new Scanner(System.in);
//
//        int rand = MY_RANDOM.nextInt(3);
//
//        Monster randoMonster = null;
//
//        if (rand == 0) {
//            randoMonster = new monsterOne();
//        } else if (rand == 1) {
//            randoMonster = new monsterTwo();
//        } else {
//            randoMonster = new monsterThree();
//        }
//        return randoMonster;
        return MonsterFactory.getRandomMonster();
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
        System.out.println("Oh no! A " + monster.getClass() + " has spawned!");
        System.out.println();

        Hero hero = chooseHero();
        System.out.println("Woah! " + hero.getName() + ", a " + hero.getClass() + ", dares to enter the dungeon!");
        System.out.println();

        battle(hero, monster);
    }

    //GETTERS//
    public static DungeonAdventure getInstance() {
        return instance;
    }

    public Dungeon getMyDungeonLayout() {
        return myDungeonLayout;
    }

    public String getCurrScene() {
        return currScene;
    }

    public String getMyName() {
        return myName;
    }

    public String getMyClass() {
        return myClass;
    }

    public Inventory getMyInventory() {
        return myInventory;
    }

    public Room getMyRoom() {
        return myRoom;
    }

    //SETTERS//
    public void setMyDungeonLayout(Dungeon theDungeonLayout) {
        myDungeonLayout = theDungeonLayout;
    }

    public void setCurrScene(String theCurrScene) {
        currScene = theCurrScene;
    }

    public void setMyName(String theName) {
        myName = theName;
    }

    public void setMyClass(String theClass) {
        myClass = theClass;
    }

    public boolean getInGame() {
        return inGame;
    }

    public void setInGame(boolean theInGame) {
        inGame = theInGame;
    }

    public void setMyRoom(Room theRoom) {
        myRoom = theRoom;
    }

    public void setMyDifficulty(String theDifficulty) {
        myDifficulty = theDifficulty;
    }

    public String getMyDifficulty() {
        return myDifficulty.toString();
    }

    public String[] getMyPillars(){
        return myPillars;
    }
}
