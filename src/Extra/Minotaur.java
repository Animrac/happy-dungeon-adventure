package Extra;/*
 * Minotaur.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

/**
 * This program creates a Minotaur character, a monster.
 * 
 * @author Anastasia Vilenius
 * @version 04/27/21
 */
public class Minotaur extends Monster {
	
	/**
	 * Constructor that initializes fields.
	 * 
	 * @param theName
	 */
	public Minotaur (final String theName) {
		super(theName, 120, 30, 60, .7, 2, .2, 10, 30);
	}
}