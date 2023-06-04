package src.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    private HeroTestHelper.TestableHeroOne heroOne;
    private HeroTestHelper.TestableHeroTwo heroTwo;
    private HeroTestHelper.TestableHeroThree heroThree;

    @BeforeEach
    public void setUp() {
        heroOne = new HeroTestHelper.TestableHeroOne("HeroOneTest");
        heroTwo = new HeroTestHelper.TestableHeroTwo("HeroTwoTest");
        heroThree = new HeroTestHelper.TestableHeroThree("HeroThreeTest");
    }

    @Test
    public void testValidConstructor() {
        assertEquals("HeroOneTest", heroOne.getName());
        assertEquals("HeroTwoTest", heroTwo.getName());
        assertEquals("HeroThreeTest", heroThree.getName());
    }

    @Test
    public void testSubtractHitPoints() {
        heroOne.subtractHitPoints(50);
        heroTwo.subtractHitPoints(50);
        heroThree.subtractHitPoints(50);

        // This depends on the initial health of each Hero type
        assertEquals(100, heroOne.getHealth());
        assertEquals(50, heroTwo.getHealth());
        assertEquals(70, heroThree.getHealth());
    }

}

