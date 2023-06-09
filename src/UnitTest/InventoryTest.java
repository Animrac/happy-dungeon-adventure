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

    // Test that adding a vision potion increments the count correctly
    @Test
    void testAddVisionPotion() {
        inventory.addVisionPotion();
        int result = inventory.getVisionPotionCount();
        // After adding one potion, the count should be 1
        assertEquals(1, result);
    }

    // Test that removing a vision potion decrements the count correctly
    @Test
    void testRemoveVisionPotion() {
        inventory.addVisionPotion();
        inventory.removeVisionPotion();
        int result = inventory.getVisionPotionCount();
        // After adding and removing one potion, the count should be 0
        assertEquals(0, result);
    }

    // Test that adding health potions increments the count correctly
    @Test
    void testAddHealthPotion() {
        inventory.addHealthPotion();
        inventory.addHealthPotion();
        int result = inventory.getHealthPotionCount();
        // After adding two potions, the count should be 2
        assertEquals(2,result);
    }

    // Test that removing health potions decrements the count correctly
    @Test
    void testRemoveHealthPotion() {
        inventory.addHealthPotion();
        inventory.addHealthPotion();
        inventory.addHealthPotion();
        inventory.addHealthPotion();
        inventory.removeHealthPotion();
        int result = inventory.getHealthPotionCount();
        // After adding four potions and removing one, the count should be 3
        assertEquals(3,result);
    }

    // Test that adding pillars increments the count correctly
    @Test
    void testAddPillar() {
        inventory.addPillar();
        inventory.addPillar();
        int result = inventory.getPillarCount();
        // After adding two pillars, the count should be 2
        assertEquals(2,result);
    }

}
