package src.Model;
/**
 * This program creates a hero character.
 *
 * Code from class.
 *
 * @author Anastasia Vilenius
 * @version 05/09/23
 */
public class heroTwo extends Hero {

    /**
     * Indicated minimum amount of hit points that can be healed.
     */
    private int myMinSpecialHeal;

    /**
     * Indicated maximum amount of hit points that can be healed.
     */
    private int myMaxSpecialHeal;

    /**
     * Constructor that initializes fields.
     *
     * @param theName the name chosen by the player
     */
    public heroTwo(final String theName) {
        super(theName, 100, 30, 60, .8, 3, 4, .6);
        setMinSpecialHeal(20);
        setMaxSpecialHeal(40);
    }

    /**
     * Checks to make sure that the maximum healing points is above 0.
     * @param theMaxSpecialHeal the maximum amount of HP that can be healed
     */
    private void setMaxSpecialHeal(final int theMaxSpecialHeal) {
        if (theMaxSpecialHeal <= 0) {
            throw new IllegalArgumentException("special skill max heal must be greater than 0");
        }
        myMaxSpecialHeal = theMaxSpecialHeal;
    }

    /**
     * Checks to make sure that the minimum healing points is above 0.
     * @param theMinSpecialHeal the minimum amount of HP that can be healed
     */
    private void setMinSpecialHeal(final int theMinSpecialHeal) {
        if (theMinSpecialHeal <= 0) {
            throw new IllegalArgumentException("special skill min damage must be greater than 0");
        }
        myMinSpecialHeal = theMinSpecialHeal;
    }

//    /**
//     * Checks to make sure the maximum healing points is greater than the minimum.
//     */
//    private void checkMinVersusMax() {
//        if(myMinSpecialHeal > myMaxSpecialHeal) {
//            throw new IllegalArgumentException("min special damage cannot be more than max damage");
//        }
//    }

    /**
     * Overrides specialAttack method in Adventurer class by allowing heroTwo a chance
     * to use a crushing blow special attack.
     *
     * @param theOpponent the monster the hero is battling
     */
    @Override
    protected void specialAttack(final Adventurer theOpponent) {
        System.out.print(getName() + " tries to use Crushing Blow");
        if(canUseSpecialSkill()) {
            int damage = generateHealingPower();

            System.out.println(" and hits for **" + damage + "** damage!!");
        }
        else {
            System.out.println(" but it fails :-(");
        }
    }

    /**
     * Generates range of values of healing points.
     *
     * @return range of values of healing points
     */
    private int generateHealingPower() {
        return Adventurer.generateRangedValue(myMinSpecialHeal, myMaxSpecialHeal);
    }
}
