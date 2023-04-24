/*
 * Goblin.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

/**
 * This program creates a Goblin character, a monster.
 * 
 * @author Anastasia Vilenius
 * @version 04/27/21
 */
public class Goblin extends Monster {
	
	/**
	 * Constructor that initializes fields.
	 * 
	 * @param theName
	 */
	public Goblin (final String theName) {
		super(theName, 80, 20, 40, .8, 3, .3, 20, 30);
	}
}
