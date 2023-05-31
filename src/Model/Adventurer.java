package src.Model;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 * This program creates an outline for a character with certain characteristics
 * for a dungeon adventure game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 05/07/23
 */

public abstract class Adventurer implements Serializable {

    /**
     * Random object for generation of items/monsters.
     */
    final static Random MY_RANDOM = new Random();

    /**
     * Scanner for user input.
     */
    static final Scanner input = new Scanner(System.in);

    /**
     * Indicates the name of the hero or monster.
     */
    private String myName;

    /**
     * Indicates the number of hit points a character has.
     */
    private int myHealth;

    /**
     * Indicates the minimum amount of damage a character can cause in an attack.
     */
    private int myMinDamage;

    /**
     * Indicates the maximum amount of damage a character can cause in an attack.
     */
    private int myMaxDamage;

    /**
     * Indicates the odds of a characters attack succeeding.
     */
    private double myAttackOdds;

    /**
     * Indicates the odds of a character blocking an attack.
     */
    protected double myBlockOdds;


    /**
     * Indicates the number of attacks a character has per round.
     */
    private int myAttackSpeed;

    /**
     * Generates a random integer within a range of numbers
     * @param theLow the lowest end of the range
     * @param theHigh the highest end of the range
     * @return a random integer within a range of numbers
     */
     protected static int generateRangedValue(final int theLow, final int theHigh) {
        return theLow + MY_RANDOM.nextInt(theHigh - theLow + 1);
    }

    /**
     * Constructor for Dungeon Character.
     *
     * @param theName the name of the character
     * @param theHealth the hit points of the character
     * @param theMinDamage the min damage a character can create
     * @param theMaxDamage the max damage a character can create
     * @param theAttackOdds the odds of a character's attack succeeding
     * @param theBlockOdds the odds of a character being able to block an attack
     * @param theAttackSpeed the # of attack generated per round
     */
     protected Adventurer (final String theName, final int theHealth, final int theMinDamage,
                final int theMaxDamage, final double theAttackOdds, final double theBlockOdds, final int theAttackSpeed) {

        setName(theName);
        setHealth(theHealth);
        setMinDamage(theMinDamage);
        setMaxDamage(theMaxDamage);
        setAttackOdds(theAttackOdds);
        setBlockOdds(theBlockOdds);
        setAttackSpeed(theAttackSpeed);
    }

    /**
     * Checks to make sure name of character is not empty or null.
     *
     * @param theName the name of the character
     */
    protected final void setName(final String theName) {
        if (theName == null || theName.length() == 0) {
            throw new IllegalArgumentException("name passed to setName was null or empty");
        }
        myName = theName;
    }

    /**
     * Checks to make sure hit points are not negative.
     *
     * @param theHealth the number of hit points a character has
     */
    public final void setHealth(final int theHealth) {
        if (theHealth < 0) {
            throw new IllegalArgumentException("hit points passed to setHitPoints are negative");
        }
        myHealth = theHealth;
    }

    /**
     * Checks to make sure the minimum amount of damage that
     * can be caused in an attack is greater than 0.
     *
     * @param theMinDamage the minimum amount of damage a character can generate in an attack
     */
    public final void setMinDamage(final int theMinDamage) {
        if (theMinDamage <= 0) {
            throw new IllegalArgumentException("min damage must be greater than 0");
        }
        myMinDamage = theMinDamage;
        if (myMaxDamage > 0) {
            checkMinVersusMax();
        }
    }

    /**
     * Checks to make sure the maximum amount of damage that
     * can be caused in an attack is greater than 0.
     *
     * @param theMaxDamage the max amount of damage a character can generate in an attack
     */
    public final void setMaxDamage(final int theMaxDamage) {
        if (theMaxDamage <= 0) {
            throw new IllegalArgumentException("max damage must be greater than 0");
        }
        myMaxDamage = theMaxDamage;
        checkMinVersusMax();
    }

    /**
     * Checks to make sure the max amount of damage is greater than
     * the minimum amount of damage.
     */
    public void checkMinVersusMax() {
        if (myMinDamage > myMaxDamage) {
            throw new IllegalArgumentException("min damage cannot be greater than max damage");
        }
    }

