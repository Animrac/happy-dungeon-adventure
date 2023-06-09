package src.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    HeroTestHelper.TestableHeroOne heroOne;
    HeroTestHelper.TestableHeroTwo heroTwo;
    HeroTestHelper.TestableHeroThree heroThree;

    @BeforeEach
    public void setUp() {
        heroOne = new HeroTestHelper.TestableHeroOne("HeroOneTest");
        heroTwo = new HeroTestHelper.TestableHeroTwo("HeroTwoTest");
        heroThree = new HeroTestHelper.TestableHeroThree("HeroThreeTest");
    }

    // Test that the runAway method's result changes after setting testRunAway to true
    @Test
    public void testRunAway() {

        assertEquals(false,heroOne.runAway());

        // After setting testRunAway to true, runAway should return true
        heroOne.setTestRunAway(true);
        assertEquals(true,heroOne.runAway());
    }

    // Test that constructors correctly assign names to different hero types
    @Test
    public void testValidConstructor() {
        // Make sure names are correctly assigned during object creation
        assertEquals("HeroOneTest", heroOne.getName());
        assertEquals("HeroTwoTest", heroTwo.getName());
        assertEquals("HeroThreeTest", heroThree.getName());
    }

    // Make sure hit points are correctly subtracted from the health of different hero types
    @Test
    public void testSubtractHitPoints() {

        heroOne.subtractHitPoints(50);
        heroTwo.subtractHitPoints(50);
        heroThree.subtractHitPoints(50);

        // Make sure health is correctly updated after subtraction
        // This depends on the initial health of each Hero type
        assertEquals(100, heroOne.getHealth());
        assertEquals(50, heroTwo.getHealth());
        assertEquals(70, heroThree.getHealth());
    }

}

