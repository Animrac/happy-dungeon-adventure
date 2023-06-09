package src.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Model.Monster;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {

    private TestMonster monster;

    @BeforeEach
    public void setUp() {
        // Initialize a test monster with predefined attributes
        monster = new TestMonster("TestMonster", 100, 20, 40, 0.6, 0.3, 2);
    }

    // Test if the Monster constructor correctly sets all properties
    @Test
    public void testMonsterConstructor() {
        // Check if all properties are set correctly during construction
        assertEquals("TestMonster", monster.getName());
        assertEquals(100, monster.getHealth());
        assertEquals(20, monster.getMinDamage());
        assertEquals(40, monster.getMaxDamage());
        assertEquals(0.6, monster.getAttackOdds());
        assertEquals(0.3, monster.getBlockOdds());
        assertEquals(2, monster.getAttackSpeed());
    }


    // Subclass of Monster that we can instantiate in this package (So it won't interfere the encapsulation of Monster class)
    class TestMonster extends Monster {
        public TestMonster(String theName, int theHealth, int theMinDamage, int theMaxDamage,
                           double theAttackOdds, double theBlockOdds, int theAttackSpeed) {
            super(theName, theHealth, theMinDamage, theMaxDamage, theAttackOdds, theBlockOdds, theAttackSpeed);
        }
    }
}


