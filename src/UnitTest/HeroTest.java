package src.UnitTest;
import src.Model.HeroOne;
import src.Model.HeroThree;
import src.Model.HeroTwo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeroTest {

    private HeroOne heroOne;
    private HeroTwo heroTwo;
    private HeroThree heroThree;

    @BeforeEach
    public void setUp() {
        heroOne = new HeroOne("HeroOneTest");
        heroTwo = new HeroTwo("HeroTwoTest");
        heroThree = new HeroThree("HeroThreeTest");
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
