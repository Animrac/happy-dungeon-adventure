/**
 * This is the driver class for the entire Heroes versus Monsters game.
 *
 * @author Anastasia Vilenius, Carmina Cruz, Hui Wagner
 * @version 04/24/23
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

		//should probably research Java GUI stuff

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
