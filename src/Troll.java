/*
 * Troll.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

/**
 * This program creates a Troll character, a monster.
 * 
 * @author Anastasia Vilenius
 * @version 04/27/21
 */
public class Troll extends Monster{
	
	/**
	 * Constructor that initializes fields.
	 * 
	 * @param theName
	 */
	public Troll (final String theName) {
		super(theName, 200, 40, 80, .5, 1, .1, 10, 20);
	}
}
