package src.Model;

/**
 * This program creates an outline for a monster character.
 *
 * @author anastasiavilenius
 * @version 05/07/23
 */
public abstract class Monster extends Adventurer {

    /**
     * Constructor that initializes fields.
     *
     * @param monsterThree
     * @param theHealth
     * @param theMinDamage
     * @param theMaxDamage
     * @param theAttackOdds
     * @param theBlockOdds
     * @param theAttackSpeed
     */
    protected Monster(String monsterThree, int theHealth, int theMinDamage, int theMaxDamage,
                      double theAttackOdds, double theBlockOdds, int theAttackSpeed) {
        super(theHealth, theMinDamage, theMaxDamage, theAttackOdds, theBlockOdds, theAttackSpeed);
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

//
//    /**
//     * Overrides subtractHitPoints method in DungeonClass and allows monster
//     * a chance to heal after hit points have been lost, as long as monster has not fainted.
//     *
//     * @param theHealth
//     */
//    @Override
//    protected void subtractHitPoints(final int theHealth) {
//        super.subtractHitPoints(theHealth);
//        if (alive() && canHeal()) {
//            myOriginalHitPoints += generateHealPoints();
//            System.out.println(getName() + " healed from the attack!");
//        }
//        else {
//            super.subtractHitPoints(theHealth);
//        }
//    }
//
}

