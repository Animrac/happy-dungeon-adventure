/*
 * Hunter.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

/**
 * This program creates a Hunter character, a hero.
 * 
 * @author Anastasia Vilenius
 * @version 04/27/21
 */
public class Hunter extends Hero {
	
	/**
	 * Constructor that initializes fields.
	 * 
	 * @param theName
	 */
	public Hunter (final String theName) {
		super(theName, 120, 20, 40, .9, 3, .3, .4);
	}
	
	/**
	 * Overrides specialAttack method in Hero class and allows the Hunter a surprise attack.
	 * 
	 * @param theOpponent
	 */
	@Override
	protected void specialAttack(final DungeonCharacter theOpponent) {
		System.out.print(getName() + " tries a surpise attack");
		if(canUseSpecialSkill()) { 
			
			System.out.println(" and lands another attack!!");
        }
		else {
			System.out.println(" but it fails :-(");
		}
	}
}

