/*
 * DungeonAdventure.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

/**
 * This is the driver class for the entire Heroes versus Monsters game.
 * 
 * Majority of my user interaction takes place in the HeroesVersusMonster class,
 * so it made more sense to me to keep it there and execute it here.
 *
 * @author Anastasia Vilenius
 * @version 06/10/21
 */
public class DungeonAdventure {
	
	/**
	 * Driver method for class (and entire game)
	 * Calls displayIntro() class to display an introduction to the game.
	 * Calls HeroesVersusMonsters.gamePlay() to run game play.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		displayIntro();
		HeroesVersusMonsters.gamePlay();
	}
	
	/**
	 * Prints out an introduction to the game.
	 */
	public static void displayIntro() {
		System.out.println("You are about to begin a game of * HEROES VS MONSTERS *");
		System.out.println();
		System.out.println("In this game you will choose a hero to represent you"
				+ "\nas you enter a dungeon maze with mysterious rooms..."
				+ "\n\nThere is only one entrance and exit to the maze, but before you may leave"
				+ "\nyour hero must retrieve two pieces of the Crown of Coding..."
				+ "\n\nGood luck! You'll need it!\n");
	}
	
}
