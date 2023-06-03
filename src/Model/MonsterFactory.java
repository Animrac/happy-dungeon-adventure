package src.Model;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * This program stores the types of monsters in a database
 *
 * @author Anastasia Vilenius, Carmina Cruz
 * @version 06/02/23
 */
public class MonsterFactory implements Serializable {
    private static java.sql.Connection connection;
    public MonsterFactory() {

//        String filePath = "src/monsterFactory.db"; // Replace with the actual file path
//
//        File file = new File(filePath);
//
//        if (file.exists()) {
////            System.out.println("File exists.");
//        }
//        else {
//            System.out.println("File does not exist. Creating database now.");

            SQLiteDataSource ds = null;

            //establish connection (creates db file if it does not exist :-)
            try {
                ds = new SQLiteDataSource();
                ds.setUrl("jdbc:sqlite:monsterFactory.db");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

//            System.out.println("Opened database successfully");


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
//                System.out.println("executeUpdate() returned " + rv);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
//            System.out.println("Created monsterFactory table successfully");

            //next insert 3 rows of data
//            System.out.println("Attempting to insert 3 rows into monsterFactory table");

            String query1 = "INSERT INTO monsterFactory VALUES ('Tom Nook #1 (Ogre)', 200, 30, 60, .6, 3, 2)";
            String query2 = "INSERT INTO monsterFactory VALUES ('Tom Nook #2 (Gremlin)', 70, 15, 30, .8, 3, 5)";
            String query3 = "INSERT INTO monsterFactory VALUES ('Tom Nook #3 (Skeleton)', 80, 30, 50, .8, 3, 3)";


            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();) {
                int rv = stmt.executeUpdate(query1);
//                System.out.println("1st executeUpdate() returned " + rv);

                rv = stmt.executeUpdate(query2);
//                System.out.println("2nd executeUpdate() returned " + rv);

                rv = stmt.executeUpdate(query3);
//                System.out.println("3rd executeUpdate() returned " + rv);

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }


            //now query the database table for all its contents and display the results
            int row = 0;
            String theName = "";
            query = "SELECT * FROM monsterFactory";

            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                new Monster(
                        rs.getString("NAME"),
                        rs.getInt("HEALTH"),
                        rs.getInt("MIN_DAMAGE"),
                        rs.getInt("MAX_DAMAGE"),
                        rs.getDouble("ATTACK_ODDS"),
                        rs.getDouble("BLOCK_ODDS"),
                        rs.getInt("ATTACK_SPEED")
                );

                //walk through each 'row' of results, grab data by column/field name
                // and print it
                while ( rs.next() ) {
                    String name = rs.getString( "NAME" );
                    String health = rs.getString( "HEALTH" );
                    String minDamage = rs.getString( "MIN_DAMAGE" );
                    String maxDamage = rs.getString( "MAX_DAMAGE" );
                    String attackOdds = rs.getString( "ATTACK_ODDS" );
                    String blockOdds = rs.getString( "BLOCK_ODDS" );
                    String attackSpeed = rs.getString( "ATTACK_SPEED" );

//                    System.out.println( "Result: Name = " + name +
//                            ", Health = " + health + minDamage + maxDamage + attackOdds + blockOdds + attackSpeed);
                }


            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
//    }


}

