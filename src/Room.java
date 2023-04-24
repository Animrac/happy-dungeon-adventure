/*
 * Room.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * This program creates the rooms that fill the 2d array maze,
 * and populates the rooms with randomly generated objects (monsters, healing potions, crown pieces).
 * 
 * @author Anastasia Vilenius
 * @version 06/10/21
 */
public class Room {
	
	/**
	 * Random object for generation of items/monsters.
	 */
	private static final Random MY_RANDOM = new Random();
	
	/**
	 * Stores whether or not item has been found.
	 */
	private boolean myFound;
	
//	/**
//	 * Stores whether room has healing potion or not.
//	 */
//	private boolean hasHealingPotion;
//	
//	/**
//	 * Stores whether room has monster or not.
//	 */
//	private boolean hasMonster;
//	
//	/**
//	 * Stores whether room has crown piece or not.
//	 */
//	private boolean hasCrownPiece;
	
	/**
	 * Stores list of inventory items.
	 */
	private List<String> myRoomItems;
	
	/**
	 * Creates instance of class
	 */
	public Room() {
		setMyFound(false);
		myRoomItems = setRoomItems();
	}
	
	/**
	 * Creates specific instance of class.
	 * 
	 * @param theItem
	 */
	public Room(final String theItem) {
		setMyFound(false);
		myRoomItems = new ArrayList<String>();
		
		if (theItem == "entrance" ) {
			setMyFound(true);
			myRoomItems.add("I");
		} else if (theItem == "exit") {
			setMyFound(true);
			myRoomItems.add("O");
		} else if (theItem == "crown") {
			setMyFound(true);
			myRoomItems.add("C");
		}
	}
	
	/**
	 * Creates an array list of items in room,
	 * and adds items if they are generated.
	 * @return array;
	 */
	private ArrayList<String> setRoomItems() {
		ArrayList<String> array = new ArrayList<String>();
		
		if(MY_RANDOM.nextDouble() <= 0.1) {
			array.add("X"); //monster
		}
		if(MY_RANDOM.nextDouble() <= 0.1) {
			array.add("H"); //healing potion
		}
		if(MY_RANDOM.nextDouble() == 0) {
			array.add("E"); //empty
		}
		return array;
	}

	public boolean getMyFound() {
		return myFound;
	}

	public void setMyFound(boolean myFound) {
		this.myFound = myFound;
	}
}

	
//	@Override
//	public String toString() {
//		???
//	}
	

//}
//	public char getRoomContents() {
//		
//		int itemCount = 0;
//		char letter = 'E';
//		
//		if (hasHealingPotion) {
//			letter = 'H';
//			itemCount++;
//		}
//		if (hasMonster) {
//			letter = 'X';
//			itemCount++;
//		}
//		if (hasCrownPiece) {
//			letter = 'C';
//			itemCount++;
//		}
//		if (itemCount > 1) {
//			letter = 'M';
//		}
//		return letter;
//	}
