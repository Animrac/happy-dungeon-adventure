//package src.Model;
//
//package Extra;/*
// * Monster.java
// *
// * TCSS 143 - Spring 2021
// * Assignment 5
// */
//
///**
// * This program creates an outline for a monster character that differs from
// * a DungeonCharacter as it has the ability to heal if attacked.
// *
// * Some code from class.
// *
// * @author anastasiavilenius
// * @version 06/08/21
// */
//public abstract class Monster extends DungeonCharacter {
//
//    /**
//     * Indicates the odds of a monster healing after an attack.
//     */
//    private double myHealOdds;
//
//    /**
//     * Indicates the minimum number of hit points a monster can heal.
//     */
//    private int myMinHeal;
//
//    /**
//     * Indicates the maximum number of hit points a monster can heal.
//     */
//    private int myMaxHeal;
//
//    /**
//     * Indicates the original number of hit points of a monster.
//     */
//    private int myOriginalHitPoints;
//
//    /**
//     * Constructor that initializes fields.
//     *
//     * @param theName
//     * @param theHitPoints
//     * @param theMinDamage
//     * @param theMaxDamage
//     * @param theAttackOdds
//     * @param theAttackSpeed
//     * @param theHealOdds
//     * @param theMinHeal
//     * @param theMaxHeal
//     */
//    protected Monster(String theName, int theHitPoints, int theMinDamage, int theMaxDamage,
//                      double theAttackOdds, int theAttackSpeed, double theHealOdds, int theMinHeal, int theMaxHeal) {
//        super(theName, theHitPoints, theMinDamage, theMaxDamage, theAttackOdds, theAttackSpeed);
//        setHealOdds(theHealOdds);
//        setMinHeal(theMinHeal);
//        setMaxHeal(theMaxHeal);
//        setOriginalHitPoints(theHitPoints);
//    }
//
//    /**
//     * Checks to make sure the odds of a monster healing is between 0 and 100.
//     *
//     * @param theHealOdds
//     */
//    private final void setHealOdds(final double theHealOdds) {
//        if(theHealOdds < 0 || theHealOdds > 100) {
//            throw new IllegalArgumentException("value passed to setHealOdds not between 0 and 100");
//        }
//        myHealOdds = theHealOdds;
//    }
//
//    /**
//     * Returns true (monster can heal) if randomly generated number is less than or equal
//     * to the odds of healing.
//     *
//     * @return whether or not monster can heal
//     */
//    private boolean canHeal() {
//        return MY_RANDOM.nextInt(101) <= myHealOdds;
//    }
//
//    /**
//     * Checks to make sure the minimum amount of hit points that can be healed is above 0.
//     *
//     * @param theMinHeal
//     */
//    protected final void setMinHeal(final int theMinHeal) {
//        if (theMinHeal < 0) {
//            throw new IllegalArgumentException("min heal must be greater than 0");
//        }
//        myMinHeal = theMinHeal;
//        if (myMinHeal > 0) {
//        }
//    }
//
//    /**
//     * Checks to make sure the maximum amount of hit points that can be healed is above 0.
//     *
//     * @param theMaxHeal
//     */
//    protected final void setMaxHeal(final int theMaxHeal) {
//        if (theMaxHeal < 0) {
//            throw new IllegalArgumentException("max heal must be greater than 0");
//        }
//        myMaxHeal = theMaxHeal;
//        if (myMaxHeal > 0) {
//        }
//    }
//
//    /**
//     * Checks to make sure the original hit points is greater than 0.
//     *
//     * @param theHitPoints
//     */
//    private void setOriginalHitPoints(final int theHitPoints) {
//        if (theHitPoints < 0) {
//            throw new IllegalArgumentException(" original hit points must be greater than 0");
//        }
//        myOriginalHitPoints = theHitPoints;
//    }
//
//    /**
//     * Generates the number of points a monster can heal after an attack.
//     *
//     * @return healPoints
//     */
//    private int generateHealPoints() {
//        int healPoints = generateRangedValue(myMinHeal, myMaxHeal);
//        if (healPoints + getHitPoints() > myOriginalHitPoints) {
//            healPoints = myOriginalHitPoints - getHitPoints();
//        }
//        return healPoints;
//    }
//
//    /**
//     * Overrides subtractHitPoints method in DungeonClass and allows monster
//     * a chance to heal after hit points have been lost, as long as monster has not fainted.
//     *
//     * @param theHitPoints
//     */
//    @Override
//    protected void subtractHitPoints(final int theHitPoints) {
//        super.subtractHitPoints(theHitPoints);
//        if (alive() && canHeal()) {
//            myOriginalHitPoints += generateHealPoints();
//            System.out.println(getName() + " healed from the attack!");
//        }
//        else {
//            super.subtractHitPoints(theHitPoints);
//        }
//    }
//
//}
//
