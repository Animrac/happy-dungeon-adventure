package src.Model;

import java.io.*;
import java.util.Random;

/**
 * The state class that holds all the current information about the game, e.g. player, dungeon layout, etc.
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/29/23
 */
public class DungeonAdventure implements Serializable {

    private static final long serialversionUID =
            129348938L;

    private static DungeonAdventure instance = new DungeonAdventure();
//    private static DungeonAdventure instance;

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

    private Hero myHero;

    private String[] myPillars = new String[]{"ABSTRACTION", "ENCAPSULATION", "INHERITANCE", "POLYMORPHISM"};

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

    public Hero getMyHero() {
        return myHero;
    }

    public Inventory getMyInventory() {
        return myInventory;
    }

    public Room getMyRoom() {
        return myRoom;
    }

    //SETTERS//
    public static void setInstance(DungeonAdventure theInstance) {
        instance = theInstance;
    }

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

    public void setMyHero(Hero theHero) {
        myHero = theHero;
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
