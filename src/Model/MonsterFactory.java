package src.Model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.*;

/**
 * This program stores the types of monsters in a database
 *
 * @author Anastasia Vilenius
 * @version 05/28/23
 */
public class MonsterFactory {

    SQLiteDataSource ds = null;

    //private static final MonsterFactory mf = new MonsterFactory();
    private static java.sql.Connection connection;

    private MonsterFactory() {
        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:monsterFactory.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );


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
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created monsterFactory table successfully" );

        //next insert 3 rows of data
        System.out.println( "Attempting to insert 3 rows into monsterFactory table" );

        String query1 = "INSERT INTO monsterFactory ('Monster One', 80, 20, 40, .8, 3, 3)";
        String query2 = "INSERT INTO monsterFactory ('Monster Two', 80, 20, 40, .8, 3, 3)";
        String query3 = "INSERT INTO monsterFactory ('Monster Three', 80, 20, 40, .8, 3, 3)";


        try (Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement()) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query3 );
            System.out.println( "3rd executeUpdate() returned " + rv );

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }


        //now query the database table for all its contents and display the results
        int row = 0;
        String theName = "";

//        switch (theName.toUpperCase()) {
//            case ("MONSTER ONE") -> row = 1;
//            case ("MONSTER TWO") -> row = 2;
//            case ("MONSTER THREE") -> row = 3;
//
//            default -> {
//                throw new NoSuchElementException(theName + "is not a valid monster");
//            }
//
//        }
//        query = "SELECT * FROM monsterFactory WHERE row = " + row;
        query = "SELECT * FROM monsterFactory";

        try ( Connection conn = ds.getConnection();
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
        while (rs.next()) {
            String name = rs.getString( "NAME" );
            String health = rs.getString( "HEALTH" );
            String minDamage = rs.getString( "MIN_DAMAGE" );
            String maxDamage = rs.getString( "MAX_DAMAGE" );
            String attackOdds = rs.getString( "ATTACK_ODDS" );
            String blockOdds = rs.getString( "BLOCK_ODDS" );
            String attackSpeed = rs.getString( "ATTACK_SPEED" );

            System.out.println( "Result: Name = " + name +
                    ", Health = " + health + minDamage + maxDamage + attackOdds + blockOdds + attackSpeed);
        }

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        System.out.println("press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();

    }

}