    /**
     * Checks to make sure that the odds of attacking are greater than or equal to 0
     * and less than or equal to 100.
     *
     * @param theAttackOdds the odds of an attack succeeding
     */
    public final void setAttackOdds(final double theAttackOdds) {
        if (theAttackOdds <= 0 || theAttackOdds >= 100) {
            throw new IllegalArgumentException("chance to hit must be greater than 0 and less than 100");
        }
        myAttackOdds = theAttackOdds;
    }

    /**
     * Checks to make sure that the odds of blocking are greater than or equal to 0
     * and less than or equal to 100.
     *
     * @param theBlockOdds the odds of an attack succeeding
     */
    public final void setBlockOdds(final double theBlockOdds) {
        if (theBlockOdds <= 0 || theBlockOdds >= 100) {
            throw new IllegalArgumentException("chance to block must be greater than 0 and less than 100");
        }
        myBlockOdds = theBlockOdds;
    }


    /**
     * Checks to make sure that the attack speed of a character is greater than 0.
     *
     * @param theAttackSpeed the # of attack that can be generated during a round
     */
    public final void setAttackSpeed(final int theAttackSpeed) {
        if (theAttackSpeed < 0) {
            throw new IllegalArgumentException("attack speed passed to setAttackSpeed is negative");
        }
        myAttackSpeed = theAttackSpeed;
    }

    /**
     * Checks to make sure that hit points is greater than 0.
     * Subtracts hit points from a character if an attack successfully landed on them.
     * A character faints once their hit points reach 0.
     *
     * @param theAmount the amount of damage to be subtracted from a character's health
     */
    public void subtractHitPoints(final int theAmount) {
        if(theAmount < 0) {
            throw new IllegalArgumentException("hit points passed to subtractHitPoints are negative");
        }
        System.out.println(getName() + " takes <<" + theAmount + ">> damage");

        myHealth -= theAmount;
        if (myHealth <= 0) {
            myHealth = 0;
            System.out.println(getName() + " has fainted");
        }
        System.out.println(getName() + " hit points now at <<" + myHealth + ">>");
    }

    /**
     *
     * @return myName
     */
    public final String getName() {
        return myName;
    }

    /**
     *
     * @return myHitPoints
     */
    public final int getHealth() {
        return myHealth;
    }

    /**
     *
     * @return myMaxDamage
     */
    public final int getMaxDamage() {
        return myMaxDamage;
    }

    /**
     *
     * @return myMinDamage
     */
    public final int getMinDamage() {
        return myMinDamage;
    }

    /**
     *
     * @return myAttackSpeed
     */
    public final int getAttackSpeed() {
        return myAttackSpeed;
    }

    /**
     *
     * @return myAttackOdds
     */
    public final double getAttackOdds() {
        return myAttackOdds;
    }

    /**
     *
     * @return myBlockOdds
     */
    public final double getBlockOdds() {
        return myBlockOdds;
    }

    /**
     * Checks if character is still alive.
     *
     * @return true if hit points are greater than 0
     */
    public final boolean alive() {
        return myHealth > 0;
    }

    /**
     * Generates random amount of damage in set range.
     *
     * @return amount of damage in a set range
     */
    private int generateDamage() {
        return Adventurer.generateRangedValue(myMinDamage, myMaxDamage);
    }

    /**
     * Checks to make sure there is an opponent to battle.
     * Sets up battle between hero and monster.
     *
     * @param opponent the opponent that the hero is fighting
     */
    public void attack(final Adventurer opponent) {
        if (opponent == null) {
            throw new IllegalArgumentException("opponent Adventurer passed to attack is null");
        }
        if (alive()) {
            double randDouble = MY_RANDOM.nextDouble();
            if (randDouble <= myAttackOdds) {
                int damage = generateDamage();
                System.out.println(getName() + " attacks " + opponent.getName());
                opponent.subtractHitPoints(damage);
            } else {
                System.out.println(getName() + "'s attack on " + opponent.getName() + " misses");
            }
        } else {
            System.out.println(getName() + " has fainted so cannot attack");
        }
    }
}
