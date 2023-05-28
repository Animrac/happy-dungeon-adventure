package src.Model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import src.Controller.*;
import src.Model.*;

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/18/23
 */
public class DungeonAdventure {

    private static final DungeonAdventure instance = new DungeonAdventure();

    /**
     * Random object used to generate random monsters.
     */
    private static final Random MY_RANDOM = new Random();

    private boolean inGame = false;

    private Dungeon dungeonLayout;

    private Inventory myInventory = new Inventory();

    private Room myRoom;

    private String currScene;

    //like a new game
    private DungeonAdventure (){

        Scanner input = new Scanner(System.in);

        System.out.println("--Would you like a custom dungeon? Y/N--");
        String custom = input.nextLine();  // Read user input

        //i know there are better ways but im lazy rn
        //TODO: USE REGEX FOR INVALID INPUT maybe
        if (custom.equals("Y") || custom.equals("y") || custom.equals("yes") || custom.equals("Yes")) {
            System.out.println("--Rows (must be at least 5):--");
            int cRows = Integer.parseInt(input.nextLine()) + 2;
            System.out.println("--Columns (must be at least 5):--");
            int cCols = Integer.parseInt(input.nextLine()) + 2;
            this.dungeonLayout = new Dungeon(cRows, cCols);
        } else {
            System.out.println("Okay, you're getting a default dungeon.");
            System.out.println();
            this.dungeonLayout = new Dungeon();
        }


        //VISUALIZATION TEST CODE
        System.out.println("Dungeon:");
        for (int i = 0; i < dungeonLayout.getMyDungeonLayout().length; i++) {
            for (int j = 0; j < dungeonLayout.getMyDungeonLayout()[i].length; j++) {
                System.out.print(dungeonLayout.getMyDungeonLayout()[i][j]);
            }
            System.out.println();
        }


        //getMyDungeonRooms()[y/row][x/column]
        System.out.println();

        System.out.println("Room [1][1]:");
        System.out.print(dungeonLayout.getMyDungeonRooms()[1][1].toString());

        System.out.println();

        System.out.println("Room [5][3]:");
        System.out.print(dungeonLayout.getMyDungeonRooms()[5][3].toString());

        System.out.println();

        System.out.println("Room [4][2]:");
        System.out.print(dungeonLayout.getMyDungeonRooms()[4][2].toString());

        System.out.println();

//        //PRINT TEST ALL THE ROOMS EVERYWHERE, this does not include the boundaries
//        for (int i = 1; i < layout.getMyDungeonRooms().length-1; i++) {
//            for (int j = 1; j < layout.getMyDungeonRooms()[i].length-1; j++) {
//                System.out.println("Room [" + i + "][" + j + "]");
//                System.out.println(layout.getMyDungeonRooms()[i][j]);
//            }
//            System.out.println();
//        }

    }

//dungeon adventure will NEVER call view
    //controller calls view and model (dungeon adventure)
    //eg when player clicks button controller
    //set method in model/dungeon adventure, like setting name
    //modularize dungeon adventure
    //controller tells view when model sets name/data/etc
    //model is independent and can run by itself"


    /**
     * Prompts user to enter name of monster,
     * and then randomly generates a kind of monster.
     *
     * @return randoMonster
     */
    public static Monster spawnMonster() {
        Scanner in  = new Scanner(System.in);

        int rand = MY_RANDOM.nextInt(3);

        Monster randoMonster = null;

        if (rand == 0) {
            randoMonster = new monsterOne();
        } else if (rand == 1) {
            randoMonster = new monsterTwo();
        } else {
            randoMonster = new monsterThree();
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
        System.out.println("Oh no! A " + monster.getClass() + " has spawned!");
        System.out.println();

        Hero hero = chooseHero();
        System.out.println("Woah! " + hero.getName() + ", a " + hero.getClass() + ", dares to enter the dungeon!");
        System.out.println();

        battle(hero, monster);
    }

    public static DungeonAdventure getInstance() {
        return instance;
    }

    public Dungeon getDungeonLayout() {
        return this.dungeonLayout;
    }

    public String getCurrScene() {
        return currScene;
    }

    public void setCurrScene(String theCurrScene) {
        this.currScene = theCurrScene;
    }

    public boolean getInGame() {
        return inGame;
    }

    public void setInGame(boolean theInGame) {
        this.inGame = theInGame;
    }

    public Inventory getMyInventory() {
        return myInventory;
    }

    public Room getMyRoom() {
        return myRoom;
    }

    public void setMyRoom(Room theRoom) {
        myRoom = theRoom;
    }

}
