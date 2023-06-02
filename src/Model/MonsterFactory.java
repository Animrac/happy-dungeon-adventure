package src.Model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.*;

/**
 * This program stores the types of monsters in a database
 *
 * @author Anastasia Vilenius
 * @version 05/31/23
 */
public class MonsterFactory {

    static SQLiteDataSource ds = null;
    private static final Random MY_RANDOM = new Random();

    /**
     *
     */
    private MonsterFactory() {
        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monsterFactory.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Opened database successfully");


        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS monsterFactory ( " +
                "NAME string," +
                "HEALTH int," +
                "MIN_DAMAGE int," +
                "MAX_DAMAGE int," +
                "ATTACK_ODDS double," +
                "BLOCK_ODDS double," +
                "ATTACK_SPEED int)";

        try (Connection connection = ds.getConnection();
             Statement stmt = connection.createStatement()) {
            int rv = stmt.executeUpdate(query);
            System.out.println("executeUpdate() returned " + rv);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Created monsterFactory table successfully");

        //next insert 3 rows of data
        System.out.println("Attempting to insert 3 rows into monsterFactory table");

        String query1 = "INSERT INTO monsterFactory ('Monster One', 80, 20, 40, .8, 3, 3)";
        String query2 = "INSERT INTO monsterFactory ('Monster Two', 80, 20, 40, .8, 3, 3)";
        String query3 = "INSERT INTO monsterFactory ('Monster Three', 80, 20, 40, .8, 3, 3)";


        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            int rv = stmt.executeUpdate(query1);
            System.out.println("1st executeUpdate() returned " + rv);

            rv = stmt.executeUpdate(query2);
            System.out.println("2nd executeUpdate() returned " + rv);

            rv = stmt.executeUpdate(query3);
            System.out.println("3rd executeUpdate() returned " + rv);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     *
     * @return random monster from database
     */
    public static Monster getRandomMonster() {
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
