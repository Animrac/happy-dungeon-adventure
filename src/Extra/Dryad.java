package Extra;/*
 * Dryad.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

/**
 * This program creates a Dryad character, a hero.
 * 
 * @author Anastasia Vilenius
 * @version 04/27/21
 */
public class Dryad extends Hero {
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
	 * @param theName
	 */
	public Dryad (final String theName) {
		super(theName, 100, 30, 60, .8, 3, .4, .6);
		setMinSpecialHeal(20);
		setMaxSpecialHeal(40);
	}
	
	/**
	 * Checks to make sure that the maximum healing points is above 0.
	 * 
	 * @param theMaxSpecialHeal
	 */
	private void setMaxSpecialHeal(final int theMaxSpecialHeal) {
		if (theMaxSpecialHeal < 0) {
			throw new IllegalArgumentException("special skill max damage must be greater than 0");
		}
		myMaxSpecialHeal = theMaxSpecialHeal;
		checkMinVersusMax();
	}
	
	/**
	 * Checks to make sure that the minimum healing points is above 0.
	 * 
	 * @param theMinSpecialHeal
	 */
	private void setMinSpecialHeal(final int theMinSpecialHeal) {
		if (theMinSpecialHeal < 0) {
			throw new IllegalArgumentException("special skill min damage must be greater than 0");
		}
		myMinSpecialHeal = theMinSpecialHeal;
		checkMinVersusMax();
	}	
	
	/**
	 * Checks to make sure the maximum healing points is greater than the minimum.
	 */
	private void checkMinVersusMax() {
		if(myMinSpecialHeal > myMaxSpecialHeal) {
			throw new IllegalArgumentException("min special damage cannot be more than max damage");	
		}
	}
	
	/**
	 * Overrides specialAttack method in DungeonCharacter class.
	 * Allows hero a chance to heal themselves once hit points have been lost.
	 * 
	 * @param theOpponent
	 */
	@Override
	protected void specialAttack(final DungeonCharacter theOpponent) {
		System.out.print(getName() + " tries to heal themself");
		if(canUseSpecialSkill()) {
			int health = generateHealingPower();
			
			System.out.println(" and increases their health by **" + health + "** !!");
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
		return DungeonCharacter.generateRangedValue(myMinSpecialHeal, myMaxSpecialHeal);
	}
}
