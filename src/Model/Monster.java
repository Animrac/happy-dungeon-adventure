package src.Model;

/**
 * This program creates an outline for a monster character.
 *
 * @author anastasiavilenius
 * @version 05/07/23
 */
public class Monster extends Adventurer {

    /**
     * Constructor that initializes fields.
     *
     * @param theName the third monster type
     * @param theHealth the health of the monster
     * @param theMinDamage the minimum amount of damage a monster can create
     * @param theMaxDamage the maximum amount of damage a monster can create
     * @param theAttackOdds the odds of an attack succeeding
     * @param theBlockOdds the odds of a monster being able to block
     * @param theAttackSpeed the number of attacks that can be generated in one round
     */
    protected Monster(String theName, int theHealth, int theMinDamage, int theMaxDamage,
                      double theAttackOdds, double theBlockOdds, int theAttackSpeed) {
        super(theName, theHealth, theMinDamage, theMaxDamage, theAttackOdds, theBlockOdds, theAttackSpeed);
        setOriginalHitPoints(theHealth);
    }


    /**
     * Checks to make sure the original hit points is greater than 0.
     *
     * @param theHitPoints the hp a monster
     */
    private void setOriginalHitPoints(final int theHitPoints) {
        if (theHitPoints < 0) {
            throw new IllegalArgumentException(" original hit points must be greater than 0");
        }
    }
}

