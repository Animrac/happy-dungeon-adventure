package src.UnitTest;
import src.Model.Dungeon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DungeonTest {

    @Test
    public void testCustomConstructor() {
        Dungeon dungeon = new Dungeon(10, 10);
         /**
          * Check that the dungeon dimensions are as expected,
         *+2 is to add the boarder that originally added inside the dungeon layout
         * Which it won't be counted inside the dungeon?
         */
        assertEquals(dungeon.getDungeonLayout().length, 10 + 2);
        assertEquals(dungeon.getDungeonLayout()[0].length, 10 + 2);
        // Check that the dungeon is traversable
        assertEquals(true,dungeon.isItTraversable());
    }

    @Test
    public void testRoomGeneration() {
        Dungeon dungeon = new Dungeon(10, 10);
        // Check that each cell of the dungeon contains a room
        for (int i = 0; i < dungeon.getDungeonLayout().length; i++) {
            for (int j = 0; j < dungeon.getDungeonLayout()[0].length; j++) {
                assertNotNull(dungeon.getMyDungeonRooms()[i][j]);
            }
        }
    }

    @Test
    public void testStartEndPillarLocation() {
        Dungeon dungeon = new Dungeon();
        // Check that the dungeon contains a start location, an end location, and four pillars
        int entryCount = 0;
        int exitCount = 0;
        int pillarCount = 0;
        //Go through all the rooms in the dungeon, make sure there is only 1 entry and exit, and 4 pillars
        for (int i = 0; i < dungeon.getDungeonLayout().length; i++) {
            for (int j = 0; j < dungeon.getDungeonLayout()[0].length; j++) {
                char cell = dungeon.getDungeonLayout()[i][j];
                if (cell == 'S') {
                    entryCount++;
                } else if (cell == 'E') {
                    exitCount++;
                } else if (cell == 'P') {
                    pillarCount++;
                }
            }
        }
        assertEquals(entryCount, 1);
        assertEquals(exitCount, 1);
        assertEquals(pillarCount, 4);
    }
}