//package src.ModelTests;
//
//import org.junit.jupiter.api.Test;
//import src.Model.Adventurer;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AdventurerTest {
//    private static class TestCharacter extends Adventurer {
//
//        /**
//         * Constructor for Test Character.
//         *
//         * @param theName        the name of the character
//         * @param theHealth      the hit points of the character
//         * @param theMinDamage   the min damage a character can create
//         * @param theMaxDamage   the max damage a character can create
//         * @param theAttackOdds  the odds of a character's attack succeeding
//         * @param theBlockOdds   the odds of a character being able to block an attack
//         * @param theAttackSpeed the # of attack generated per round
//         */
//        protected TestCharacter(String theName, int theHealth, int theMinDamage, int theMaxDamage, double theAttackOdds, double theBlockOdds, int theAttackSpeed) {
//            super(theName, theHealth, theMinDamage, theMaxDamage, theAttackOdds, theBlockOdds, theAttackSpeed);
//        }
//
//        //@Override
//        protected static int generateRangedValue(int min, int max) {
//            return Adventurer.generateRangedValue(min, max);
//        }
//    }
//
//    @Test
//    void generateRangedValue() {
//        int val = TestCharacter.generateRangedValue(1,10);
//        assertTrue(val >= 1 && val <= 10);
//    }
//
//    @Test
//    void setName() {
//        TestCharacter.setName("Test");
//        assertEquals("Test", TestCharacter.getName());
//    }
//
//    @Test
//    void setHealth() {
//        TestCharacter.setHealth(100);
//        assertEquals(100, TestCharacter.getHealth());
//    }
//
//    @Test
//    void setMinDamage() {
//        TestCharacter.setMinDamage(10);
//        assertEquals(10, TestCharacter.getMinDamage());
//    }
//
//    @Test
//    void setMaxDamage() {
//        TestCharacter.setMaxDamage(20);
//        assertEquals(20, TestCharacter.getMaxDamage());
//    }
//
//    @Test
//    void checkMinVersusMax() {
//        TestCharacter.setMinDamage(20);
//        //assertThrows(IllegalArgumentException.class, () -> TestCharacter.setMaxDamage(10));
//    }
//
//    @Test
//    void setAttackOdds() {
//        TestCharacter.setAttackOdds(0.8);
//        assertEquals(0.8, TestCharacter.getAttackOdds());
//    }
//
//    @Test
//    void setBlockOdds() {
//        TestCharacter.setBlockOdds(0.5);
//        assertEquals(0.5, TestCharacter.getBlockOdds());
//    }
//
//    @Test
//    void setAttackSpeed() {
//        TestCharacter.setAttackSpeed(2);
//        assertEquals(2, TestCharacter.getAttackSpeed());
//    }
//
//    @Test
//    void subtractHitPoints() {
//        TestCharacter.subtractHitPoints(20);
//        assertEquals(80, TestCharacter.getHealth());
//    }
//
//    @Test
//    void getName() {
//    }
//
//    @Test
//    void getHealth() {
//    }
//
//    @Test
//    void getMaxDamage() {
//    }
//
//    @Test
//    void getMinDamage() {
//    }
//
//    @Test
//    void getAttackSpeed() {
//    }
//
//    @Test
//    void getAttackOdds() {
//    }
//
//    @Test
//    void getBlockOdds() {
//    }
//
//    @Test
//    void alive() {
//        TestCharacter.setHealth(50);
//        assertTrue(TestCharacter.alive());
//    }
//
//    @Test
//    void attack() {
//    }
//}