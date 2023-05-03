package Extra;/*
 * HeroesVersusMonsters.java
 *
 * TCSS 143 - Spring 2021
 * Assignment 5
 */

import java.util.Scanner;
import java.util.Random;

/**
 * This program interacts with the user to name the hero and monster,
 * and allows user to choose their hero.
 * 
 * @author Anastasia Vilenius
 * @version 06/10/21
 */
public class HeroesVersusMonsters {
	
	/**
	 * Random object used to generate random monsters.
	 */
	private static final Random MY_RANDOM = new Random();
	
	/**
	 * Prompts user to enter name of monster,
	 * and then randomly generates a kind of monster.
	 * 
	 * @return randoMonster
	 */
	public static Monster spawnMonster() {
		Scanner in  = new Scanner(System.in);
			
		System.out.print("First, choose a name for a monster: ");
		String name = in.next();
			
		int rand = MY_RANDOM.nextInt(3);
			
		Monster randoMonster = null;
			
		if (rand == 0) {
				randoMonster = new Goblin(name);
		} else if (rand == 1) {
				randoMonster = new Minotaur(name);
		} else {
				randoMonster = new Troll(name);
		}
		return randoMonster;
	}
		
	/**
	 * Prompts user to name their hero,
	 * and then allows them to pick between three types of heroes to play as.
	 * 
	 * @return chosenHero
	 */
	public static Hero chooseHero() {//throws Exception {
		Scanner inp = new Scanner(System.in);
			
		System.out.print("Now choose a name for your hero: ");
		String name = inp.next();
			
		System.out.println("Time to choose your hero!");
		System.out.println();
		System.out.println("1: Centaur \n2: Dryad \n3: Hunter");
		System.out.println();
			
		Hero chosenHero = null;
			
		if (inp.nextInt() == 1) {
			chosenHero = new Centaur(name);
		} else if (inp.nextInt() == 2) {
			chosenHero = new Dryad(name);
		} else if (inp.nextInt() == 3) {
			chosenHero = new Hunter(name);
		}
		return chosenHero;
	}
		
	public static void battle(Hero hero, Monster monster) {
		while (hero.alive() && monster.alive() && !hero.runAway()) {
			System.out.println(hero.getName() + " hit points: " + hero.getHitPoints());
			System.out.println(monster.getName() + "hit points: " + monster.getHitPoints());
			hero.attack(monster);
				
			if(!hero.runAway()) {
				monster.attack(hero);
				System.out.println("Press enter for next round");
				DungeonCharacter.input.nextLine();
			}
			else {
				System.out.println(hero.getName() + " bravely ran away...");
			}
		}
		if(monster.alive()) {
			System.out.println(monster.getName() + " defeated " + hero.getName() + "!");
		}
		else {
			System.out.println(hero.getName() + " defeated " + monster.getName() + "!");
		}
	}
	
	/**
	 * 
	 */
	public static void gamePlay() {
		Monster monster = spawnMonster();
		System.out.println("Oh no! A " + monster.getClass() + " has spawned!\nIt is named " + monster.getName()); //.getClass???
		System.out.println();
		
		Hero hero = chooseHero();
		System.out.println("Woah! " + hero.getName() + ", a " + hero.getClass() + ", dares to enter the dungeon!");
		System.out.println();
		
		battle(hero, monster);
	}
}
