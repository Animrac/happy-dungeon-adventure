package src.UnitTest;
import src.Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

    /*

    Room 2d Array Key:
    M - Multiple Items
    X - Pit
    i - Entrance (In)
    O - Exit (Out)
    V - Vision Potion
    H - Healing Potion ▪ <space> - Empty Room
    A, E, I, P - Pillars

     */

public class RoomTest {

    @Test
    void testRoomCreation() {

        char[][] Room1 = {
                {'/', '/', '/', },
                {'/', 'V', '/', },
                {'/', '/', '/', }
        };

        Room testRoom1 = new Room(Room1, 1, 1, 'V');

        assertEquals(true,testRoom1.getHasVisionPotion());



    }

}
