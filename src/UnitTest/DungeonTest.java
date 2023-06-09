package src.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import src.Model.Dungeon;
import src.Model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DungeonTest {
    private Dungeon dungeon1;
    private Dungeon dungeon2;

    // Set up method to initialize the dungeons before each test
    @BeforeEach
    public void setUp() {
        dungeon1 = new Dungeon();
        dungeon2 = new Dungeon(10,10);
    }

    // Test the functionality of the Dungeon constructor with custom size
    @Test
    public void testCustomConstructor() {
        assertEquals(dungeon2.getDungeonLayout().length, 10 + 2);
        assertEquals(dungeon2.getDungeonLayout()[0].length, 10 + 2);
        // Make sure that the generated dungeon is traversable
        assertEquals(true,dungeon2.isItTraversable());
    }

    // Test the generation of rooms in the dungeon
    @Test
    public void testRoomGeneration() {
        // Make sure that each cell in the dungeon has a room
        for (int i = 0; i < dungeon2.getDungeonLayout().length; i++) {
            for (int j = 0; j < dungeon2.getDungeonLayout()[0].length; j++) {
                assertNotNull(dungeon2.getMyDungeonRooms()[i][j]);
            }
        }
    }

    // Test the location of start, end, and pillars in the dungeon
    @Test
    public void testStartEndPillarLocation() {
        int entryCount = 0;
        int exitCount = 0;
        int pillarCount = 0;
        // Loop through each room to count the occurrences.
        for (int i = 0; i < dungeon1.getDungeonLayout().length; i++) {
            for (int j = 0; j < dungeon1.getDungeonLayout()[0].length; j++) {
                char cell = dungeon1.getDungeonLayout()[i][j];
                if (cell == 'S') {
                    entryCount++;
                } else if (cell == 'E') {
                    exitCount++;
                } else if (cell == 'P') {
                    pillarCount++;
                }
            }
        }
        // Make sure there is only one start, one end, and four pillars in the dungeon
        assertEquals(entryCount, 1);
        assertEquals(exitCount, 1);
        assertEquals(pillarCount, 4);
    }

    // Test the isTraversable() method of Dungeon
    @Test
    public void testIsTraversable() {
        assertEquals(true,dungeon1.isItTraversable());
    }

    // Test the addRooms() method of Dungeon
    @Test
    public void testAddRooms() {
        // Adds rooms to the dungeon
        dungeon1.addRooms();
        Room[][] rooms = dungeon1.getMyDungeonRooms();
        // Make sure that each cell in the dungeon contains a room
        for (Room[] row : rooms) {
            for (Room room : row) {
                assertNotNull(room);
            }
        }
    }
}
