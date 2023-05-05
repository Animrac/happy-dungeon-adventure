package Extra;/*
 * Dungeon.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

import java.util.Scanner;
/**
 * This program creates a 2d array to represent a dungeon maze,
 * and allows the user to move through it.
 * 
 * @author Anastasia Vilenius
 * @version 06/10/21
 */
public class Dungeon {
	
	/**
	 * 2d array dungeon maze.
	 */
	private Room[][] myMaze;
	
	/**
	 * Default rows for dungeon maze.
	 */
	public final static int DEFAULT_ROWS = 5;
	
	/**
	 * Default columns for dungeon maze.
	 */
	public final static int DEFAULT_COLUMNS = 5;
	
	/**
	 * 
	 */
	private static boolean north = false;
	
	/**
	 * 
	 */
	private static boolean south = false;
	
	/**
	 * 
	 */
	private static boolean west = false;
	
	/**
	 * 
	 */
	private static boolean east = false;		
	
	/**
	 * Constructor for Dungeon
	 * 
	 * @param theRows
	 * @param theCols
	 */
//	Dungeon(final int theRows, final int theCols) {
//		myMaze = setMaze();
//	}
	
	/**
	 * Creates 2d array to represent maze and fills with rooms.
	 * 
	 * @param hero
	 * @return dungeon
	 */
	private Room[][] setMaze(Hero hero) {
		Room[][] dungeon = new Room[DEFAULT_ROWS][DEFAULT_COLUMNS];
			
		for (int row = 0; row < dungeon.length; row++) {
			for (int col = 0; col < dungeon[row].length; col++) {
				dungeon[row][col] = new Room();
			}
		}
		hero.setCurPos(dungeon[DEFAULT_ROWS][DEFAULT_COLUMNS]);
		return dungeon;
	}
	
	/**
	 * Prompts user for which direction to move hero.
	 * ((not complete))
	 * 
	 * @param myMaze
	 * @param row
	 * @param col
	 * @return
	 */
	private static boolean moveHero(Room[][] myMaze, int row, int col) {
		Scanner in = new Scanner(System.in);
		String direction = "";
		
		System.out.println("Use the following commans to move your hero through the maze: \nw [north/up]"
				+ "\ns [south/down]\na [west/left]\nd [east/right]");
		direction = in.next();
		
		if (validMove(myMaze, row, col)) {
			if (direction == "w") {
			}
		} return false;
	}
	/**
	 * Checks to see if valid move was made my hero.
	 * From class.
	 * 
	 * @return row >= 0 && row < myMaze.length && col >= 0 && col < myMaze[row].length
	 */
	private static boolean validMove(Room[][] myMaze, int row, int col) {
		return row >= 0 && row < myMaze.length && col >= 0 && col < myMaze[row].length;
	}
}
