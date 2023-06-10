package src.UnitTest;
import src.Model.HeroOne;
import src.Model.HeroTwo;
import src.Model.HeroThree;

public class HeroTestHelper {

    static class TestableHeroOne extends HeroOne {
        public TestableHeroOne(String name) {
            super(name);
        }

        /** Expose protected method for testing*/
        @Override
        public void subtractHitPoints(final int theAmount) {
            super.subtractHitPoints(theAmount);
        }

        /** Expose protected method setRunAway for testing*/
        public void setTestRunAway(final boolean theRunAway) {
            setRunAway(theRunAway);
        }
    }

    static class TestableHeroTwo extends HeroTwo {
        public TestableHeroTwo(String name) {
            super(name);
        }

        /** Expose protected method for testing*/
        @Override
        public void subtractHitPoints(final int theAmount) {
            super.subtractHitPoints(theAmount);
        }
    }

    static class TestableHeroThree extends HeroThree {
        public TestableHeroThree(String name) {
            super(name);
        }

        /** Expose protected method for testing*/
        @Override
        public void subtractHitPoints(final int theAmount) {
            super.subtractHitPoints(theAmount);
        }
    }

}

