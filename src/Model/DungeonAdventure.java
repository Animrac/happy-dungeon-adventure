package src.Model;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static src.Model.Adventurer.MY_RANDOM;

/**
 * The state class that holds all the current information about the game, e.g. player, dungeon layout, etc.
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 06/02/23
 */
public class DungeonAdventure implements Serializable {

    private static DungeonAdventure instance = new DungeonAdventure();
//    private static DungeonAdventure instance;

    /**
     * Random object used to generate random monsters.
     */

    private String myName;

    private String myClass;

    private String myDifficulty;

    private boolean inGame = false;

    private boolean inBattle = false;

    private Dungeon myDungeonLayout;

    private Inventory myInventory = new Inventory();

    private Room myRoom;

    private String currScene;

    private Hero myHero;

    private final MonsterFactory myMonsterDatabase = new MonsterFactory();

    private Monster myCurrMonster;

    private final String[] myPillars = new String[]{"ABSTRACTION", "ENCAPSULATION", "INHERITANCE", "POLYMORPHISM"};

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

    public MonsterFactory getMyMonsterDatabase() {
        return myMonsterDatabase;
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
        return myDifficulty;
    }

    public String[] getMyPillars(){
        return myPillars;
    }

    public void setMyCurrMonster (Monster theMonster) {
        myCurrMonster = theMonster;
    }

    public Monster getMyCurrMonster () {
        return myCurrMonster;
    }

    public Inventory setMyInventory(Inventory myInventory) {
            return this.myInventory = myInventory;

    }

    public boolean getInBattle() {
        return inBattle;
    }

    public void setInBattle(boolean theInBattle) {
        inBattle = theInBattle;
    }



    /**
     *
     * @return random monster from database
     */
    public Monster getRandomMonster() {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monsterFactory.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        List<Monster> monsters = new ArrayList<>();
        String query = "SELECT * FROM monsterFactory";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("NAME");
                int health = rs.getInt("HEALTH");
                int minDamage = rs.getInt("MIN_DAMAGE");
                int maxDamage = rs.getInt("MAX_DAMAGE");
                double attackOdds = rs.getDouble("ATTACK_ODDS");
                double blockOdds = rs.getDouble("BLOCK_ODDS");
                int attackSpeed = rs.getInt("ATTACK_SPEED");

                Monster monster = new Monster(name, health, minDamage, maxDamage, attackOdds, blockOdds, attackSpeed);
                monsters.add(monster);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        if(monsters.isEmpty()) {
            throw new IllegalArgumentException(("MonsterFactory is empty."));
        }

        int row = MY_RANDOM.nextInt(monsters.size());
        return monsters.get(row);
    }

}
