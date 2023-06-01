package src.UnitTest;
import src.Model.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

        @Test
        void testAddVisionPotion() {
            inventory.addVisionPotion();
            int result = inventory.getVisionPotionCount();
            assertEquals(1, result);
        }

        @Test
        void testRemoveVisionPotion() {
            inventory.addVisionPotion();
            inventory.removeVisionPotion();
            int result = inventory.getVisionPotionCount();
            assertEquals(0, result);
        }

        @Test
        void testAddHealthPotion() {
            inventory.addHealthPotion();
            inventory.addHealthPotion();
            int result = inventory.getHealthPotionCount();
            assertEquals(2,result);
        }
        @Test
        void testRemoveHealthPotion() {
            inventory.addHealthPotion();
            inventory.addHealthPotion();
            inventory.addHealthPotion();
            inventory.addHealthPotion();
            inventory.removeHealthPotion();
            int result = inventory.getHealthPotionCount();
            assertEquals(3,result);
        }

        @Test
        void testAddPillar() {
        inventory.addPillar();
        inventory.addPillar();
        int result = inventory.getPillarCount();
        assertEquals(2,result);
        }

}
