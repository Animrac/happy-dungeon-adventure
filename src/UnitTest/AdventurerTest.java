package src.UnitTest;
import src.Model.Adventurer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdventurerTest {
    private TestableAdventurer adventurer;
    private TestableAdventurer opponent;

    private class TestableAdventurer extends Adventurer {

        public TestableAdventurer(String theName, int theHealth, int theMinDamage, int theMaxDamage,
                                  double theAttackOdds, double theBlockOdds, int theAttackSpeed) {
            super(theName, theHealth, theMinDamage, theMaxDamage, theAttackOdds, theBlockOdds, theAttackSpeed);
        }
    }
    @BeforeEach
    public void setUp() {
        adventurer = new TestableAdventurer("Hero", 150, 10, 40, 50.0, 50.0, 2);
        opponent = new TestableAdventurer("Monster", 80, 10, 20, 50.0, 50.0, 2);
    }

    /** Test the adventurer's health is correctly updated*/
    @Test
    public void testSetHealth() {
        int health = 80;
        adventurer.setHealth(health);
        assertEquals(health, adventurer.getHealth());
    }

    /**Test hit points are correctly subtracted from the adventurer's health*/
    @Test
    public void testSubtractHitPoints() {
        int initialHealth = adventurer.getHealth();
        int damage = 20;
        adventurer.subtractHitPoints(damage);
        assertEquals(initialHealth - damage, adventurer.getHealth());
    }

    /** Test the adventurer's minimum damage value is correctly updated*/
    @Test
    public void testSetMinDamage() {
        int minDamage = 15;
        adventurer.setMinDamage(minDamage);
        assertEquals(minDamage, adventurer.getMinDamage());
    }

    /** Test that the adventurer's maximum damage value is correctly updated*/
    @Test
    public void testSetMaxDamage() {
        int maxDamage = 30;
        adventurer.setMaxDamage(maxDamage);
        assertEquals(maxDamage, adventurer.getMaxDamage());
    }

    /** Test the adventurer's attack odds value is correctly updated*/
    @Test
    public void testSetAttackOdds() {
        double attackOdds = 70.0;
        adventurer.setAttackOdds(attackOdds);
        assertEquals(attackOdds, adventurer.getAttackOdds());
    }

    /**Test the adventurer's block odds value is correctly updated*/
    @Test
    public void testSetBlockOdds() {
        double blockOdds = 40.0;
        adventurer.setBlockOdds(blockOdds);
        assertEquals(blockOdds, adventurer.getBlockOdds());
    }

    /** Test the adventurer's attack speed is correctly updated*/
    @Test
    public void testSetAttackSpeed() {
        int attackSpeed = 3;
        adventurer.setAttackSpeed(attackSpeed);
        assertEquals(attackSpeed, adventurer.getAttackSpeed());
    }

    /** Test the alive method correctly identifies if the adventurer is alive or not*/
    @Test
    public void testAlive() {
        assertEquals(true,adventurer.alive());
        adventurer.setHealth(0);
        assertEquals(false,adventurer.alive());
    }

    /** Test damage generation falls within the defined minimum and maximum damage range*/
    @Test
    public void testGenerateDamage() {
        for (int i = 0; i < 1000; i++) {
            int damage = adventurer.generateDamage();
            assertEquals(true,damage >= adventurer.getMinDamage() && damage <= adventurer.getMaxDamage());
        }
    }

    /** Test an attack from the adventurer correctly reduces the opponent's health*/
    @Test
    public void testAttack() {
        int initialOpponentHealth = opponent.getHealth();
        adventurer.attack(opponent);
        assertEquals(true,opponent.getHealth() <= initialOpponentHealth);
    }

    /** Test a fainted (zero health) adventurer can't inflict damage on the opponent*/
    @Test
    public void testAttackFaintedAdventurer() {
        adventurer.setHealth(0);
        int initialOpponentHealth = opponent.getHealth();
        adventurer.attack(opponent);
        assertEquals(initialOpponentHealth, opponent.getHealth());
    }
}

