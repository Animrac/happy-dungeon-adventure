package src.UnitTest;
import src.Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    /**
    Dungeon 2d Array Key:
    X - Not a room
    S - Dungeon Entrance
    E - Dungeon Exit
    O - Empty room
    V - Room with Vision Potion
    H - Room with Healing Potion
    P - Room with pillar
    M - Room with Multiple Items
    */

/** Some issue need to fixed, like the monster, multiple items*/
class RoomTest {

    char[][] dungeonLayout;
    Room roomEntry;
    Room roomExit;
    Room roomWithItem;
    Room roomEmpty;

    @BeforeEach
    void setUp() {
        /** This is the dungeon layout which included all the rooms in the dungeon*/
        dungeonLayout = new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'S', 'O', 'O', 'X'},
                {'H', 'O', 'O', 'O', 'V'},
                {'X', 'O', 'E', 'P', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        };

        /** Initialize room at position (1,1) with key 'S'(Entry Room) (2D array starts on row 0 and col 0)*/
        roomEntry = new Room(dungeonLayout, 1, 1, 'S');
        /** Initialize room at position (3,2) with key 'E'(Exit Room)*/
        roomExit = new Room(dungeonLayout,3,2,'E');
        /** Initialize room at position (3,3) with key 'P'(Room with item)*/
        roomWithItem = new Room(dungeonLayout,3,3,'P');
        /** Initialize room at position (1,2) with key 'O'(Empty room)*/
        roomEmpty = new Room(dungeonLayout,1,2,'O');
    }

    /** Check What EntryRoom contains and the surroundings (North,South,West,East)*/
    @Test
    void testEntryRoom() {
        System.out.println("Checking EntryRoom north...");
        assertEquals(false,roomEntry.getCanGoNorth());

        System.out.println("Checking  EntryRoom south...");
        assertEquals(true,roomEntry.getCanGoSouth());

        System.out.println("Checking  EntryRoom west...");
        assertEquals(false,roomEntry.getCanGoWest());

        System.out.println("Checking EntryRoom east...");
        assertEquals(true,roomEntry.getCanGoEast());

        System.out.println("Checking EntryRoom healing potion...");
        assertEquals(false,roomEntry.getHasHealingPotion());

        System.out.println("Checking EntryRoom vision potion...");
        assertEquals(false,roomEntry.getHasVisionPotion());

        System.out.println("Checking EntryRoom pillar...");
        assertEquals(false,roomEntry.getHasPillar());

        System.out.println("Checking EntryRoom pit...");
        assertEquals(false,roomEntry.getHasPit());

        System.out.println("Checking EntryRoom monster...");
        assertEquals(false,roomEntry.getHasMonster());
    }

    /** Check What ExitRoom contains and the surroundings (North,South,West,East)*/
    @Test
    void testExitRoom() {
        System.out.println("Checking ExitRoom north...");
        assertEquals(true,roomExit.getCanGoNorth());

        System.out.println("Checking ExitRoom south...");
        assertEquals(false,roomExit.getCanGoSouth());

        System.out.println("Checking ExitRoom west...");
        assertEquals(true,roomExit.getCanGoWest());

        System.out.println("Checking ExitRoom east...");
        assertEquals(true,roomExit.getCanGoEast());

        System.out.println("Checking ExitRoom healing potion...");
        assertEquals(false,roomExit.getHasHealingPotion());

        System.out.println("Checking ExitRoom vision potion...");
        assertEquals(false,roomExit.getHasVisionPotion());

        System.out.println("Checking ExitRoom pillar...");
        assertEquals(false,roomExit.getHasPillar());

        System.out.println("Checking ExitRoom pit...");
        assertEquals(false,roomExit.getHasPit());

        System.out.println("Checking ExitRoom monster...");
        assertEquals(false,roomExit.getHasMonster());
    }

    /** Test if the RoomWithItem has been initialized correctly*/
    @Test
    void testRoomWithItem () {
        System.out.println("Checking roomWithItem north...");
        assertEquals(true,roomWithItem.getCanGoNorth());

        System.out.println("Checking roomWithItem south...");
        assertEquals(false,roomWithItem.getCanGoSouth());

        System.out.println("Checking roomWithItem west...");
        assertEquals(true,roomWithItem.getCanGoWest());

        System.out.println("Checking roomWithItem east...");
        assertEquals(false,roomWithItem.getCanGoEast());

        System.out.println("Checking roomWithItem healing potion...");
        assertEquals(false,roomWithItem.getHasHealingPotion());

        System.out.println("Checking roomWithItem vision potion...");
        assertEquals(false,roomWithItem.getHasVisionPotion());

        System.out.println("Checking roomWithItem pillar...");
        assertEquals(true,roomWithItem.getHasPillar());

        System.out.println("Checking roomWithItem pit...");
        assertEquals(false,roomWithItem.getHasPit());

        /** When the room has the pillar, there is default setting with a monster in it*/
        System.out.println("Checking roomWithItem monster...");
        assertEquals(true,roomWithItem.getHasMonster());
    }

    /** Test if the EmptyRoom has been initialized correctly*/
    @Test
    void testEmptyRoom() {
        System.out.println("Checking roomEmpty north...");
        assertEquals(false,roomEmpty.getCanGoNorth());

        System.out.println("Checking roomEmpty south...");
        assertEquals(true,roomEmpty.getCanGoSouth());

        System.out.println("Checking roomEmpty west...");
        assertEquals(true,roomEmpty.getCanGoWest());

        System.out.println("Checking roomEmpty east...");
        assertEquals(true,roomEmpty.getCanGoEast());

        System.out.println("Checking roomEmpty healing potion...");
        assertEquals(false,roomEmpty.getHasHealingPotion());

        System.out.println("Checking roomEmpty vision potion...");
        assertEquals(false,roomEmpty.getHasVisionPotion());

        System.out.println("Checking roomEmpty pillar...");
        assertEquals(false,roomEmpty.getHasPillar());

        System.out.println("Checking roomEmpty pit...");
        assertEquals(false,roomEmpty.getHasPit());

        System.out.println("Checking roomEmpty monster...");
        assertEquals(false,roomEmpty.getHasMonster());
    }

}
